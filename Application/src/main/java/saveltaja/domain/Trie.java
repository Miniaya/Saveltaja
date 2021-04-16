package saveltaja.domain;

import java.util.HashMap;

/**
 * Provides a Trie data structure for storing the substrings of the melody.
 * Also provides the tools needed to handle the Trie.
 */
public class Trie {
    
    private TrieNode root;
    
    public Trie() {
        this.root = new TrieNode();
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
    public boolean find(List substring) {
        HashMap<String, TrieNode> children = root.getChildren();
        TrieNode node = null;
        
        for (int i = 0 ; i < substring.length() ; i++) {
            String note = substring.get(i);
            
            if (children.containsKey(note)) {
                node = children.get(note);
                children = node.getChildren();
            } else {
                node = null;
                break;
            }
        }
        
        if (node != null && node.isLeaf()) {
            return true;
        } else {
            return false;
        }
    }
}
