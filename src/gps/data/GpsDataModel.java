
package gps.data;

import java.util.Observable;

/**
 * An observable model containing parsed GPS data
 * @author javarobots
 */
public class GpsDataModel extends Observable {

    //From $GPGGA sentence
    private double mGGALatitude = 00.000;
    private double mGGALongitude = 000.000;
    private String mGGALatitudeHemisphere = "N"; //N or S
    private String mGGALongitudeHemispere = "W"; //E or W
    private int mGGAFixQuality = 0; //0=Invalid 1=GPS 2=DGPS
    private double mGGAAltitude = 0; //Altitude in meters
    private int mGGANumberOfSatelites = 0; //Number of satellites being tracked

    //From $GPRMC sentence
    private double mRMCSpeedOverGround = 0; //Speed in knots
    private double mRMCTrueCourse = 0; //Heading over ground

    //From $GPGSA sentence
    private int mGSAFixMode = 1; //1=No Fix 2=2D 3=3D
    private double mGSAPdop = 99;
    private double mGSAHdop = 99;
    private double mGSAVdop = 99;

    public double getGgaAltitude() {
        return mGGAAltitude;
    }

    public void setGgaAltitude(double altitude) {
        this.mGGAAltitude = altitude;
        setChanged();
    }

    public int getGsaFixMode() {
        return mGSAFixMode;
    }

    public void setGsaFixMode(int fixMode) {
        this.mGSAFixMode = fixMode;
        setChanged();
    }

    public int getGgaFixQuality() {
        return mGGAFixQuality;
    }

    public void setGgaFixQuality(int fixQuality) {
        this.mGGAFixQuality = fixQuality;
        setChanged();
    }

    public double getGsaHdop() {
        return mGSAHdop;
    }

    public void setGsaHdop(double hdop) {
        this.mGSAHdop = hdop;
        setChanged();
    }

    public double getGgaLatitude() {
        return mGGALatitude;
    }

    public void setGgaLatitude(double latitude) {
        this.mGGALatitude = latitude;
        setChanged();
    }

    public String getGgaLatitudeHemisphere() {
        return mGGALatitudeHemisphere;
    }

    public void setGgaLatitudeHemisphere(String latitudeHemisphere) {
        this.mGGALatitudeHemisphere = latitudeHemisphere;
        setChanged();
    }

    public double getGgaLongitude() {
        return mGGALongitude;
    }

    public void setGgaLongitude(double longitude) {
        this.mGGALongitude = longitude;
        setChanged();
    }

    public String getGgaLongitudeHemispere() {
        return mGGALongitudeHemispere;
    }

    public void setGgaLongitudeHemispere(String longitudeHemispere) {
        this.mGGALongitudeHemispere = longitudeHemispere;
        setChanged();
    }

    public double getGsaPdop() {
        return mGSAPdop;
    }

    public void setGsaPdop(double pdop) {
        this.mGSAPdop = pdop;
        setChanged();
    }

    public double getRmcSpeedOverGround() {
        return mRMCSpeedOverGround;
    }

    public void setRmcSpeedOverGround(double speedOverGround) {
        this.mRMCSpeedOverGround = speedOverGround;
        setChanged();
    }

    public double getRmcTrueCourse() {
        return mRMCTrueCourse;
    }

    public void setRmcTrueCourse(double trueCourse) {
        this.mRMCTrueCourse = trueCourse;
        setChanged();
    }

    public double getGsaVdop() {
        return mGSAVdop;
    }

    public void setGsaVdop(double vdop) {
        this.mGSAVdop = vdop;
        setChanged();
    }

    public int getGsaNumberOfSatelites() {
        return mGGANumberOfSatelites;
    }

    public void setGsaNumberOfSatelites(int numberOfSatelites) {
        this.mGGANumberOfSatelites = numberOfSatelites;
        setChanged();
    }


}
