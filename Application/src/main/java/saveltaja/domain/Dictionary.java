package saveltaja.domain;

/**
 * Data structure that stores key-value pairs
 * 
 * @param <String> key, type String
 * @param <T> value, can be any type
 */
public class Dictionary<String, T> {
    private List<String> keys;
    private List<T> values;
    
    public Dictionary() {
        keys = new List();
        values = new List();
    }
    
    /**
     * Saves key-value pair into the Dictionary
     * 
     * @param key key to be stored
     * @param value value to be stored for specified key
     */
    public void put(String key, T value) {
        keys.add(key);
        values.add(value);
    }
    
    /**
     * Get value based on given key
     * 
     * @param key key for wanted value
     * 
     * @return value with given key
     */
    public T get(String key) {
        int index = keys.find(key);
        
        if (index < 0) {
            return null;
        }
        
        return values.get(index);
    }
    
    /**
     * Gets all stored keys
     * 
     * @return List of keys saved
     */
    public List<String> keySet() {
        return keys;
    }
    
    /**
     * Checks if the specified key is in Dictionary
     * 
     * @param key key to be checked
     * 
     * @return true, if key is found, false otherwise
     */
    public boolean containsKey(String key) {
        return keys.find(key) >= 0;
    }
    
    /**
     * Checks if the Dictionary is empty (it has no key-value pairs stored)
     * 
     * @return true, if empty, false otherwise
     */
    public boolean isEmpty() {
        return keys.length() == 0 && values.length() == 0;
    }
    
    /**
     * Returns the number of saved key-value pairs
     * 
     * @return how many pairs have been saved
     */
    public int size() {
        return keys.length();
    }
}
