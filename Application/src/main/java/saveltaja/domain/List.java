package saveltaja.domain;

public class List {
    
    private String[] array;
    private int index;
    
    public List(String[] array) {
        this.array = array;
        this.index = array.length;
    }
    
    public List() {
        this.array = new String[16];
        this.index = 0;
    }
    
    public void add(String item) {
        if (this.index == array.length) {
            this.array = expandArray(this.array);
        }
        
        this.array[this.index] = item;
        this.index++;
    }
    
    public void addAll(String[] array) {
        for (String item : array) {
            this.add(item);
        }
    }
    
    private String[] expandArray(String[] oldArray) {
        String[] newArray = new String[oldArray.length * 2];
        
        for (int i = 0 ; i < oldArray.length ; i++) {
            newArray[i] = oldArray[i];
        }
        
        return newArray;
    }
    
    public String get(int index) {
        return this.array[index];
    }
    
    public int length() {
        return this.index;
    }
}
