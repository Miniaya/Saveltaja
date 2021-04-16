package saveltaja.domain;

import java.util.HashMap;

public class Trie {
    
    private TrieNode root;
    
    public Trie() {
        this.root = new TrieNode();
    }
    
    public void add(List word) {
        HashMap<String, TrieNode> children = root.getChildren();
        
        for (int i = 0 ; i < word.length() ; i++) {
            String note = word.get(i);
            TrieNode node;
            
            if (children.containsKey(note)) {
                node = children.get(note);
            } else {
                node = new TrieNode(note);
                children.put(note, node);
            }
            
            children = node.getChildren();
            
            if (i == word.length() - 1) {
                node.setLeaf(true);
            }
        }
    }
    
    public boolean find(List word) {
        HashMap<String, TrieNode> children = root.getChildren();
        TrieNode node = null;
        
        for (int i = 0 ; i < word.length() ; i++) {
            String note = word.get(i);
            
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
