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
     */
    @Test
    public void testDistanceInFeet() {
        System.out.println("distanceInFeet");
        double lat1 = 0.0;
        double lon1 = 0.0;
        double lat2 = 0.0;
        double lon2 = 0.0;
        double expResult = 0.0;
        double result = mInstance.distanceInFeet(lat1, lon1, lat2, lon2);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of headingToPoint method, of class NavigationCalculations.
     */
    @Test
    public void testHeadingToPoint() {
        System.out.println("headingToPoint");
        double lat1 = 0.0;
        double lon1 = 0.0;
        double lat2 = 0.0;
        double lon2 = 0.0;
        double expResult = 0.0;
        double result = mInstance.headingToPoint(lat1, lon1, lat2, lon2);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
