package saveltaja.ui;

import java.io.FileInputStream;
import java.util.Properties;

import saveltaja.dao.FileDao;
import saveltaja.domain.Service;
import saveltaja.io.IO;

public class CLUI {
    
    private Service service;
    private IO io;
    private boolean[] choices;
    private int k;
    private int duration;
    
    public CLUI (IO io) {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("config.properties"));
            String noteFile = properties.getProperty("noteFile");
            this.service = new Service(new FileDao(noteFile));
        } catch (Exception e) {
            System.out.println("Something went wrong with notefile");
        }
        
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
        markovChain();
        duration();
        pdfChoice();
        musicxmlChoice();
        if (choices[1]) {
            musescoreChoice();
        }
        
        service.setChoices(choices);
        String file = service.createNotes(k, duration);
        
        if (file != null) {
            io.print("Saved notes to file " + file);
        } else {
            io.print("Couldn't save notes");
        }
    }
    
    private void markovChain() {
        while (true) {
            try {
                k = Integer.valueOf(io.readLine("Give the length of the Markov chain: "));
            } catch (NumberFormatException e) {
                io.print("Chain can't be negative or non numeric");
                continue;
            }
            
            if (k > 0) {
                break;
            } else {
                io.print("Chain can't be negative or non numeric");
            }
        }
    }
    
    private void duration() {
        while (true) {
            try {
                duration = Integer.valueOf(io.readLine("Give the number of tones in melody: "));
            } catch (NumberFormatException e) {
                io.print("Duration can't be negative or non numeric");
                continue;
            }
            
            if (duration > 0) {
                break;
            } else {
                io.print("Duration can't be negative or non numeric");
            }
        }
    }
    
    private void pdfChoice() {
        while (true) {
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
            
            if (pdf.equals("y") || pdf.equals("n")) {
                break;
            }
        }
    }
    
    private void musicxmlChoice() {
        while (true) {
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
            
            if (musicxml.equals("y") || musicxml.equals("n")) {
                break;
            }
        }
    }
    
    private void musescoreChoice() {
        while (true) {
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
            
            if (musescore.equals("y") || musescore.equals("n")) {
                break;
            }
        }
    }
}
