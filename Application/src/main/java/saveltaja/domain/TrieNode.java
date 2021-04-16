package saveltaja.domain;

import java.util.HashMap;

public class TrieNode {
    
    private String node;
    private HashMap<String, TrieNode> children;
    private boolean isLeaf;
    
    public TrieNode() {
        children = new HashMap();
    }
    
    public TrieNode(String node) {
        this.node = node;
        children = new HashMap();
    }
    
    public HashMap<String, TrieNode> getChildren() {
        return this.children;
    }
    
    public boolean isLeaf() {
        return this.isLeaf;
    }
    
    public void setLeaf(boolean isLeaf) {
        this.isLeaf = isLeaf;
    }
}
