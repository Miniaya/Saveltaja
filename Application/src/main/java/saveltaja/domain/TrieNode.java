package saveltaja.domain;

import java.util.HashMap;

/**
 * A tool used to store trie branches.
 */
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
    
    /**
     * @return all children of the node
     */
    public HashMap<String, TrieNode> getChildren() {
        return this.children;
    }
    
    /**
     * @return true, if the node is leaf, false otherwise
     */
    public boolean isLeaf() {
        return this.isLeaf;
    }
    
    /**
     * Set the variable to true or false, depending on if the node is leaf or not
     * 
     * @param isLeaf true, if the node is leaf, false otherwise
     */
    public void setLeaf(boolean isLeaf) {
        this.isLeaf = isLeaf;
    }
}
