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
     * Creates a trie of substrings in the length of k from the list of notes.
     * Also maps which tones come after a certain substring.
     * 
     * @param k  length of the substring provided by user
     */
    private void createSubstrings(int k) {
        List<String> notes = dao.readAll();
        
        for (int i = k - 1 ; i < notes.size() - 1 ; i++) {
            String substring = String.join("", notes.subList(i - k + 1, i + 1));
            tones.add(notes.get(i));
            substrings.putIfAbsent(substring, new ArrayList());
            substrings.get(substring).add(notes.get(i + 1));
        }
        
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
        
        Translator translator = new Translator();
        
        return translator.translate(melody);
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

}
