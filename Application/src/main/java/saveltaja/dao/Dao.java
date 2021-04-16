package saveltaja.dao;

import saveltaja.domain.List;

public interface Dao {
    
    List readAll();
    
    boolean writeNotes(List notes);
    
    void setChoices(boolean[] choices);
    
}
