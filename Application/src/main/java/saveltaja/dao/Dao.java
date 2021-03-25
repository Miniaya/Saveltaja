package saveltaja.dao;

import java.util.List;

public interface Dao {
    
    List<String> readAll();
    
    boolean writeNotes(List<String> notes);
    
}
