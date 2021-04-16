package saveltaja.ui;

import saveltaja.dao.FileDao;
import saveltaja.domain.Service;
import saveltaja.io.IO;

public class CLUI {
    
    private Service service;
    private IO io;
    private boolean[] choices;
    
    public CLUI (IO io) {
        this.service = new Service(new FileDao("notes.csv"));
        this.io = io;
        this.choices = new boolean[3];
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
        pdfChoice();
        musicxmlChoice();
        
        if (choices[1]) {
            musescoreChoice();
        }
        
        service.setChoices(choices);
        service.createNotes(k, duration);
    }
    
    private void pdfChoice() {
        String pdf = io.readLine("Do you want to export ly file to pdf? Requires Lilypond. (y/n)");
        
        switch (pdf) {
            case "y":
                choices[0] = true;
                break;
            case "n":
                choices[0] = false;
                break;
            default:
                io.print("Unknown command with pdf");
        }
    }
    
    private void musicxmlChoice() {
        String musicxml = io.readLine("Do you want to export ly file to musicxml? Requires Python-ly. (y/n)");
        
        switch (musicxml) {
            case "y":
                choices[1] = true;
                break;
            case "n":
                choices[1] = false;
                break;
            default:
                io.print("Unknown command with musicxml");
        }
    }
    
    private void musescoreChoice() {
        String musescore = io.readLine("Do you want to open musicxml file with MuseScore? "
                + "Requires exported musicxml file and MuseScore. (y/n)");
        
        switch (musescore) {
            case "y":
                choices[2] = true;
                break;
            case "n":
                choices[2] = false;
                break;
            default:
                io.print("Unknown command with MuseScore");
        }
    }
}
