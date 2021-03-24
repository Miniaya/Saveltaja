package saveltaja.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;
import saveltaja.dao.Dao;

public class Service {
    
    private Dao dao;
    private TreeMap<String, List<String>> substrings;
    
    public Service(Dao dao) {
        this.dao = dao;
        this.substrings = new TreeMap();
    }
    
    public void createNotes(int k) {
        createSubstrings(k);
        String[] array = {"A", ".B", "^C", "^C", "^D", "B", "A", ".G", "A", "A#", "B", "^C", "A", "Gb", "^G", "^G", "^D", "Ab", "A", "B#", "^C", "A", "^C", "^D", "B", "A"};
        ArrayList<String> list = new ArrayList(Arrays.asList(array));
        dao.writeNotes(translateForLilypond(list));
    }
    
    
    
    private List<String> readAllNotes() {
        return dao.readAll();
    }
    
    private void createSubstrings(int k) {
        List<String> notes = readAllNotes();
        
        /*
        Works with k = 2
        */
        
        for (int i = k - 1 ; i < notes.size() - 1 ; i++) {
            String substring = String.join("", notes.subList(i - k + 1, i + 1));
            substrings.putIfAbsent(substring, new ArrayList());
            substrings.get(substring).add(notes.get(i + 1));
        }
        
//        dao.writeNotes(notes);
        
        System.out.println(substrings.keySet());
        System.out.println(substrings.entrySet());
    }
    
    private List<String> translateForLilypond(List<String> notes) {
        ArrayList<String> translated = new ArrayList();
        
        StringBuilder bar = new StringBuilder();
        
        for (int i = 0 ; i < notes.size() ; i++) {
            if (i != 0 && i % 4 == 0) {
                translated.add(bar.toString().strip());
                bar = new StringBuilder();
            }
            
            StringBuilder temp = new StringBuilder();
            temp.append(notes.get(i).toLowerCase());
            
            if (temp.indexOf("^") >= 0) {
                temp.replace(0, 1, "");
                temp.append("''");
                
            } else if (temp.indexOf(".") >= 0) {
                temp.replace(0, 1, "");
                
            } else {
                temp.append("'");
            }
            
            if (temp.indexOf("#") > 0) {
                temp.replace(1, 2, "is");
                
            } else if (temp.indexOf("b") > 0) {
                temp.replace(1, 2, "es");
            }
            
            bar.append(temp);
            bar.append(" ");
            
        }
        
        return translated;
    }
}
