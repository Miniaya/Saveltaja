package saveltaja.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import saveltaja.dao.InMemoryDao;

public class ServiceTest {
    
    private Service s;
    private InMemoryDao dao;
    
    public ServiceTest() {
        dao = new InMemoryDao();
        s = new Service(dao);
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
    public void durationIsCorrect() {
        s.createNotes(2, 6);
        int len = dao.readWritten().get(0).split(" ").length + dao.readWritten().get(1).split(" ").length;
        assertEquals(6, len);
    }
}