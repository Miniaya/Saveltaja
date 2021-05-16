package saveltaja.dao;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import saveltaja.domain.List;

public class FileDaoTest {
    
    private static FileDao dao;
    
    public FileDaoTest() {
        dao = new FileDao("testNotes.csv");
    }
    
    @AfterClass
    public static void tearDownClass() {
        dao.deleteFile("test.ly");
    }

    @Test
    public void readAllGetsAllNotesFromFile() {
        List read = dao.readAll();
        assertEquals(19, read.length());
    }
    
    @Test
    public void readAllReturnsNullWhenFileNotFound() {
        FileDao fail = new FileDao("non_existent.csv");
        assertEquals(null, fail.readAll());
    }
    
    @Test
    public void writeNotesSucceed() {
        String[] array = {"A'4 Cis''8 B'8 D4", "F'2 Ees'4 Des'4 B2"};
        List toWrite = new List(array);
        assertEquals("test.ly", dao.writeNotes(toWrite, "test"));
    }
}
