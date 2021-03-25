package saveltaja.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import saveltaja.dao.Dao;

public class Service {
    
    private Dao dao;
    private TreeMap<String, List<String>> substrings;
    private HashSet<String> tones;
    
    public Service(Dao dao) {
        this.dao = dao;
        this.substrings = new TreeMap();
        this.tones = new HashSet();
    }
    
    public void createNotes(int k, int duration) {
        createSubstrings(k);
//        String[] array = {"A", ".B", "^C", "^C", "^D", "B", "A", ".G", "A", "A#", "B", "^C", "A", "Gb", "^G", "^G", "^D", "Ab", "A", "B#", "^C", "A", "^C", "^D", "B", "A"};
//        ArrayList<String> list = new ArrayList(Arrays.asList(array));
//        dao.writeNotes(translateForLilypond(list));
        List<String> melody = createMelody(k, duration);
        dao.writeNotes(melody);
    }
    
    private List<String> readAllNotes() {
        return dao.readAll();
    }
    
    private void createSubstrings(int k) {
        List<String> notes = readAllNotes();
        
        for (int i = k - 1 ; i < notes.size() - 1 ; i++) {
            String substring = String.join("", notes.subList(i - k + 1, i + 1));
            tones.add(notes.get(i));
            substrings.putIfAbsent(substring, new ArrayList());
            substrings.get(substring).add(notes.get(i + 1));
        }
        
        System.out.println(substrings.keySet());
        System.out.println(substrings.entrySet());
    }
    
    private List<String> createMelody(int k, int duration) {
        ArrayList<String> melody = new ArrayList();
        ArrayList<String> singleTones = new ArrayList(tones);
        
        for (int i = 0 ; i < k ; i++) {
            Collections.shuffle(singleTones);
            melody.add(singleTones.get(0));
        }
        
        for (int i = k - 1 ; i < duration - 1 ; i++) {
            String substring = String.join("", melody.subList(i - k + 1, i + 1));
            List<String> nextTones = substrings.floorEntry(substring).getValue();
            
            melody.add(mostCommon(nextTones));
        }
        
        System.out.println(melody);
        
        return translateForLilypond(melody);
    }
    
    public static <T> T mostCommon(List<T> list) {
        Map<T, Integer> map = new HashMap<>();

        for (T t : list) {
            Integer val = map.get(t);
            map.put(t, val == null ? 1 : val + 1);
        }

        Entry<T, Integer> max = null;

        for (Entry<T, Integer> e : map.entrySet()) {
            if (max == null || e.getValue() > max.getValue())
                max = e;
        }

        return max.getKey();
    }


    
    private List<String> translateForLilypond(List<String> tones) {
        ArrayList<String> translated = new ArrayList();
        
        StringBuilder bar = new StringBuilder();
        
        for (int i = 0 ; i <= tones.size() ; i++) {
            if (i != 0 && i % 4 == 0) {
                translated.add(bar.toString().strip());
                bar = new StringBuilder();
            } else if (i == tones.size()) {
                translated.add(bar.toString().strip());
                break;
            }
            
            StringBuilder temp = new StringBuilder();
            temp.append(tones.get(i).toLowerCase());
            
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
