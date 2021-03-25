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
        String[] correct = {"A","B",".C","E#","^D","G","^F","^D#","D","^C","Bb","^D#","^D","Eb",".B","^C",".A","F","G#"};
        assertEquals(correct, dao.readAll().toArray());
    }
    
    @Test
    public void readAllReturnsNullWhenFileNotFound() {
        FileDao fail = new FileDao("non_existent.csv");
        assertEquals(null, fail.readAll());
    }
    
    @Test
    public void writeNotesSucceed() {
        String[] array = {"A' Cis'' B' D", "F' Ees' Des' B"};
        ArrayList<String> toWrite = new ArrayList(Arrays.asList(array));
        assertTrue(dao.writeNotes(toWrite, "test.ly"));
    }
}
