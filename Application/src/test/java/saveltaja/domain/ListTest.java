package saveltaja.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ListTest {
    
    private List<String> list;
    private String[] array = {"moi", "hei", "terve"};
    
    @Before
    public void setUp() {
        list = new List();
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
        List<String> sub = list.subList(0, 2);
        String[] correct = {"moi", "hei"};
        assertEquals(new List(correct), sub);
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
        List<String> another = new List(anotherArray);
        assertNotEquals(list, another);
    }
    
    @Test
    public void shorterListNotEqual() {
        list.addAll(array);
        String[] anotherArray = {"moi", "hei"};
        List<String> another = new List(anotherArray);
        assertNotEquals(list, another);
    }
    
    @Test
    public void longerListNotEqual() {
        list.addAll(array);
        String[] anotherArray = {"moi", "hei", "terve", "hellurei"};
        List<String> another = new List(anotherArray);
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
    
    @Test
    public void putReplacesWantedValue() {
        list.addAll(array);
        list.put(2, "tervehdys");
        assertEquals("tervehdys", list.get(2));
    }
    
    @Test
    public void subListWithoutEndingArgumentIsCorrect() {
        list.addAll(array);
        List sub = list.subList(1);
        assertEquals(2, sub.length());
        assertEquals("hei", sub.get(0));
        assertEquals("terve", sub.get(1));
    }
    
    @Test
    public void findReturnsRightIdex() {
        list.addAll(array);
        assertEquals(0, list.find("moi"));
    }
    
    @Test
    public void findReturnsCorrectWhenItemNotFound() {
        list.addAll(array);
        assertEquals(-1, list.find("tervehdys"));
    }
    
    @Test
    public void deleteWorks() {
        list.addAll(array);
        list.delete(1, list.length());
        assertEquals(1, list.length());
        assertEquals("moi", list.get(list.length() - 1));
    }
}