package saveltaja.domain;

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
        String[] array = {".A4.", "Bb8", "^C#8", "*Db4", "Eb8", "^F2", ".G#4", "*B2"};
        translated = translator.translate(new List(array));
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
        List<String> tr = translator.translate(new List(array));
        
        assertEquals(2, tr.get(0).split(" ").length);
    }
    
    @Test
    public void translatesFlatsRight() {
        assertEquals("bes'8", translated.get(0).split(" ")[1]);
    }
    
    @Test
    public void translatesSharpsRight() {
        assertEquals("cis''8", translated.get(0).split(" ")[2]);
    }
    
    @Test
    public void translatesPitchesRight() {
        String[] tones = translated.get(1).split(" ");
        
        assertEquals("ees'8", tones[0]);
        assertEquals("f''2", tones[1]);
        assertEquals("gis4", tones[2]);
        assertEquals("b'''2", tones[3]);
    }
    
    @Test
    public void translatesDottedNotesRight() {
        String[] tones = translated.get(0).split(" ");
        assertEquals("a4.", tones[0]);
    }
}
