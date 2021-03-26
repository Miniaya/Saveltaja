package saveltaja.domain;

import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TranslatorTest {
    
    private Translator translator;
    private List<String> translated;
    
    public TranslatorTest() {
        translator = new Translator();
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        String[] array = {".A", "Bb", "^C#", ".Db", "Eb", "^F", ".G#", "^B"};
        translated = translator.translate(Arrays.asList(array));
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void returnsListWithFourNoteElements() {
        assertEquals(4, translated.get(0).split(" ").length);
    }
    
    @Test
    public void returnsCorrectNumberOfNotes() {
        String[] array = {".A", "Bb"};
        List<String> tr = translator.translate(Arrays.asList(array));
        
        assertEquals(2, tr.get(0).split(" ").length);
    }
    
    @Test
    public void translatesFlatsRight() {
        assertEquals("bes'", translated.get(0).split(" ")[1]);
    }
    
    @Test
    public void translatesSharpsRight() {
        assertEquals("cis''", translated.get(0).split(" ")[2]);
    }
    
    @Test
    public void translatesPitchesRight() {
        String[] tones = translated.get(1).split(" ");
        
        assertEquals("ees'", tones[0]);
        assertEquals("f''", tones[1]);
        assertEquals("gis", tones[2]);
    }
}
