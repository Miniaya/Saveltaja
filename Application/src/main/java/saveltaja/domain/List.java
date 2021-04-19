package saveltaja.domain;

import java.util.Arrays;

/**
 * Provides the list data structure where the notes are stored. Also provides tool for
 * handling the list and items in the list.
 */
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
    
    /**
     * Adds the item to the list
     * 
     * @param item item to be added to the list
     */
    public void add(String item) {
        if (this.index == array.length) {
            this.array = expandArray(this.array);
        }
        
        this.array[this.index] = item;
        this.index++;
    }
    
    /**
     * Adds all items from the array to the list
     * 
     * @param array items to be added to the list
     */
    public void addAll(String[] array) {
        for (String item : array) {
            this.add(item);
        }
    }
    
    /**
     * Expands the list if it becomes full
     * 
     * @param oldArray full array
     * 
     * @return expanded array with items from the old list in it
     */
    private String[] expandArray(String[] oldArray) {
        String[] newArray = new String[oldArray.length * 2];
        
        for (int i = 0 ; i < oldArray.length ; i++) {
            newArray[i] = oldArray[i];
        }
        
        return newArray;
    }
    
    /**
     * Get the item from specified index
     * 
     * @param index where the item will be get
     * 
     * @return the item from specified index
     */
    public String get(int index) {
        if (index >= this.index || this.index == 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        
        return this.array[index];
    }
    
    /**
     * Returns the length of the list
     * 
     * @return length of the list
     */
    public int length() {
        return this.index;
    }
    
    /**
     * Creates the sublist from list, start included, end excluded
     * 
     * @param start start index of the sublist, included
     * @param end end index of the sublist, excluded
     * 
     * @return specified sublist
     */
    public List subList(int start, int end) {
        String[] subArray = new String[end - start];
        int index = 0;
                
        for (int i = start ; i < end ; i++) {
            subArray[index] = this.array[i];
            index++;
        }
        
        return new List(subArray);
    }
    
    /**
     * Checks if the wanted value is in the List
     * 
     * @param item item wanted to be found
     * 
     * @return -1 if not found, otherwise index of the item
     */
    public int find(String item) {
        for (int i = 0 ; i < this.index ; i++) {
            if (this.array[i].equals(item)) {
                return i;
            }
        }
        
        return -1;
    }
    
    /**
     * Creates the sublist of the list, from start to the end of the list
     * 
     * @param start starting index of the sublist
     * 
     * @return specified sublist
     */
    public List subList(int start) {
        return subList(start, this.index);
    }
    
    /**
     * Returns one random item from the list
     * 
     * @return random item from the list
     */
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
    
    /**
     * Replaces the value from certain index with specified value
     * 
     * @param index index of the item to be replaced
     * @param item replacement
     */
    public void put(int index, String item) {
        this.array[index] = item;
    }
    
    /**
     * Deletes specified sublist, start included, end excluded
     * 
     * @param start starting index
     * @param end ending index
     */
    public void delete(int start, int end) {
        for (int i = end - 1 ; i >= start ; i--) {
            this.array[i] = null;
            index--;
        }
    }
    
    @Override
    public String toString() {
        String str = "";
        
        for (int i = 0 ; i < this.index ; i++) {
            str += this.array[i];
        }
        return str;
    }

    /**
     * Checks if the lists are the same
     * 
     * @param obj object to be compared
     * 
     * @return true if they are the same, false if not
     */
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
        if (other.length() < index || other.length() > index) {
            return false;
        }
        for (int i = 0 ; i < other.length() ; i++) {
            if (!other.get(i).equals(array[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Arrays.deepHashCode(this.array);
        hash = 83 * hash + this.index;
        return hash;
    }
}
