package saveltaja.domain;

import java.util.Arrays;

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
        if (index >= this.index || this.index == 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        
        return this.array[index];
    }
    
    public int length() {
        return this.index;
    }
    
    public String[] subList(int start, int end) {
        String[] subArray = new String[end - start];
        int index = 0;
                
        for (int i = start ; i < end ; i++) {
            subArray[index] = this.array[i];
            index++;
        }
        
        return subArray;
    }
    
    public String getRandom() {
        int i = -1;
                
        while(true) {
            i = (int) System.nanoTime() % this.index;
            
            if (i >= 0 && i < this.index) {
                break;
            }
        }
        
        return this.array[i];
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Arrays.deepHashCode(this.array);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final List other = (List) obj;
        if (!Arrays.deepEquals(this.array, other.array)) {
            return false;
        }
        return true;
    }
}
