package gps.nmea;

/**
 *
 * @author javarobots <javarobots74@gmail.com>
 */
public enum NmeaSentences {

    GGA("Global Positioning System Fix Data"),
    GLL("Geographic Position"),
    GSA("GPS DOP and Active Satellites"),
    GSV("GPS Satellites in View"),
    RMC("Recommended Minimum Specific GPS/Transit Data"),
    VTG("Track Made Good and Ground Speed");

    private String mDescription;

    NmeaSentences(String description){
        mDescription = description;
    }

    public String getDescription() {
        return mDescription;
    }



}
