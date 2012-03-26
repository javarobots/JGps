package gps.nmea;

import gps.data.GpsDataModel;
import org.junit.*;

/**
 *
 * @author javarobots
 */
public class SentenceParserTest {

    private GpsDataModel mDataModel;
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
        mDataModel = new GpsDataModel();
        mParser = new SentenceParser(mDataModel);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of processDataString method, of class SentenceParser.
     */
    @Test
    public void testProcessData() {
        System.out.println("processDataString");
        String data = "*37,$GPGGA,013359,3503.5131,N,10632.7851,W,0,00,,,M,,M,,*4C$GPGGA,013359,3503.9999,N,10632.9999,W,0,00,,,M,,M,,*4C";
        String[] processResult = mParser.processData(data);
        Assert.assertTrue(processResult.length == 3);
        Assert.assertEquals("GPGGA,013359,3503.5131,N,10632.7851,W,0,00,,,M,,M,,*4C",processResult[1]);
        Assert.assertEquals("GPGGA,013359,3503.9999,N,10632.9999,W,0,00,,,M,,M,,*4C",processResult[2]);
    }

    /**
     * Test of parseSentence method, of class SentenceParser.
     */
    @Test
    public void testParseSentence() {
        System.out.println("parserSentence");
        String sentence = "$GPGGA,013359,3503.5131,N,10632.7851,W,0,00,,,M,,M,,*4C";
        mParser.parseSentence(sentence);
        Assert.assertEquals((Double) mDataModel.getGgaLatitude(), new Double(3503.5131));
        Assert.assertEquals((Double) mDataModel.getGgaLongitude(), new Double(10632.7851));
        Assert.assertEquals(mDataModel.getGgaLatitudeHemisphere(), "N");
        Assert.assertEquals(mDataModel.getGgaLongitudeHemispere(), "W");
    }
}
