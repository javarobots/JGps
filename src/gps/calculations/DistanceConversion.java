package gps.calculations;

/**
 * Convert distances of various measurements
 * @author javarobots
 */
public class DistanceConversion {

    /**
     * Convert meters to feet
     * @param meters
     * @return
     */
    public static double metersToFeet(double meters){
        return meters * 3.2808399;
    }

    /**
     * Convert feet to meters
     * @param feet
     * @return
     */
    public static double feetToMeters(double feet){
        return feet / 3.2808399;
    }

}
