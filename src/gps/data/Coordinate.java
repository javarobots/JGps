package gps.data;

/**
 *
 * @author javarobots
 */
public class Coordinate {

    private double mLatitude;
    private Hemisphere mLatitudeHemisphere;
    private double mLongitude;
    private Hemisphere mLongitudeHemisphere;

    //A default Coordinate for initializing values.
    public Coordinate(){
        mLatitude = 30.5994;
        mLatitudeHemisphere = Hemisphere.NORTH;
        mLongitude = 87.1611;
        mLongitudeHemisphere = Hemisphere.WEST;
    }

    public Coordinate(double latitude, Hemisphere latHemisphere, double longitude, Hemisphere lonHemisphere){
        mLatitude = latitude;
        mLatitudeHemisphere = latHemisphere;
        mLongitude = longitude;
        mLongitudeHemisphere = lonHemisphere;
    }

    public double getLatitude() {
        return mLatitude;
    }

    public void setLatitude(double latitude) {
        mLatitude = latitude;
    }

    public double getLongitude() {
        return mLongitude;
    }

    public void setLongitude(double longitude) {
        mLongitude = longitude;
    }

    public Hemisphere getLatitudeHemisphere() {
        return mLatitudeHemisphere;
    }

    public void setLatitudeHemisphere(Hemisphere hemisphere) {
        mLatitudeHemisphere = hemisphere;
    }

    public Hemisphere getLongitudeHemisphere() {
        return mLongitudeHemisphere;
    }

    public void setLongitudeHemisphere(Hemisphere hemisphere) {
        mLongitudeHemisphere = hemisphere;
    }



}
