package gps.calculations;

import java.text.DecimalFormat;

/**
 * Calculations given latitude and longitude
 * values to determine heading and distance
 * between  points
 * @author javarobots
 */
public class NavigationCalculations {

    private static NavigationCalculations instance;
    private DecimalFormat numberFormatter = new DecimalFormat("#.###");

    private NavigationCalculations(){

    }

    public static NavigationCalculations getInstance(){
        if (instance == null){
            instance = new NavigationCalculations();
        }
        return instance;
    }

    /**
     * Convert a latitude or longitude value in
     * "dd mm.mmm" format to a single degree value.
     * @param degreesMinutes - the String value in "dd mm.mmm" format
     * @return the degree value
     */
    public double degreesMinutesToDegrees(String degreesMinutes){
        String[] splitValue = degreesMinutes.split(" ");
        double degrees = Double.parseDouble(splitValue[0]);
        double minutes = Double.parseDouble(splitValue[1]);
        minutes = minutes / 60;
        degrees = degrees + minutes;
        return degrees;
    }

    /**
     * Convert degrees and minutes to a radian value
     * @param degrees - the degree value
     * @param minutes - the minute value
     * @return radian value
     */
    public double degreesMinutesToRad(double degrees, double minutes){
        degrees = Math.abs(degrees);
        double partialDegree = minutes / 60;
        degrees = degrees + partialDegree;
        return (Math.PI / 180) * degrees;
    }

    /**
     * Convert degree to radian value
     * @param degrees - the degree value
     * @return
     */
    public double degreesToRad(double degrees){
        degrees = Math.abs(degrees);
        return (Math.PI / 180) * degrees;
    }

    /**
     * Convert radians to degrees
     * @param rads - radian angle
     * @return the degree conversion
     */
    public double radToDegree(double rads){
        return (180 / Math.PI) * rads;
    }

    /**
     * Calculate the distance in feet between
     * two points defined by there latitude and longitude
     * @param lat1 - latitude 1 in radians
     * @param lon1 - longitude 1 in radians
     * @param lat2 - latitude 2 in radians
     * @param lon2 - longitude 2 in radians
     * @return the distance in feet between the two coordinates
     */
    public double distanceInFeet(double lat1, double lon1, double lat2, double lon2){
        double radianDistance = 2 * Math.asin(Math.sqrt((Math.sin((lat1 - lat2) / 2)) * (Math.sin((lat1 - lat2) / 2)) +
                Math.cos(lat1) * Math.cos(lat2) * (Math.sin((lon1 - lon2) / 2) * (Math.sin((lon1 - lon2) / 2)))));
        double nmDistance = (10800 / Math.PI) * radianDistance;
        double statuteDistance = nmDistance * 1.150779;
        return Double.parseDouble(numberFormatter.format(statuteDistance * 5280));
    }

    /**
     * Calculate the heading from coordinate 1 to coordinate 2
     * @param lat1 - latitude 1 in radians
     * @param lon1 - longitude 1 in radians
     * @param lat2 - latitude 2 in radians
     * @param lon2 - longitude 2 in radians
     * @return the heading to destination point 2
     */
    public double headingToPoint(double lat1, double lon1, double lat2, double lon2){
        double directionToCoord;
        if (Math.sin(lon2 - lon1) < 0){
            directionToCoord = Math.acos((Math.sin(lat2) - Math.sin(lat1) * Math.cos(distanceInRadians(lat1, lon1, lat2, lon2))) /
                    (Math.sin(distanceInRadians(lat1, lon1, lat2, lon2)) * Math.cos(lat1)));
        }
        else {
             directionToCoord = (2 * Math.PI) - Math.acos((Math.sin(lat2) - Math.sin(lat1) * Math.cos(distanceInRadians(lat1, lon1, lat2, lon2))) /
                     (Math.sin(distanceInRadians(lat1, lon1, lat2, lon2)) * Math.cos(lat1)));
        }
        return radToDegree(directionToCoord);
    }

    /**
     * Calculate the distance in radians between
     * two points defined by their latitude and longitude
     * @param lat1 - latitude 1 in radians
     * @param lon1 - longitude 1 in radians
     * @param lat2 - latitude 2 in radians
     * @param lon2 - longitude 2 in radians
     * @return the distance in radians between the two coordinates
     */
    private double distanceInRadians(double lat1, double lon1, double lat2, double lon2){
        return Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2));
    }

}
