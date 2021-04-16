package saveltaja.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import saveltaja.dao.InMemoryDao;

public class TrieTest {
    
    private Trie trie;
    private List list;
    
    public TrieTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        String[] array = {".Eb4", "D2", "^F#8"};
        this.trie = new Trie();
        this.list = new List(array);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void addedNotesWithDifferentStartingNoteCanBeFound() {
        String[] array = {"F2", "^C4", ".B#8"};
        
        List aList = new List(array);
        
        this.trie.add(aList);
        this.trie.add(list);
        
        assertTrue(trie.find(aList));
        assertTrue(trie.find(list));
    }
    
    @Test
    public void addedNotesWithSameStartingNoteCanBeFound() {
        String[] array = {".Eb4", "^C4", ".B#8"};
        
        List aList = new List(array);
        
        this.trie.add(aList);
        this.trie.add(list);
        
        assertTrue(trie.find(aList));
        assertTrue(trie.find(list));
    }
    
    @Test
    public void findReturnsFalseIfListNotInTrie() {
        String[] array = {".Eb4", "^C4", ".B#8"};
        assertFalse(trie.find(new List(array)));
    }
}