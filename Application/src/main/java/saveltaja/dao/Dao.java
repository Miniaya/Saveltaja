package saveltaja.dao;

import java.util.List;

public interface Dao {
    
    List<String> readAll();
    
    void writeNotes(List<String> notes);
    
}
