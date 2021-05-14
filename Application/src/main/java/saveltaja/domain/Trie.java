package saveltaja.domain;

/**
 * Provides a Trie data structure for storing the substrings of the melody.
 * Also provides the tools needed to handle the Trie.
 */
public class Trie {
    
    private TrieNode root;
    private RandomGenerator random;
    
    public Trie() {
        this.root = new TrieNode();
        this.random = new RandomGenerator();
    }
    
    /**
     * Adds a new branch to the trie.
     * 
     * @param substring new substring to be added
     */
    public void add(List<String> substring) {
        Dictionary<String, TrieNode> children = root.getChildren();
        
        for (int i = 0 ; i < substring.length() ; i++) {
            String note = substring.get(i);
            TrieNode node;
            
            if (children.containsKey(note)) {
                node = children.get(note);
            } else {
                node = new TrieNode(note);
                children.put(note, node);
            }
            
            children = node.getChildren();
            
            if (i == substring.length() - 1) {
                node.setLeaf(true);
            }
        }
    }
    
    /**
     * Checks if the given substring is in the trie.
     * 
     * @param substring substring to be found
     * 
     * @return true if found, false otherwise
     */
    private TrieNode searchNode(List<String> substring) {
        Dictionary<String, TrieNode> children = root.getChildren();
        TrieNode node = null;
        
        for (int i = 0 ; i < substring.length() ; i++) {
            String note = substring.get(i);
            
            if (children.containsKey(note)) {
                node = children.get(note);
                children = node.getChildren();
            } else {
                return null;
            }
        }
        
        return node;
    }
    
    /**
     * Checks if the whole substring is in the trie
     * 
     * @param substring 
     * 
     * @return true if found, false otherwise
     */
    public boolean search(List<String> substring) {
        TrieNode node = searchNode(substring);
        
        if (node != null && node.isLeaf()) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Gets leafs of the given prefix
     * 
     * @param prefix
     * 
     * @return List of leafs
     */
    public List<String> getLeafs(List prefix) {
        List leafs = new List();
        TrieNode node = searchNode(prefix);
        
        if (node == null || node.getChildren().isEmpty()) {
            return null;
        } else {
            Dictionary<String, TrieNode> children = node.getChildren();
            
            for (int i = 0 ; i < children.keySet().length() ; i++) {
                String key = children.keySet().get(i);
                
                if (children.get(key).isLeaf()) {
                    leafs.add(key);
                } else {
                    return null;
                }
            }
            
            return leafs;
        }
    }
    
    /**
     * Gets and returns one of the top level nodes
     * 
     * @return one note
     */
    public String getFirst() {
        Dictionary<String, TrieNode> children = root.getChildren();
        List<String> notes = children.keySet();
        String rand = notes.get(random.getRandom(notes.length()));
        return rand;
    }
    
    /**
     * Gets and returns one of the next level nodes from given prefix
     * 
     * @param prefix first nodes
     * 
     * @return next note
     */
    public String getNext(List prefix) {
        TrieNode node = searchNode(prefix);
        List<String> notes = node.getChildren().keySet();
        String rand = notes.get(random.getRandom(notes.length()));
        return rand;
    }
    
}
