package saveltaja.domain;

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
        
        melody.add(substrings.getFirst());
//        System.out.println("kierros 0");

        for (int i = 1 ; i < k ; i++) {
//            System.out.println("kierros " + i);
            melody.add(substrings.getNext(melody));
        }
        
        for (int i = k ; i < duration ; i++) {
            List substring = melody.subList(i - k, i);
            
//            System.out.println("kierros " + i);
            melody.add(substrings.getNext(substring));
        }
        
        Translator translator = new Translator();
        
        return translator.translate(melody);
    }

}