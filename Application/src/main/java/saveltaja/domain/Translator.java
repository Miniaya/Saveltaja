package saveltaja.domain;

public class Translator {
    
    /**
     * Translates the tone format acceptable for .ly format
     * 
     * @param tones list of tones to be translated
     * 
     * @return list containing four translated notes on one element
     */
    public List translate(List tones) {
        List translated = new List();
        StringBuilder bar = new StringBuilder();
        
        for (int i = 0 ; i <= tones.length() ; i++) {
            if (i == tones.length()) {
                translated.add(bar.toString().strip());
                break;
            } else if (i != 0 && i % 4 == 0) {
                translated.add(bar.toString().strip());
                bar = new StringBuilder();
            } 
            
            StringBuilder temp = new StringBuilder(tones.get(i));
            if (temp.charAt(0) != 'R') {
                StringBuilder duration = separateDuration(temp);
                temp.delete(temp.length() - duration.length(), temp.length());
                temp = replaceAccidentals(replacePitches(temp));
                temp.append(duration);
            }
            
            bar.append(temp.toString().toLowerCase() + " ");
        }
        return translated;
    }
    
    /**
     * Separates the duration of the note (number and dot) from the note itself
     * 
     * @param tone where the duration will be separated
     * 
     * @return duration of the note in number form (and with dot)
     */
    private StringBuilder separateDuration(StringBuilder tone) {
        StringBuilder duration = new StringBuilder();
        
        if (tone.charAt(tone.length() - 1) == '.') {
            duration.append(tone.substring(tone.length() - 2));
        } else {
            duration.append(tone.charAt(tone.length() - 1));
        }
        
        return duration;
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
        
        if (tone.charAt(0) == '^') {
            tone.replace(0, 1, "");
            tone.append("''");
                
        } else if (tone.charAt(0) == '.') {
            tone.replace(0, 1, "");
                
        } else if (tone.charAt(0) == '*') {
            tone.replace(0, 1, "");
            tone.append("'''");
        } else {
            tone.append("'");
        }
        
        return tone;
    }
    
}
