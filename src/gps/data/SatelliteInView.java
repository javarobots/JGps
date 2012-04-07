
package gps.data;

/**
 *
 * @author javarobots
 */
public class SatelliteInView {

    private int mPrnNumber;
    private int mElevation;
    private int mAzimuth;
    private int mCarrierToNoiseRatio;

    public SatelliteInView(int prn, int elevation, int azimuth, int carrierToNoise){
        mPrnNumber = prn;
        mElevation = elevation;
        mAzimuth = azimuth;
        mCarrierToNoiseRatio = carrierToNoise;
    }

    public int getAzimuth() {
        return mAzimuth;
    }

    public int getCarrierToNoiseRatio() {
        return mCarrierToNoiseRatio;
    }

    public int getElevation() {
        return mElevation;
    }

    public int getPrnNumber() {
        return mPrnNumber;
    }



}
