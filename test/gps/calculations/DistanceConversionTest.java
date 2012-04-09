package gps.calculations;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author javarobots74
 */
public class DistanceConversionTest {

    public DistanceConversionTest() {
    }

    /**
     * Test of metersToFeet method, of class DistanceConversion.
     */
    @Test
    public void testMetersToFeet() {
        System.out.println("Test metersToFeet()");
        double meters = 10.0;
        double expResult = 32.808399;
        double result = DistanceConversion.metersToFeet(meters);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of feetToMeters method, of class DistanceConversion.
     */
    @Test
    public void testFeetToMeters() {
        System.out.println("Test feetToMeters()");
        double feet = 32.808399;
        double expResult = 10.0;
        double result = DistanceConversion.feetToMeters(feet);
        assertEquals(expResult, result, 0.0);
    }
}
