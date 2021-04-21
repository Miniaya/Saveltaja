package saveltaja.domain;

import java.util.Arrays;
import java.util.HashMap;

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
    public void add(List substring) {
        HashMap<String, TrieNode> children = root.getChildren();
        
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
    private TrieNode searchNode(List substring) {
        HashMap<String, TrieNode> children = root.getChildren();
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
    
    public boolean search(List substring) {
        TrieNode node = searchNode(substring);
        
        if (node != null && node.isLeaf()) {
            return true;
        } else {
            return false;
        }
    }
    
    public List getLeafs(List prefix) {
        List leafs = new List();
        TrieNode node = searchNode(prefix);
        
        if (node == null || node.getChildren().isEmpty()) {
            return null;
        } else {
            HashMap<String, TrieNode> children = node.getChildren();
            
            for (String key : children.keySet()) {
                if (children.get(key).isLeaf()) {
                    leafs.add(key);
                } else {
                    return null;
                }
            }
            
            return leafs;
        }
    }
    
    public String getFirst() {
        HashMap<String, TrieNode> children = root.getChildren();
        String[] notes = Arrays.stream(children.keySet().toArray()).toArray(String[]::new);
        String rand = notes[random.getRandom(notes.length)];
//        System.out.println(rand);
        return rand;
    }
    
    public String getNext(List prefix) {
        TrieNode node = searchNode(prefix);
        String[] notes = Arrays.stream(node.getChildren().keySet().toArray()).toArray(String[]::new);
        String rand = notes[random.getRandom(notes.length)];
//        System.out.println(rand);
        return rand;
    }
    
}
