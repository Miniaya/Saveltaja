package saveltaja.ui;

import saveltaja.dao.FileDao;
import saveltaja.domain.Service;
import saveltaja.io.IO;

public class CLUI {
    
    private Service service;
    private IO io;
    
    public CLUI (IO io) {
        this.service = new Service(new FileDao("notes.csv"));
        this.io = io;
    }
    
    public void init() {
        io.print(
            "----------------------------------------------------- \n" +
            "--()------  _   __  _  _ __   __  __   __  __  ------ \n" +
            "--/|_----- |   |  | |\\/| |_| |  | |_  |_  |__| ------ \n" +
            "-(_|_)---- |_  |__| |  | |   |__| __| |__ | \\  ------ \n" +
            "---J------------------------------------------------- \n"
        );
        
        int k = Integer.valueOf(io.readLine("Give the length of the Markov chain: "));
        int duration = Integer.valueOf(io.readLine("Give the number of tones in melody: "));
        
        service.createNotes(k, duration);
    }
    
}
