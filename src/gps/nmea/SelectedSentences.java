
package gps.nmea;

/**
 *
 * @author javarobots
 */
public class SelectedSentences {

    private boolean mParseGGA = false;
    private boolean mParseGLL = false;
    private boolean mParseGSA = false;
    private boolean mParseRMC = true;
    private boolean mParseVTG = false;

    public boolean isParseGGA() {
        return mParseGGA;
    }

    public void setParseGGA(boolean parseGGA) {
        this.mParseGGA = parseGGA;
    }

    public boolean isParseGLL() {
        return mParseGLL;
    }

    public void setParseGLL(boolean parseGLL) {
        this.mParseGLL = parseGLL;
    }

    public boolean isParseGSA() {
        return mParseGSA;
    }

    public void setParseGSA(boolean parseGSA) {
        this.mParseGSA = parseGSA;
    }

    public boolean isParseRMC() {
        return mParseRMC;
    }

    public void setParseRMC(boolean parseRMC) {
        this.mParseRMC = parseRMC;
    }

    public boolean isParseVTG() {
        return mParseVTG;
    }

    public void setParseVTG(boolean parseVTG) {
        this.mParseVTG = parseVTG;
    }



}
