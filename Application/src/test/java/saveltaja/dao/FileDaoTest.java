package saveltaja.dao;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class FileDaoTest {
    
    private FileDao dao;
    
    public FileDaoTest() {
        dao = new FileDao("testNotes.csv");
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
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
}
