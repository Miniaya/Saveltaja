package saveltaja.domain;

public class Dictionary<String, T> {
    private List<String> keys;
    private List<T> values;
    
    public Dictionary() {
        keys = new List();
        values = new List();
    }
    
    public void put(String key, T value) {
        keys.add(key);
        values.add(value);
    }
    
    public T get(String key) {
        int index = keys.find(key);
        
        if (index < 0) {
            return null;
        }
        
        return values.get(index);
    }
    
    public List<String> keySet() {
        return keys;
    }
    
    public boolean containsKey(String key) {
        return keys.find(key) >= 0;
    }
    
    public boolean isEmpty() {
        return keys.length() == 0 && values.length() == 0;
    }
    
    public int size() {
        return keys.length();
    }
}
