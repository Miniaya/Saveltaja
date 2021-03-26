package saveltaja.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import saveltaja.dao.Dao;

/**
 * Provides tools needed to create a melody
 */
public class Service {
    
    private Dao dao;
    private TreeMap<String, List<String>> substrings;
    private HashSet<String> tones;
    
    public Service(Dao dao) {
        this.dao = dao;
        this.substrings = new TreeMap();
        this.tones = new HashSet();
    }
    
    public void createNotes(int k, int duration) {
        createSubstrings(k);
        List<String> melody = createMelody(k, duration);
        dao.writeNotes(melody);
    }
    
    /**
     * Gathers all notes provided in .csv file
     * 
     * @see saveltaja.dao.FileDao#readAll() 
     * 
     * @return list of notes saved to file
     */
    private List<String> readAllNotes() {
        return dao.readAll();
    }
    
    /**
     * Creates a trie of substrings in the length of k from the list of notes.
     * Also maps which tones come after a certain substring.
     * 
     * @param k  length of the substring provided by user
     */
    private void createSubstrings(int k) {
        List<String> notes = readAllNotes();
        
        for (int i = k - 1 ; i < notes.size() - 1 ; i++) {
            String substring = String.join("", notes.subList(i - k + 1, i + 1));
            tones.add(notes.get(i));
            substrings.putIfAbsent(substring, new ArrayList());
            substrings.get(substring).add(notes.get(i + 1));
        }
        
        System.out.println(substrings.keySet());
        System.out.println(substrings.entrySet());
    }
    
    /**
     * Creates the melody using information provided in the trie
     * 
     * @param k length of the substring provided by user
     * @param duration  number of tones in melody
     * 
     * @return Translated melody ready to put on file
     */
    private List<String> createMelody(int k, int duration) {
        ArrayList<String> melody = new ArrayList();
        ArrayList<String> singleTones = new ArrayList(tones);
        
        for (int i = 0 ; i < k ; i++) {
            Collections.shuffle(singleTones);
            melody.add(singleTones.get(0));
        }
        
        for (int i = k - 1 ; i < duration - 1 ; i++) {
            String substring = String.join("", melody.subList(i - k + 1, i + 1));
            List<String> nextTones = substrings.floorEntry(substring).getValue();
            
            melody.add(mostCommon(nextTones));
        }
        
        System.out.println(melody);
        
        return translateForLilypond(melody);
    }
    
    /**
     * Gets the most common tone from the list of tones
     * 
     * @param <T>   Generic value
     * @param list  list of tones
     * 
     * @return most common tone on the list
     */
    private static <T> T mostCommon(List<T> list) {
        Map<T, Integer> map = new HashMap<>();

        for (T t : list) {
            Integer val = map.get(t);
            map.put(t, val == null ? 1 : val + 1);
        }

        Entry<T, Integer> max = null;

        for (Entry<T, Integer> e : map.entrySet()) {
            if (max == null || e.getValue() > max.getValue())
                max = e;
        }

        return max.getKey();
    }

    /**
     * Translates the tone format acceptable for .ly format
     * 
     * @param tones list of tones to be translated
     * 
     * @return list containing four translated notes on one element
     */
    private List<String> translateForLilypond(List<String> tones) {
        ArrayList<String> translated = new ArrayList();
        StringBuilder bar = new StringBuilder();
        
        for (int i = 0 ; i <= tones.size() ; i++) {
            if (i == tones.size()) {
                translated.add(bar.toString().strip());
                break;
            } else if (i != 0 && i % 4 == 0) {
                translated.add(bar.toString().strip());
                bar = new StringBuilder();
            } 
            
            StringBuilder temp = new StringBuilder();
            temp.append(tones.get(i));
            temp = replaceAccidentals(replacePitches(temp));
            
            bar.append(temp.toString().toLowerCase() + " ");
        }
        
        return translated;
    }
    
    /**
     * Replaces sharps with '-is' and flats with '-es'
     * 
     * @param tone  tone to be tarnslated
     * 
     * @return translated tone
     */
    private StringBuilder replaceAccidentals(StringBuilder tone) {
        
        if (tone.indexOf("#") > 0) {
            tone.replace(1, 2, "is");
            
        } else if (tone.indexOf("b") > 0) {
            tone.replace(1, 2, "es");
        }
        
        return tone;
    }
    
    /**
     * Replaces Pitch markings to correct form
     * 
     * @param tone  to be translated
     * 
     * @return translated tone
     */
    private StringBuilder replacePitches(StringBuilder tone) {
        
        if (tone.indexOf("^") >= 0) {
            tone.replace(0, 1, "");
            tone.append("''");
                
        } else if (tone.indexOf(".") >= 0) {
            tone.replace(0, 1, "");
                
        } else {
            tone.append("'");
        }
        
        return tone;
    }
}
