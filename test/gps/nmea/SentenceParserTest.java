package gps.nmea;

import gps.data.*;
import java.util.List;
import static org.junit.Assert.*;
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
        System.out.println("Test Process Data String");
        String data = "3.0,M,,*7A,$GPGGA,122010,3503.51296,N,10632.78261,W,2,12,19.2,1653.9,M,-23.0,M,,*7E$GPGGA,122407,3503.51489,N,10632.78574,W,2,4,19.2,1656.9,M,-23.0,M,,*45";
        String[] processResult = mParser.processData(data);
        assertTrue(processResult.length == 3);
        assertEquals("GPGGA,122010,3503.51296,N,10632.78261,W,2,12,19.2,1653.9,M,-23.0,M,,*7E",processResult[1]);
        assertEquals("GPGGA,122407,3503.51489,N,10632.78574,W,2,4,19.2,1656.9,M,-23.0,M,,*45",processResult[2]);
    }

    /**
     * Test of parseSentence method, of class SentenceParser.
     */
    @Test
    public void testGGAParse() {
        System.out.println("Test GGA Parser");
        String sentence = "$GPGGA,122010,3503.51296,N,10632.78261,W,2,12,19.2,1653.9,M,-23.0,M,,*7E";
        boolean sentenceParsed = mParser.parseSentence(sentence);
        assertEquals(false, sentenceParsed);
        mSelectedSentences.setParseGGA(true);
        sentenceParsed = mParser.parseSentence(sentence);
        assertEquals(true, sentenceParsed);
        Coordinate coordinate = mDataModel.getGGACoordinate();
        if (coordinate == null){
            fail("Coordinate is null");
        } else {
            //Test time parsing
            UTCTime time = mDataModel.getGGATime();
            assertEquals(12, time.getHour());
            assertEquals(20, time.getMinute());
            assertEquals(10, time.getSecond());
            //Test coordinate parsing
            assertEquals(new Double(3503.51296), (Double) coordinate.getLatitude());
            assertEquals(new Double(10632.78261), (Double) coordinate.getLongitude());
            assertEquals("N", coordinate.getLatitudeHemisphere().getHemisphere());
            assertEquals("W", coordinate.getLongitudeHemisphere().getHemisphere());
            //Test quality parsing
            assertEquals(2, mDataModel.getGgaFixQuality());
            //Test number of satellites in use
            assertEquals(12, mDataModel.getGGANumberOfSatelites());
            //Test HDOP parsing
            assertEquals(19.2, mDataModel.getGGAHdop(), 0);
            //Test antenna height
            assertEquals(1653.9, mDataModel.getGGAHeightAboveSeaLevel(), 0);
            //Test geoidal height
            assertEquals(-23.0, mDataModel.getGGAGeoidalHeight(), 0);
        }
    }

    /**
     * Test of parseSentence method, of class SentenceParser.
     */
    @Test
    public void testVTGParse() {
        System.out.println("Test VTG Parser");
        String sentence = "$GPVTG,139.4,T,139.4,M,0.14,N,0.26,K*4F";
        boolean sentenceParsed = mParser.parseSentence(sentence);
        assertEquals(false, sentenceParsed);
        mSelectedSentences.setParseVTG(true);
        sentenceParsed = mParser.parseSentence(sentence);
        assertEquals(true, sentenceParsed);
        //Test true course parsing
        assertEquals(139.4, mDataModel.getVTGTrueCourse(), 0);
        //Test magnetic course parsing
        assertEquals(139.4, mDataModel.getVTGMagneticCourse(), 0);
        //Test knots parsing
        assertEquals(.14, mDataModel.getVTGSpeedInKnots(), 0);
        //Test KPH parsing
        assertEquals(.26, mDataModel.getVTGSpeedInKilometers(), 0);
    }

    /**
     * Test of parseSentence method, of class SentenceParser.
     */
    @Test
    public void testGLLParse() {
        System.out.println("Test GLL Parser");
        String sentence = "$GPGLL,3503.51296,N,10632.78261,W,122010,A*38";
        boolean sentenceParsed = mParser.parseSentence(sentence);
        assertEquals(false, sentenceParsed);
        mSelectedSentences.setParseGLL(true);
        sentenceParsed = mParser.parseSentence(sentence);
        assertEquals(true, sentenceParsed);
        Coordinate coordinate = mDataModel.getGLLCoordinate();
        if (coordinate == null){
            fail("Coordinate is null");
        } else {
            //Test coordinate parsing
            assertEquals((Double) coordinate.getLatitude(), new Double(3503.51296));
            assertEquals((Double) coordinate.getLongitude(), new Double(10632.78261));
            assertEquals(coordinate.getLatitudeHemisphere().getHemisphere(), "N");
            assertEquals(coordinate.getLongitudeHemisphere().getHemisphere(), "W");
            //Test status parsing
            assertEquals("A", mDataModel.getGLLStatus());
            //Test time parsing
            UTCTime time = mDataModel.getGLLTime();
            assertEquals(12, time.getHour());
            assertEquals(20, time.getMinute());
            assertEquals(10, time.getSecond());
        }
    }

    /**
     * Test of parseSentence method, of class SentenceParser.
     *
     */
    @Test
    public void testGSAParse() {
        System.out.println("Test GSA Parser");
        String sentence = "$GPGSA,A,3,02,05,15,18,25,26,29,30,51,12,16,21,13.6,19.2,9.6*31";
        boolean sentenceParsed = mParser.parseSentence(sentence);
        assertEquals(false, sentenceParsed);
        mSelectedSentences.setParseGSA(true);
        sentenceParsed = mParser.parseSentence(sentence);
        assertEquals(true, sentenceParsed);
        //Test mode parsing
        assertEquals("A", mDataModel.getmGSAMode());
        //Test status parsing
        assertEquals(3, mDataModel.getGsaFixMode());
        //Test PRN parsing
        assertEquals(02, mDataModel.getmGSAPRNNumber()[0]);
        assertEquals(05, mDataModel.getmGSAPRNNumber()[1]);
        assertEquals(15, mDataModel.getmGSAPRNNumber()[2]);
        assertEquals(18, mDataModel.getmGSAPRNNumber()[3]);
        assertEquals(25, mDataModel.getmGSAPRNNumber()[4]);
        assertEquals(26, mDataModel.getmGSAPRNNumber()[5]);
        assertEquals(29, mDataModel.getmGSAPRNNumber()[6]);
        assertEquals(30, mDataModel.getmGSAPRNNumber()[7]);
        assertEquals(51, mDataModel.getmGSAPRNNumber()[8]);
        assertEquals(12, mDataModel.getmGSAPRNNumber()[9]);
        assertEquals(16, mDataModel.getmGSAPRNNumber()[10]);
        assertEquals(21, mDataModel.getmGSAPRNNumber()[11]);
        //Test dilution of precision parsing
        assertEquals(13.6, mDataModel.getGsaPdop(), 0);
        assertEquals(19.2, mDataModel.getGsaHdop(), 0);
        assertEquals(9.6, mDataModel.getGsaVdop(), 0);
    }

    /**
     * Test of parseSentence method, of class SentenceParser.
     *
     */
    @Test
    public void testGSAEmptyPRNParse() {
        System.out.println("Test GSA Empty PRN Parser");
        String sentence = "$GPGSA,A,3,02,05,15,18,25,26,29,,51,12,16,,13.6,19.2,9.6*31";
        mSelectedSentences.setParseGSA(true);
        mParser.parseSentence(sentence);
        //Test PRN parsing
        assertEquals(02, mDataModel.getmGSAPRNNumber()[0]);
        assertEquals(05, mDataModel.getmGSAPRNNumber()[1]);
        assertEquals(15, mDataModel.getmGSAPRNNumber()[2]);
        assertEquals(18, mDataModel.getmGSAPRNNumber()[3]);
        assertEquals(25, mDataModel.getmGSAPRNNumber()[4]);
        assertEquals(26, mDataModel.getmGSAPRNNumber()[5]);
        assertEquals(29, mDataModel.getmGSAPRNNumber()[6]);
        assertEquals(0, mDataModel.getmGSAPRNNumber()[7]);
        assertEquals(51, mDataModel.getmGSAPRNNumber()[8]);
        assertEquals(12, mDataModel.getmGSAPRNNumber()[9]);
        assertEquals(16, mDataModel.getmGSAPRNNumber()[10]);
        assertEquals(0, mDataModel.getmGSAPRNNumber()[11]);
    }

    @Test
    public void testRMCParse() {
        System.out.println("Test RMC Parser");
        String sentence = "$GPRMC,122010,A,3503.51296,N,10632.78261,W,0.08,118.4,010412,0.0,E*56";
        boolean sentenceParsed = mParser.parseSentence(sentence);
        assertEquals(true, sentenceParsed);
        mSelectedSentences.setParseRMC(false);
        sentenceParsed = mParser.parseSentence(sentence);
        assertEquals(false, sentenceParsed);

        //Test time parsing
        UTCTime time = mDataModel.getRMCTime();
        assertEquals(12, time.getHour());
        assertEquals(20, time.getMinute());
        assertEquals(10, time.getSecond());

        //Test status parse
        assertEquals("A", mDataModel.getRMCStatus());

        //Test coordinate parse
        Coordinate coordinate = mDataModel.getRMCCoordinate();
        assertEquals(3503.51296, coordinate.getLatitude(), 0);
        assertEquals(10632.78261, coordinate.getLongitude(), 0);
        assertEquals("N", coordinate.getLatitudeHemisphere().getHemisphere());
        assertEquals("W", coordinate.getLongitudeHemisphere().getHemisphere());

        //Test speed parse
        assertEquals(0.08, mDataModel.getRmcSpeedOverGround(), 0);

        //Test course parse
        assertEquals(118.4, mDataModel.getRmcTrueCourse(), 0);

        //Test date parse
        UTCDate date = mDataModel.getRMCDate();
        assertEquals(1, date.getDay());
        assertEquals(4, date.getMonth());
        assertEquals(12, date.getYear());

        //Test magnetic variation
        assertEquals(0.0, mDataModel.getRMCMagneticVariation(), 0);

        //Test variation direction
        assertEquals("E", mDataModel.getRMCMagneticVariationDirection());
    }

    @Test
    public void testGSVParse() {
        System.out.println("Test GSV Parser");
        String[] sentences = {"$GPGSV,3,1,12,02,08,085,27,05,33,045,16,15,33,148,22,18,32,228,31*72",
                                "$GPGSV,3,2,12,25,25,211,20,26,35,095,20,29,86,321,41,30,16,306,22*76",
                                "$GPGSV,3,3,12,51,49,181,34,12,03,179,54,16,02,329,54,21,38,294,54*78"};
        boolean sentenceParsed = mParser.parseSentence(sentences[0]);
        assertEquals(false, sentenceParsed);
        mSelectedSentences.setParseGSV(true);
        sentenceParsed = mParser.parseSentence(sentences[0]);
        assertEquals(true, sentenceParsed);
        sentenceParsed = mParser.parseSentence(sentences[1]);
        assertEquals(true, sentenceParsed);
        sentenceParsed = mParser.parseSentence(sentences[2]);
        assertEquals(true, sentenceParsed);

        //Get Satellites In View list and compare its size
        List<SatelliteInView> satellitesInView = mDataModel.getSatellitesInView();
        assertEquals(12,satellitesInView.size());

        int[] expectedValues = {2,8,85,27,
                                5,33,45,16,
                                15,33,148,22,
                                18,32,228,31,
                                25,25,211,20,
                                26,35,95,20,
                                29,86,321,41,
                                30,16,306,22,
                                51,49,181,34,
                                12,03,179,54,
                                16,02,329,54,
                                21,38,294,54};
        for (int i = 0; i < 12; i++){
            SatelliteInView currentSatellite = satellitesInView.get(i);
            assertEquals(expectedValues[(i*4) + 0], currentSatellite.getPrnNumber());
            assertEquals(expectedValues[(i*4) + 1], currentSatellite.getElevation());
            assertEquals(expectedValues[(i*4) + 2], currentSatellite.getAzimuth());
            assertEquals(expectedValues[(i*4) + 3], currentSatellite.getCarrierToNoiseRatio());
        }

        //Parse set again and ensure lists reset
        sentenceParsed = mParser.parseSentence(sentences[0]);
        assertEquals(true, sentenceParsed);
        sentenceParsed = mParser.parseSentence(sentences[1]);
        assertEquals(true, sentenceParsed);
        sentenceParsed = mParser.parseSentence(sentences[2]);
        assertEquals(true, sentenceParsed);

        //Get Satellites In View list and compare its size
        satellitesInView = mDataModel.getSatellitesInView();
        assertEquals(12,satellitesInView.size());
        for (int i = 0; i < 12; i++){
            SatelliteInView currentSatellite = satellitesInView.get(i);
            assertEquals(expectedValues[(i*4) + 0], currentSatellite.getPrnNumber());
            assertEquals(expectedValues[(i*4) + 1], currentSatellite.getElevation());
            assertEquals(expectedValues[(i*4) + 2], currentSatellite.getAzimuth());
            assertEquals(expectedValues[(i*4) + 3], currentSatellite.getCarrierToNoiseRatio());
        }
    }
}
