package saveltaja.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileDao implements Dao {
    
    private File file;
    
    public FileDao (String fileName) {
        this.file = new File(fileName);
    }
    
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
        }
        
        return read;
    }
    
    @Override
    public void writeNotes(List<String> notes) {
        File noteFile = createNewFile();
        
        try {
            FileWriter writer = new FileWriter(noteFile.getName());
            writer.write("\\version \"2.18.2\"\n");
            writer.write("{\n");
            
            for (int i = 0 ; i < notes.size() ; i++) {
                writer.write("  " + notes.get(i) + "\n");
            }
            
            writer.write("}");
            writer.close();
            
        } catch (IOException ex) {
            Logger.getLogger(FileDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private File createNewFile() {
        try {
            File noteFile = new File("test.ly");
            
            if (noteFile.createNewFile()) {
                System.out.println("File created: " + noteFile.getName());
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
