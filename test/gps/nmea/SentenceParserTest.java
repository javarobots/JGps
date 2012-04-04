package gps.nmea;

import gps.data.Coordinate;
import gps.data.GpsDataModel;
import gps.data.UTCTime;
import org.junit.*;

/**
 *
 * @author javarobots
 */
public class SentenceParserTest {

    private GpsDataModel mDataModel;
    private SentenceParser mParser;
    private SelectedSentences mSelectedSentences;

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
        mSelectedSentences = new SelectedSentences();
        mDataModel.setSelectedSentences(mSelectedSentences);
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
        System.out.println("Process Data String Test");
        String data = "3.0,M,,*7A,$GPGGA,122010,3503.51296,N,10632.78261,W,2,12,19.2,1653.9,M,-23.0,M,,*7E$GPGGA,122407,3503.51489,N,10632.78574,W,2,4,19.2,1656.9,M,-23.0,M,,*45";
        String[] processResult = mParser.processData(data);
        Assert.assertTrue(processResult.length == 3);
        Assert.assertEquals("GPGGA,122010,3503.51296,N,10632.78261,W,2,12,19.2,1653.9,M,-23.0,M,,*7E",processResult[1]);
        Assert.assertEquals("GPGGA,122407,3503.51489,N,10632.78574,W,2,4,19.2,1656.9,M,-23.0,M,,*45",processResult[2]);
    }

    /**
     * Test of parseSentence method, of class SentenceParser.
     */
    @Test
    public void testGGAParse() {
        System.out.println("GGA Parser Test");
        String sentence = "$GPGGA,122010,3503.51296,N,10632.78261,W,2,12,19.2,1653.9,M,-23.0,M,,*7E";
        boolean sentenceParsed = mParser.parseSentence(sentence);
        Assert.assertEquals(false, sentenceParsed);
        mSelectedSentences.setParseGGA(true);
        sentenceParsed = mParser.parseSentence(sentence);
        Assert.assertEquals(true, sentenceParsed);
        Coordinate coordinate = mDataModel.getGGACoordinate();
        if (coordinate == null){
            Assert.fail("Coordinate is null");
        } else {
            //Test time parsing
            UTCTime time = mDataModel.getGGATime();
            Assert.assertEquals(12, time.getHour());
            Assert.assertEquals(20, time.getMinute());
            Assert.assertEquals(10, time.getSecond());
            //Test coordinate parsing
            Assert.assertEquals(new Double(3503.51296), (Double) coordinate.getLatitude());
            Assert.assertEquals(new Double(10632.78261), (Double) coordinate.getLongitude());
            Assert.assertEquals("N", coordinate.getLatitudeHemisphere().getHemisphere());
            Assert.assertEquals("W", coordinate.getLongitudeHemisphere().getHemisphere());
            //Test quality parsing
            Assert.assertEquals(2, mDataModel.getGgaFixQuality());
            //Test number of satellites in use
            Assert.assertEquals(12, mDataModel.getGGANumberOfSatelites());
            //Test HDOP parsing
            Assert.assertEquals(19.2, mDataModel.getGGAHdop(), 0);
            //Test antenna height
            Assert.assertEquals(1653.9, mDataModel.getGGAHeightAboveSeaLevel(), 0);
            //Test geoidal height
            Assert.assertEquals(-23.0, mDataModel.getGGAGeoidalHeight(), 0);
        }
    }

    /**
     * Test of parseSentence method, of class SentenceParser.
     */
    @Test
    public void testVTGParse() {
        System.out.println("VTG Parser Test");
        String sentence = "$GPVTG,139.4,T,139.4,M,0.14,N,0.26,K*4F";
        boolean sentenceParsed = mParser.parseSentence(sentence);
        Assert.assertEquals(false, sentenceParsed);
        mSelectedSentences.setParseVTG(true);
        sentenceParsed = mParser.parseSentence(sentence);
        Assert.assertEquals(true, sentenceParsed);
        //Test true course parsing
        Assert.assertEquals(139.4, mDataModel.getVTGTrueCourse(), 0);
        //Test magnetic course parsing
        Assert.assertEquals(139.4, mDataModel.getVTGMagneticCourse(), 0);
        //Test knots parsing
        Assert.assertEquals(.14, mDataModel.getVTGSpeedInKnots(), 0);
        //Test KPH parsing
        Assert.assertEquals(.26, mDataModel.getVTGSpeedInKilometers(), 0);
    }

    /**
     * Test of parseSentence method, of class SentenceParser.
     */
    @Test
    public void testGLLParse() {
        System.out.println("GLL Parser Test");
        String sentence = "$GPGLL,3503.51296,N,10632.78261,W,122010,A*38";
        boolean sentenceParsed = mParser.parseSentence(sentence);
        Assert.assertEquals(false, sentenceParsed);
        mSelectedSentences.setParseGLL(true);
        sentenceParsed = mParser.parseSentence(sentence);
        Assert.assertEquals(true, sentenceParsed);
        Coordinate coordinate = mDataModel.getGLLCoordinate();
        if (coordinate == null){
            Assert.fail("Coordinate is null");
        } else {
            //Test coordinate parsing
            Assert.assertEquals((Double) coordinate.getLatitude(), new Double(3503.51296));
            Assert.assertEquals((Double) coordinate.getLongitude(), new Double(10632.78261));
            Assert.assertEquals(coordinate.getLatitudeHemisphere().getHemisphere(), "N");
            Assert.assertEquals(coordinate.getLongitudeHemisphere().getHemisphere(), "W");
            //Test status parsing
            Assert.assertEquals("A", mDataModel.getGLLStatus());
            //Test time parsing
            UTCTime time = mDataModel.getGLLTime();
            Assert.assertEquals(12, time.getHour());
            Assert.assertEquals(20, time.getMinute());
            Assert.assertEquals(10, time.getSecond());
        }
    }
}
