package saveltaja.dao;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class FileDaoTest {
    
    private static FileDao dao;
    
    public FileDaoTest() {
        dao = new FileDao("testNotes.csv");
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
        dao.deleteFile("test.ly");
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void readAllGetsAllNotesFromFile() {
        String[] correct = {"A4","B4",".C8","E#8","^D2","G4","^F4","^D#8","D8","^C4","Bb8","^D#4","^D2","Eb2",".B4","^C4",".A8","F8","G#2"};
        assertEquals(correct, dao.readAll().toArray());
    }
    
    @Test
    public void readAllReturnsNullWhenFileNotFound() {
        FileDao fail = new FileDao("non_existent.csv");
        assertEquals(null, fail.readAll());
    }
    
    @Test
    public void writeNotesSucceed() {
        String[] array = {"A'4 Cis''8 B'8 D4", "F'2 Ees'4 Des'4 B2"};
        ArrayList<String> toWrite = new ArrayList(Arrays.asList(array));
        assertTrue(dao.writeNotes(toWrite, "test"));
    }
}
