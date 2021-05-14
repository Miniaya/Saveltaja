package saveltaja.domain;

import saveltaja.dao.Dao;

/**
 * Provides tools needed to create a melody
 */
public class Service {
    
    private Dao dao;
    private Trie substrings;
    
    public Service(Dao dao) {
        this.dao = dao;
        this.substrings = new Trie();
    }
    
    /**
     * @see saveltaja.dao.FileDao setChoices
     * 
     * @param choices choices made by user
     */
    public void setChoices(boolean[] choices) {
        dao.setChoices(choices);
    }
    
    public String createNotes(int k, int duration) {
        if (!createSubstrings(k)) {
            return null;
        }
        
        List melody = createMelody(k, duration);
        
        return dao.writeNotes(melody);
    }
    
    /**
     * Creates a trie of substrings in the length of k from the list of notes.
     * Also maps which tones come after a certain substring.
     * 
     * @param k  length of the substring provided by user
     */
    private boolean createSubstrings(int k) {
        List<String> notes = dao.readAll();
        
        if (notes == null) {
            return false;
        }
        
        for (int i = k ; i < notes.length() - 1 ; i++) {
            List substring = notes.subList(i - k, i + 1);
            substrings.add(substring);
        }
        
        return true;
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

        for (int i = 1 ; i < k ; i++) {
            melody.add(substrings.getNext(melody));
        }
        
        for (int i = k ; i < duration ; i++) {
            List substring = melody.subList(i - k, i);
            melody.add(substrings.getNext(substring));
        }
        
        Translator translator = new Translator();
        
        return translator.translate(melody);
    }

}