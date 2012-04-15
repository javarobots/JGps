package gps.calculations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.*;

/**
 *
 * @author javarobots74
 */
public class NavigationCalculationsTest {

    private NavigationCalculations mInstance;

    public NavigationCalculationsTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        mInstance = NavigationCalculations.getInstance();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of degreesMinutesToDegrees method, of class NavigationCalculations.
     */
    @Test
    public void testDegreesMinutesToDegrees() {
        System.out.println("Test degreesMinutesToDegrees");
        String degreesMinutes = "30 35.964";
        double expResult = 30.5994;
        double result = mInstance.degreesMinutesToDegrees(degreesMinutes);
        assertEquals(expResult, result, 0.0);
    }
    
    /**
     * Test of degreesMinutesToDegrees method, of class NavigationCalculations.
     */
    @Test
    public void testDegreesMinutesSecondsToDegrees() {
        System.out.println("Test degreesMinutesSecondsToDegrees");
        String degreesMinutesSeconds = "35 04 01.03";
        double expResult = 35.0669;
        double result = mInstance.degreeMinutesSecondsToDegrees(degreesMinutesSeconds);
        assertEquals(expResult, result, 0.0001);
    }

    /**
     * Test of degreesMinutesToRad method, of class NavigationCalculations.
     */
    @Test
    public void testDegreesMinutesToRad() {
        System.out.println("Test degreesMinutesToRad");
        double degrees = 23;
        double minutes = 10.5;
        double expResult = .40448;
        double result = mInstance.degreesMinutesToRad(degrees, minutes);
        assertEquals(expResult, result, 0.00001);
    }

    /**
     * Test of degreesToRad method, of class NavigationCalculations.
     */
    @Test
    public void testDegreesToRad() {
        System.out.println("Test degreesToRad");
        double degrees = 30.5994;
        double expResult = .53406;
        double result = mInstance.degreesToRad(degrees);
        assertEquals(expResult, result, 0.00001);
    }

    /**
     * Test of radToDegree method, of class NavigationCalculations.
     */
    @Test
    public void testRadToDegree() {
        System.out.println("Test radToDegree");
        double rads = .53406;
        double expResult = 30.5994;
        double result = mInstance.radToDegree(rads);
        assertEquals(expResult, result, 0.0001);
    }

    /**
     * Test of distanceInFeet method, of class NavigationCalculations.
     * The distance value and coordinates obtained from Google Earth.
     */
    @Test
    public void testDistanceInFeet() {
        System.out.println("distanceInFeet");
        double lat1 = mInstance.degreesToRad(35.057761);
        double lon1 = mInstance.degreesToRad(106.547575);
        double lat2 = mInstance.degreesToRad(35.057958);
        double lon2 = mInstance.degreesToRad(106.547383);
        double expResult = 90;
        double result = mInstance.distanceInFeet(lat1, lon1, lat2, lon2);
        assertEquals(expResult, result, 2.0);
    }

    /**
     * Test of headingToPoint method, of class NavigationCalculations.
     * The heading value and coordinates obtained from Google Earth.
     */
    @Test
    public void testHeadingToPoint() {
        System.out.println("headingToPoint");
        double lat1 = mInstance.degreesToRad(35.057761);
        double lon1 = mInstance.degreesToRad(106.547575);
        double lat2 = mInstance.degreesToRad(35.057958);
        double lon2 = mInstance.degreesToRad(106.547383);
        double expResult = 38;
        double result = mInstance.headingToPoint(lat1, lon1, lat2, lon2);
        assertEquals(expResult, result, 1);
    }
}
