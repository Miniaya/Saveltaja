package saveltaja.domain;

public class Translator {
    
    /**
     * Translates the tone format acceptable for LY format
     * 
     * @param tones list of tones to be translated
     * 
     * @return list containing four translated notes on one element
     */
    public List translate(List tones) {
        List translated = new List();
        String bar = "";
        
        for (int i = 0 ; i <= tones.length() ; i++) {
            if (i == tones.length()) {
                translated.add(bar.toString().strip());
                break;
            } else if (i != 0 && i % 4 == 0) {
                translated.add(bar);
                bar = "";
            } 
            
            List temp = new List(tones.get(i).split(""));
            if (!temp.get(0).equals("R")) {
                String duration = separateDuration(temp);
                temp.delete(temp.length() - duration.length(), temp.length());
                temp = replaceAccidentals(replacePitches(temp));
                temp.add(duration);
            }
            
            bar += temp.toString().toLowerCase() + " ";
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
    private String separateDuration(List tone) {
        String duration;
        
        if (tone.get(tone.length() - 1).equals(".")) {
            List subList = tone.subList(tone.length() - 2);
            duration = subList.get(0) + subList.get(1);
        } else {
            duration = tone.get(tone.length() - 1);
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
    private List replaceAccidentals(List tone) {
        int index = tone.find("#");
        
        if (index > 0) {
            tone.put(index, "is");
            return tone;
            
        }
        
        index = tone.find("b");
        
        if (index > 0) {
            tone.put(index, "es");
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
    private List replacePitches(List tone) {
        
        if (tone.get(0).equals("^")) {
            tone.put(0, "");
            tone.add("''");
                
        } else if (tone.get(0).equals(".")) {
            tone.put(0, "");
                
        } else if (tone.get(0).equals("*")) {
            tone.put(0, "");
            tone.add("'''");
        } else {
            tone.add("'");
        }
        
        return tone;
    }
    
}
