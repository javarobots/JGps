
package gps.data;

import java.util.Observable;

/**
 * An observable model containing parsed GPS data
 * @author javarobots
 */
public class GpsDataModel extends Observable {

    //From $GPGGA sentence
    private double mLatitude = 00.000;
    private double mLongitude = 000.000;
    private String mLatitudeHemisphere = "N"; //N or S
    private String mLongitudeHemispere = "W"; //E or W
    private int mFixQuality = 0; //0=Invalid 1=GPS 2=DGPS
    private double mAltitude = 0; //Altitude in meters
    private int mNumberOfSatelites = 0; //Number of satellites being tracked

    //From $GPRMC sentence
    private double mSpeedOverGround = 0; //Speed in knots
    private double mTrueCourse = 0; //Heading over ground

    //From $GPGSA sentence
    private int mFixMode = 1; //1=No Fix 2=2D 3=3D
    private double mPdop = 99;
    private double mHdop = 99;
    private double mVdop = 99;

    public double getAltitude() {
        return mAltitude;
    }

    public void setAltitude(double altitude) {
        this.mAltitude = altitude;
        setChanged();
    }

    public int getFixMode() {
        return mFixMode;
    }

    public void setFixMode(int fixMode) {
        this.mFixMode = fixMode;
        setChanged();
    }

    public int getFixQuality() {
        return mFixQuality;
    }

    public void setFixQuality(int fixQuality) {
        this.mFixQuality = fixQuality;
        setChanged();
    }

    public double getHdop() {
        return mHdop;
    }

    public void setHdop(double hdop) {
        this.mHdop = hdop;
        setChanged();
    }

    public double getLatitude() {
        return mLatitude;
    }

    public void setLatitude(double latitude) {
        this.mLatitude = latitude;
        setChanged();
    }

    public String getLatitudeHemisphere() {
        return mLatitudeHemisphere;
    }

    public void setLatitudeHemisphere(String latitudeHemisphere) {
        this.mLatitudeHemisphere = latitudeHemisphere;
        setChanged();
    }

    public double getLongitude() {
        return mLongitude;
    }

    public void setLongitude(double longitude) {
        this.mLongitude = longitude;
        setChanged();
    }

    public String getLongitudeHemispere() {
        return mLongitudeHemispere;
    }

    public void setLongitudeHemispere(String longitudeHemispere) {
        this.mLongitudeHemispere = longitudeHemispere;
        setChanged();
    }

    public double getPdop() {
        return mPdop;
    }

    public void setPdop(double pdop) {
        this.mPdop = pdop;
        setChanged();
    }

    public double getSpeedOverGround() {
        return mSpeedOverGround;
    }

    public void setSpeedOverGround(double speedOverGround) {
        this.mSpeedOverGround = speedOverGround;
        setChanged();
    }

    public double getTrueCourse() {
        return mTrueCourse;
    }

    public void setTrueCourse(double trueCourse) {
        this.mTrueCourse = trueCourse;
        setChanged();
    }

    public double getVdop() {
        return mVdop;
    }

    public void setVdop(double vdop) {
        this.mVdop = vdop;
        setChanged();
    }

    public int getmNumberOfSatelites() {
        return mNumberOfSatelites;
    }

    public void setmNumberOfSatelites(int numberOfSatelites) {
        this.mNumberOfSatelites = numberOfSatelites;
        setChanged();
    }


}
