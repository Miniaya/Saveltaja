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
    public void getThrowsExceptionWhenListIsEmpty() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> list.get(0));
    }
    
    @Test
    public void getThrowsExceptionWhenIndexOutOfBounds() {
        list.addAll(array);
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> list.get(3));
    }
    
    @Test
    public void addAllAddsAllItems() {
        list.addAll(array);
        assertEquals(3, list.length());
    }
    
    @Test
    public void subListReturnsCorrectArray() {
        list.addAll(array);
        String[] sub = list.subList(0, 2);
        String[] correct = {"moi", "hei"};
        assertEquals(correct, sub);
    }
    
    @Test
    public void randomItemIsFromList() {
        list.addAll(array);
        String random = list.getRandom();
        boolean found = false;
        
        for (String a : array) {
            if (a.equals(random)) {
                found = true;
            }
        }
        
        assertTrue(found);
    }
    
    @Test
    public void equalsRecognizesSameLists() {
        list.addAll(array);
        List another = new List(array);
        assertEquals(list, another);
    }
    
    @Test
    public void listsWithSameLengthNotEqual() {
        list.addAll(array);
        String[] anotherArray = {"moi", "hei", "hei"};
        List another = new List(anotherArray);
        assertNotEquals(list, another);
    }
    
    @Test
    public void shorterListNotEqual() {
        list.addAll(array);
        String[] anotherArray = {"moi", "hei"};
        List another = new List(anotherArray);
        assertNotEquals(list, another);
    }
    
    @Test
    public void longerListNotEqual() {
        list.addAll(array);
        String[] anotherArray = {"moi", "hei", "terve", "hellurei"};
        List another = new List(anotherArray);
        assertNotEquals(list, another);
    }
    
    @Test
    public void emptyListNotNull() {
        assertFalse(list.equals(null));
    }
    
    @Test
    public void listAndArrayNotEqual() {
        list.addAll(array);
        assertNotEquals(list, array);
    }
}