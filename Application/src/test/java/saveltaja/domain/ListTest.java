package saveltaja.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ListTest {
    
    private List list;
    private String[] array = {"moi", "hei", "terve"};
    
    public ListTest() {

    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        list = new List();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void constructorWithoutParameters() {
        assertEquals(0, list.length());
    }
    
    @Test
    public void constructorWithParameters() {
        List l = new List(array);
        assertEquals(3, l.length());
    }
    
    @Test
    public void anItemCanBeAdded() {
        list.add("Hello");
        assertEquals(1, list.length());
    }
    
    @Test
    public void getReturnsCorrectItem() {
        list.add("this");
        list.add("test");
        list.add("is");
        list.add("great");
        assertEquals("is", list.get(2));
    }
    
    @Test
    public void addAllAddsAllItems() {
        list.addAll(array);
        assertEquals(3, list.length());
    }
    
}