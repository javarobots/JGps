
package gps.data;

import java.util.Observable;

/**
 * An observable model containing parsed GPS data
 * @author javarobots
 */
public class GpsDataModel extends Observable {

    //From $GPGGA sentence
    private UTCTime mGGATime;
    private Coordinate mGGACoordinate;
    private int mGGAFixQuality = 0; //0=Invalid 1=GPS 2=DGPS
    private double mGGAAltitude = 0; //Altitude in meters
    private int mGGANumberOfSatelites = 0; //Number of satellites being tracked
    private double mGGAHdop = 99;
    private double mGGAHeightAboveSeaLevel = 0;
    private double mGGAGeoidalHeight = 0;

    //From $GPRMC sentence
    private UTCTime mRMCTime;
    private String mRMCStatus = "V";
    private Coordinate mRMCCoordinate;
    private double mRMCSpeedOverGround = 0; //Speed in knots
    private double mRMCCourseOverGround = 0; //Heading over ground
    private UTCDate mRMCDate;
    private double mRMCMagneticVariation;
    private String mRMCMagneticVariationDirection;
    private String mRMCModeIndicator = "N";

    //From $GPGSA sentence
    private String mGSAMode = "M";
    private int mGSAFixType = 1; //1=No Fix 2=2D 3=3D
    private int[] mGSAPRNNumber = {0,0,0,0,0,0,0,0,0,0,0,0};
    private double mGSAPdop = 99;
    private double mGSAHdop = 99;
    private double mGSAVdop = 99;
    
    //---------------------- GGA Getter Setters ----------------------------

    public double getGgaAltitude() {
        return mGGAAltitude;
    }

    public void setGgaAltitude(double altitude) {
        this.mGGAAltitude = altitude;
        setChanged();
    }

    public int getGgaFixQuality() {
        return mGGAFixQuality;
    }

    public void setGgaFixQuality(int fixQuality) {
        this.mGGAFixQuality = fixQuality;
        setChanged();
    }
    
    //---------------------- GSA Getter Setter -----------------------------    

    public int getGsaFixMode() {
        return mGSAFixType;
    }

    public void setGsaFixMode(int fixMode) {
        this.mGSAFixType = fixMode;
        setChanged();
    }

    public double getGsaHdop() {
        return mGSAHdop;
    }

    public void setGsaHdop(double hdop) {
        this.mGSAHdop = hdop;
        setChanged();
    }

    public double getGsaPdop() {
        return mGSAPdop;
    }

    public void setGsaPdop(double pdop) {
        this.mGSAPdop = pdop;
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
    
    //---------------------- RMC Getter Setter -----------------------------

    public double getRmcSpeedOverGround() {
        return mRMCSpeedOverGround;
    }

    public void setRmcSpeedOverGround(double speedOverGround) {
        this.mRMCSpeedOverGround = speedOverGround;
        setChanged();
    }

    public double getRmcTrueCourse() {
        return mRMCCourseOverGround;
    }

    public void setRmcTrueCourse(double trueCourse) {
        this.mRMCCourseOverGround = trueCourse;
        setChanged();
    }
}
