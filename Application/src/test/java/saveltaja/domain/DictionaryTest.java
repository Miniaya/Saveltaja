package saveltaja.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class DictionaryTest {
    
    private Dictionary<String, String> dic;
    
    public DictionaryTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        dic = new Dictionary();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void newDictionaryIsEmpty() {
        assertTrue(dic.isEmpty());
        assertEquals(0, dic.size());
    }
    
    @Test
    public void newKeyValuePairCanBeAdded() {
        dic.put("character", "Winnie The Pooh");
        assertEquals(1, dic.size());
        assertTrue(dic.containsKey("character"));
    }
    
    @Test
    public void valueCanBeObtainedWithCorrectKey() {
        dic.put("apina", "monkey");
        assertEquals("monkey", dic.get("apina"));
        dic.put("peruna", "potato");
        dic.put("laukku", "bag");
        assertEquals("potato", dic.get("peruna"));
    }
    
    @Test
    public void invalidKeyReturnsNull() {
        dic.put("tietokone", "computer");
        assertNull(dic.get("piano"));
    }
    
    @Test
    public void keySetReturnsCorrect() {
        dic.put("fps", "Counter-Strike");
        dic.put("sandbox", "Terraria");
        dic.put("survival", "Rust");
        
        assertEquals(new List(new String[] {"fps", "sandbox", "survival"}), dic.keySet());
    }
}
