package gps.coordinate;

/**
 * Represents a single GPS coordinate
 * @author javarobots
 */
public class Coordinate {

    private String longitide;
    private String latitiude;

    public Coordinate(String latitude, String longitude){
        this.latitiude = latitude;
        this.longitide = longitude;
    }

    public String getLatitiude() {
        return latitiude;
    }

    public String getLongitide() {
        return longitide;
    }

}
