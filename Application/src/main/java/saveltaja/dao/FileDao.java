package saveltaja.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import saveltaja.domain.List;

/**
 * Provides tools for reading and writing files
 */
public class FileDao implements Dao {
    
    private File file;
    
    public FileDao (String fileName) {
        this.file = new File(fileName);
    }
    
    /**
     * Reads the notes from CSV file and saves them to a list
     * 
     * @return list of notes from the file
     */
    @Override
    public List readAll() {
        List read = new List();
        
        try {
            Scanner reader = new Scanner(file);
            while(reader.hasNextLine()) {
                String[] splitLine = reader.nextLine().split(";");
                
                if (splitLine.length == 1) {
                    continue;
                }
                
                read.addAll(splitLine);
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        return read;
    }
    
    /**
     * Writes the notes from list to a specific file
     * 
     * @param notes list of notes to be written
     * 
     * @return true whether the writing succeeded, false otherwise
     */
    @Override
    public boolean writeNotes(List notes) {
        return writeNotes(notes, "mun");
    }
    
    /**
     * Writes the notes from list to a file specified
     * 
     * @param notes list of notes to be written
     * @param fileName  name of the file 
     * 
     * @return  true whether the writing succeded, false otherwise
     */
    public boolean writeNotes(List notes, String fileName) {
        File noteFile = createNewFile(fileName);
        
        try {
            FileWriter writer = new FileWriter(noteFile.getName());
            writer.write("\\version \"2.18.2\"\n{\n");
            
            for (int i = 0 ; i < notes.length() ; i++) {
                writer.write("  " + notes.get(i) + "\n");
            }
            
            writer.write("}");
            writer.close();
            
            exportToPdf(noteFile);
            exportToMusicXML(noteFile);
            openMuseScore(noteFile);
            
            return true;
        } catch (IOException ex) {
            Logger.getLogger(FileDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    /**
     * Creates a PDF file from given LY file
     * 
     * @param noteFile file to be converted to PDF
     * 
     * @return true, if PDF is created
     */
    private boolean exportToPdf(File noteFile) {
        try {
            Process process = Runtime.getRuntime().exec(
                    new String[]{"sh", "-c", "lilypond --pdf " + noteFile.getName()},
                    null,
                    new File(noteFile.getAbsoluteFile().getParent()));
            
            printResults(process);
            File pdf = new File(noteFile.getName().split("ly")[0] + "pdf");
            
            return pdf.exists();
            
        } catch (IOException ex) {
            Logger.getLogger(FileDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    private boolean exportToMusicXML(File noteFile) {
        try {
            File musicxml = new File(noteFile.getName().split("ly")[0] + "musicxml");
            Process process = Runtime.getRuntime().exec(
                    new String[]{"sh", "-c", "ly musicxml " + noteFile.getName() + " > " + musicxml.getName()},
                    null,
                    new File(noteFile.getAbsoluteFile().getParent()));
            
            printResults(process);
            
            return musicxml.exists();
            
        } catch (IOException ex) {
            Logger.getLogger(FileDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    private void openMuseScore(File noteFile) {
        try {
            Process process = Runtime.getRuntime().exec(
                    new String[]{"sh", "-c", "xdg-open " + noteFile.getName().split("ly")[0] + "musicxml"},
                    null,
                    new File(noteFile.getAbsoluteFile().getParent()));
            
            printResults(process);
            File musicxml = new File(noteFile.getName().split("ly")[0] + "musicxml");
            
        } catch (IOException ex) {
            Logger.getLogger(FileDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * prints trace of what happens when a process is executed
     * 
     * @param process 
     * 
     * @throws IOException 
     */
    private static void printResults(Process process) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = "";
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(FileDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Deletes specified file from project root
     * 
     * @param fileName file to be deleted
     */
    public void deleteFile(String fileName) {
        File file = new File(fileName);
        file.delete();
    }
    
    /**
     * Creates new file in .ly format
     * 
     * @param fileName  name of the file
     * 
     * @return created file
     */
    private File createNewFile(String fileName) {
        try {
            File noteFile = new File(fileName + ".ly");
            
            if (noteFile.createNewFile()) {
                System.out.println("Notes can be found from " + noteFile.getName());
            } else {
                System.out.println("File already exists.");
            }
            
            return noteFile;
            
        } catch (IOException ex) {
            Logger.getLogger(FileDao.class.getName()).log(Level.SEVERE, null, ex);
            
            return null;
        }
        
    }
    
}
