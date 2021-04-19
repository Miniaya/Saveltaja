package saveltaja.domain;

import java.util.Arrays;
import java.util.HashSet;
import saveltaja.dao.Dao;

/**
 * Provides tools needed to create a melody
 */
public class Service {
    
    private Dao dao;
    private Trie substrings;
    private HashSet<String> tones;
    
    public Service(Dao dao) {
        this.dao = dao;
        this.substrings = new Trie();
        this.tones = new HashSet();
    }
    
    /**
     * @see saveltaja.dao.FileDao setChoices
     * 
     * @param choices choices made by user
     */
    public void setChoices(boolean[] choices) {
        dao.setChoices(choices);
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
        
        for (int i = k ; i < notes.length() - 1 ; i++) {
            // This is temporary solution
            if (notes.get(i).equals(":")) {
                continue;
            }
            List substring = notes.subList(i - k, i + 1);
            tones.add(notes.get(i));
            substrings.add(substring);
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
        // This is temporary solution, I just needed it to work
//        List singleTones = new List(Arrays.stream(tones.toArray()).toArray(String[]::new));
        String[] singleTones = {"A4", "B4", "^C8", "^C8", "^D4", "B8", "A8", "G8", "A4", "A4", "B4"};

        for (int i = 0 ; i < k ; i++) {
            
            melody.add(singleTones[i]);
        }
        
        for (int i = k ; i < duration - 1 ; i++) {
            List substring = melody.subList(i - k, i);
            List nextTones = substrings.getLeafs(substring);
            
            melody.add(nextTones.getRandom());
        }
        
        Translator translator = new Translator();
        
        return translator.translate(melody);
    }

}