package gps.calculations;

import static org.junit.Assert.*;
import org.junit.*;

/**
 *
 * @author javarobots74
 */
public class NavigationCalculationsTest {

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
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of degreesMinutesToDegrees method, of class NavigationCalculations.
     */
    @Test
    public void testDegreesMinutesToDegrees() {
        System.out.println("degreesMinutesToDegrees");
        String degreesMinutes = "";
        NavigationCalculations instance = null;
        double expResult = 0.0;
        double result = instance.degreesMinutesToDegrees(degreesMinutes);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of degreesMinutesToRad method, of class NavigationCalculations.
     */
    @Test
    public void testDegreesMinutesToRad() {
        System.out.println("degreesMinutesToRad");
        double degrees = 0.0;
        double minutes = 0.0;
        NavigationCalculations instance = null;
        double expResult = 0.0;
        double result = instance.degreesMinutesToRad(degrees, minutes);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of degreesToRad method, of class NavigationCalculations.
     */
    @Test
    public void testDegreesToRad() {
        System.out.println("degreesToRad");
        double degrees = 0.0;
        NavigationCalculations instance = null;
        double expResult = 0.0;
        double result = instance.degreesToRad(degrees);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of radToDegree method, of class NavigationCalculations.
     */
    @Test
    public void testRadToDegree() {
        System.out.println("radToDegree");
        double rads = 0.0;
        NavigationCalculations instance = null;
        double expResult = 0.0;
        double result = instance.radToDegree(rads);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
        NavigationCalculations instance = null;
        double expResult = 0.0;
        double result = instance.distanceInFeet(lat1, lon1, lat2, lon2);
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
        NavigationCalculations instance = null;
        double expResult = 0.0;
        double result = instance.headingToPoint(lat1, lon1, lat2, lon2);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
