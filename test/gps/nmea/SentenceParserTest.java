package gps.nmea;

import gps.data.GpsDataModel;
import static org.junit.Assert.fail;
import org.junit.*;

/**
 *
 * @author javarobots
 */
public class SentenceParserTest {

    private SentenceParser mParser;

    public SentenceParserTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        mParser = new SentenceParser(new GpsDataModel());
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of processDataString method, of class SentenceParser.
     */
    @Test
    public void testProcessDataString() {
        System.out.println("processDataString");
        String data = "*37,$GPGGA,1,2,3,4,5";

        Assert.assertEquals("$GPGGA,1,2,3,4,5",mParser.processDataString(data));
    }

    /**
     * Test of parseSentence method, of class SentenceParser.
     */
    @Test
    public void testParseSentence() {
        System.out.println("parserSentence");
        String sentence = "";
        SentenceParser instance = null;
        instance.parseSentence(sentence);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
