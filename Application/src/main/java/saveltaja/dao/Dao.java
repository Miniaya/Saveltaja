package saveltaja.dao;

import saveltaja.domain.List;

public interface Dao {
    
    List readAll();
    
    String writeNotes(List notes);
    
    void setChoices(boolean[] choices);
    
}
