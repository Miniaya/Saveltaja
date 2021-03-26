package saveltaja.domain;

import java.util.ArrayList;
import java.util.List;

public class Translator {
    
    /**
     * Translates the tone format acceptable for .ly format
     * 
     * @param tones list of tones to be translated
     * 
     * @return list containing four translated notes on one element
     */
    public List<String> translate(List<String> tones) {
        ArrayList<String> translated = new ArrayList();
        StringBuilder bar = new StringBuilder();
        
        for (int i = 0 ; i <= tones.size() ; i++) {
            if (i == tones.size()) {
                translated.add(bar.toString().strip());
                break;
            } else if (i != 0 && i % 4 == 0) {
                translated.add(bar.toString().strip());
                bar = new StringBuilder();
            } 
            
            StringBuilder temp = new StringBuilder();
            temp.append(tones.get(i));
            temp = replaceAccidentals(replacePitches(temp));
            
            bar.append(temp.toString().toLowerCase() + " ");
        }
        
        return translated;
    }
    
    /**
     * Replaces sharps with '-is' and flats with '-es'
     * 
     * @param tone  tone to be translated
     * 
     * @return translated tone
     */
    private StringBuilder replaceAccidentals(StringBuilder tone) {
        
        if (tone.indexOf("#") > 0) {
            tone.replace(1, 2, "is");
            
        } else if (tone.indexOf("b") > 0) {
            tone.replace(1, 2, "es");
        }
        
        return tone;
    }
    
    /**
     * Replaces Pitch markings to correct form
     * 
     * @param tone  to be translated
     * 
     * @return translated tone
     */
    private StringBuilder replacePitches(StringBuilder tone) {
        
        if (tone.indexOf("^") >= 0) {
            tone.replace(0, 1, "");
            tone.append("''");
                
        } else if (tone.indexOf(".") >= 0) {
            tone.replace(0, 1, "");
                
        } else {
            tone.append("'");
        }
        
        return tone;
    }
    
}
