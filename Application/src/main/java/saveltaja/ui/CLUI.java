package saveltaja.ui;

import saveltaja.dao.FileDao;
import saveltaja.domain.Service;
import saveltaja.io.IO;

public class CLUI {
    
    private Service service;
    
    public CLUI (IO io) {
        this.service = new Service(new FileDao("nuotit.csv"));
    }
    
    public void init() {
        service.createNotes(2);
    }
    
}
