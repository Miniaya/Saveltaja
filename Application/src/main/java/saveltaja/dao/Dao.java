package saveltaja.dao;

import java.util.List;

public interface Dao {
    
    public List<String> readAll();
    
    public void writeNotes(List<String> notes);
    
}
