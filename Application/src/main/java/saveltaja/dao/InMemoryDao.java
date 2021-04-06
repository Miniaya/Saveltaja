package saveltaja.dao;

import saveltaja.domain.List;

public class InMemoryDao implements Dao {
    
    private List notes;
    private List written;
    
    public InMemoryDao() {
        String[] array = {"A","B",".C","E#","^D","G","^F","^D#","D","^C","Bb","^D#","^D","Eb",".B","^C",".A","F","G#"};
        notes = new List(array);
    }
    
    @Override
    public List readAll() {
        return notes;
    }
    
    @Override
    public boolean writeNotes(List notes) {
        written = notes;
        return true;
    }
    
    public List readWritten() {
        return written;
    }
    
}
