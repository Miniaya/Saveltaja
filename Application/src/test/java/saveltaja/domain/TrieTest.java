package saveltaja.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

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
        
        assertTrue(trie.search(aList));
        assertTrue(trie.search(list));
    }
    
    @Test
    public void addedNotesWithSameStartingNoteCanBeFound() {
        String[] array = {".Eb4", "^C4", ".B#8"};
        
        List aList = new List(array);
        
        this.trie.add(aList);
        this.trie.add(list);
        
        assertTrue(trie.search(aList));
        assertTrue(trie.search(list));
    }
    
    @Test
    public void findReturnsFalseIfListNotInTrie() {
        String[] array = {".Eb4", "^C4", ".B#8"};
        assertFalse(trie.search(new List(array)));
    }
    
    @Test
    public void nodesWithCorrectPerfixAreFound() {
        String[] array = {".Eb4", "D2", ".B#8"};
        String[] prefix = {".Eb4", "D2"};
        
        this.trie.add(list);
        this.trie.add(new List(array));
        
        String[] correct = {"^F#8", ".B#8"};
        
        assertEquals(new List(correct), trie.getLeafs(new List(prefix)));
    }
    
    @Test
    public void tooShortPrefixReturnsNull() {
        String[] array = {".Eb4"};
        this.trie.add(list);
        assertNull(trie.getLeafs(new List(array)));
    }
    
    @Test
    public void prefixWithoutLeafsReturnsNull() {
        this.trie.add(list);
        assertNull(trie.getLeafs(list));
    }
    
    @Test
    public void firstNodeIsFromFirstSetOfNodes() {
        String[] array = {"F2", "^C4", ".B#8"};
        
        this.trie.add(new List(array));
        this.trie.add(list);
        
        String first = trie.getFirst();
        
        assertTrue(first.equals("F2") || first.equals(".Eb4"));
    }
    
    @Test
    public void getNextReturnsCorrect() {
        String[] array = {"F2", "^C4", ".B#8"};
        
        this.trie.add(new List(array));
        this.trie.add(list);
        
        String next = trie.getNext(new List(new String[] {"F2"}));
        assertEquals("^C4", next);
    }
}