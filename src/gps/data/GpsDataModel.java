
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
    private int mGGANumberOfSatelites = 0; //Number of satellites being tracked
    private double mGGAHdop = 99; //Horizontal dilution of precision
    private double mGGAHeightAboveSeaLevel = 0; //Altitude in meters
    private double mGGAGeoidalHeight = 0; //Geoidal height in meters

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

    public double getGGAHeightAboveSeaLevel() {
        return mGGAHeightAboveSeaLevel;
    }

    public void setGGAHeightAboveSeaLevel(double heightAboveSeaLevel) {
        this.mGGAHeightAboveSeaLevel = heightAboveSeaLevel;
        setChanged();
    }
    
    public int getGgaFixQuality() {
        return mGGAFixQuality;
    }

    public void setGgaFixQuality(int fixQuality) {
        this.mGGAFixQuality = fixQuality;
        setChanged();
    }

    public Coordinate getGGACoordinate() {
        return mGGACoordinate;
    }

    public void setGGACoordinate(Coordinate coordinate) {
        this.mGGACoordinate = coordinate;
        setChanged();
    }

    public double getGGAGeoidalHeight() {
        return mGGAGeoidalHeight;
    }

    public void setGGAGeoidalHeight(double geoidalHeight) {
        this.mGGAGeoidalHeight = geoidalHeight;
        setChanged();
    }

    public double getGGAHdop() {
        return mGGAHdop;
    }

    public void setGGAHdop(double hdop) {
        this.mGGAHdop = hdop;
        setChanged();
    }

    public UTCTime getGGATime() {
        return mGGATime;
    }

    public void setGGATime(UTCTime time) {
        this.mGGATime = time;
        setChanged();
    }
    
    public int getGGANumberOfSatelites() {
        return mGGANumberOfSatelites;
    }

    public void setGGANumberOfSatelites(int numberOfSatelites) {
        this.mGGANumberOfSatelites = numberOfSatelites;
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

    public String getmGSAMode() {
        return mGSAMode;
    }

    public void setmGSAMode(String mode) {
        this.mGSAMode = mode;
        setChanged();
    }

    public int[] getmGSAPRNNumber() {
        return mGSAPRNNumber;
    }

    public void setmGSAPRNNumber(int[] prnNumber) {
        this.mGSAPRNNumber = prnNumber;
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

    public Coordinate getRMCCoordinate() {
        return mRMCCoordinate;
    }

    public void setRMCCoordinate(Coordinate coordinate) {
        this.mRMCCoordinate = coordinate;
        setChanged();
    }

    public UTCDate getRMCDate() {
        return mRMCDate;
    }

    public void setRMCDate(UTCDate date) {
        this.mRMCDate = date;
        setChanged();
    }

    public double getRMCMagneticVariation() {
        return mRMCMagneticVariation;
    }

    public void setRMCMagneticVariation(double magneticVariation) {
        this.mRMCMagneticVariation = magneticVariation;
        setChanged();
    }

    public String getRMCMagneticVariationDirection() {
        return mRMCMagneticVariationDirection;
    }

    public void setRMCMagneticVariationDirection(String magneticVariationDirection) {
        this.mRMCMagneticVariationDirection = magneticVariationDirection;
        setChanged();
    }

    public String getRMCModeIndicator() {
        return mRMCModeIndicator;
    }

    public void setRMCModeIndicator(String modeIndicator) {
        this.mRMCModeIndicator = modeIndicator;
        setChanged();
    }

    public String getRMCStatus() {
        return mRMCStatus;
    }

    public void setRMCStatus(String status) {
        this.mRMCStatus = status;
        setChanged();
    }

    public UTCTime getRMCTime() {
        return mRMCTime;
    }

    public void setRMCTime(UTCTime time) {
        this.mRMCTime = time;
        setChanged();
    }
    
    
}
