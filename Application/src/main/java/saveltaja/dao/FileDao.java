package saveltaja.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    public List<String> readAll() {
        ArrayList<String> read = new ArrayList();
        
        try {
            Scanner reader = new Scanner(file);
            while(reader.hasNextLine()) {
                String[] splitLine = reader.nextLine().split(";");
                
                if (splitLine.length == 1) {
                    continue;
                }
                
                read.addAll(Arrays.asList(splitLine));
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
    public boolean writeNotes(List<String> notes) {
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
    public boolean writeNotes(List<String> notes, String fileName) {
        File noteFile = createNewFile(fileName);
        
        try {
            FileWriter writer = new FileWriter(noteFile.getName());
            writer.write("\\version \"2.18.2\"\n{\n");
            
            for (int i = 0 ; i < notes.size() ; i++) {
                writer.write("  " + notes.get(i) + "\n");
            }
            
            writer.write("}");
            writer.close();
            
            createPdf(noteFile);
            
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
    private boolean createPdf(File noteFile) {
        try {
            Process process = Runtime.getRuntime().exec(
                    new String[]{"sh", "-c", "lilypond --pdf " + noteFile.getName()},
                    null,
                    new File(noteFile.getAbsoluteFile().getParent()));
            
            printResults(process);
            File pdf = new File(noteFile.getName() + ".pdf");
            
            return pdf.exists();
            
        } catch (IOException ex) {
            Logger.getLogger(FileDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    /**
     * prints trace of what happens when a process is executed
     * 
     * @param process 
     * 
     * @throws IOException 
     */
    private static void printResults(Process process) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = "";
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
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
