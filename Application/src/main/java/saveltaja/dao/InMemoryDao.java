package saveltaja.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InMemoryDao implements Dao {
    
    private ArrayList<String> notes;
    private List<String> written;
    
    public InMemoryDao() {
        String[] array = {"A","B",".C","E#","^D","G","^F","^D#","D","^C","Bb","^D#","^D","Eb",".B","^C",".A","F","G#"};
        notes = new ArrayList(Arrays.asList(array));
    }
    
    @Override
    public List<String> readAll() {
        return notes;
    }
    
    @Override
    public boolean writeNotes(List<String> notes) {
        written = notes;
        return true;
    }
    
    public List<String> readWritten() {
        return written;
    }
    
}
