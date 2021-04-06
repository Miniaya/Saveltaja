package saveltaja.domain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.TreeMap;
import saveltaja.dao.Dao;

/**
 * Provides tools needed to create a melody
 */
public class Service {
    
    private Dao dao;
    private TreeMap<String, List> substrings;
    private HashSet<String> tones;
    
    public Service(Dao dao) {
        this.dao = dao;
        this.substrings = new TreeMap();
        this.tones = new HashSet();
    }
    
    public void createNotes(int k, int duration) {
        createSubstrings(k);
        List melody = createMelody(k, duration);
        dao.writeNotes(melody);
    }
    
    /**
     * Creates a trie of substrings in the length of k from the list of notes.
     * Also maps which tones come after a certain substring.
     * 
     * @param k  length of the substring provided by user
     */
    private void createSubstrings(int k) {
        List notes = dao.readAll();
        
        for (int i = k - 1 ; i < notes.length() - 1 ; i++) {
            String substring = String.join("", notes.subList(i - k + 1, i + 1));
            tones.add(notes.get(i));
            substrings.putIfAbsent(substring, new List());
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
    private List createMelody(int k, int duration) {
        List melody = new List();
        List singleTones = new List(Arrays.stream(tones.toArray()).toArray(String[]::new));
        
        for (int i = 0 ; i < k ; i++) {
            
            melody.add(singleTones.getRandom());
        }
        
        for (int i = k - 1 ; i < duration - 1 ; i++) {
            String substring = String.join("", melody.subList(i - k + 1, i + 1));
            List nextTones = substrings.floorEntry(substring).getValue();
            Random r = new Random();
            
            melody.add(nextTones.get(r.nextInt(nextTones.length())));
        }
        
        Translator translator = new Translator();
        
        return translator.translate(melody);
    }

}
