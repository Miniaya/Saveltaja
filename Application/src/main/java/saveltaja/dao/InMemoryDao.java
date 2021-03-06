package saveltaja.dao;

import saveltaja.domain.List;

/**
 * This class is purely for testing purposes
 */
public class InMemoryDao implements Dao {
    
    private List<String> notes;
    private List<String> written;
    
    public InMemoryDao() {
        String[] array = {"A","B",".C","E#","^D","G","^F","^D#","D","^C","Bb","^D#","^D","Eb",".B","^C",".A","F","G#"};
        notes = new List(array);
    }
    
    @Override
    public void setChoices(boolean[] choices) {
        
    }
    
    @Override
    public List readAll() {
        return notes;
    }
    
    @Override
    public String writeNotes(List notes) {
        written = notes;
        return "Success";
    }
    
    public List<String> readWritten() {
        return written;
    }
    
}
