
package gps.nmea;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author javarobots
 */
public class SelectedSentences {

    private Map<NmeaSentences,Boolean> mSelectedSentenceMap;

    public SelectedSentences(){
        //Init map and set defaults
        mSelectedSentenceMap  = new HashMap<>();
        mSelectedSentenceMap.put(NmeaSentences.GGA, false);
        mSelectedSentenceMap.put(NmeaSentences.GLL, false);
        mSelectedSentenceMap.put(NmeaSentences.GSA, false);
        mSelectedSentenceMap.put(NmeaSentences.RMC, true);
        mSelectedSentenceMap.put(NmeaSentences.VTG, false);
        mSelectedSentenceMap.put(NmeaSentences.GSV, false);
    }
    
    public boolean isParse(NmeaSentences sentence){
        boolean returnValue = false;
        if (sentence.equals(NmeaSentences.GGA)){
            returnValue = isParseGGA();
        } else if (sentence.equals(NmeaSentences.GLL)){
            returnValue = isParseGLL();
        } else if (sentence.equals(NmeaSentences.GSA)){
            returnValue =  isParseGSA();
        } else if (sentence.equals(NmeaSentences.RMC)){
            returnValue =  isParseRMC();
        } else if (sentence.equals(NmeaSentences.VTG)){
            returnValue =  isParseVTG();
        } else if (sentence.equals(NmeaSentences.GSV)){
            returnValue =  isParseGSV();
        }
        return returnValue;
    }

    public boolean isParseGGA() {
        return mSelectedSentenceMap.get(NmeaSentences.GGA);
    }

    public void setParseGGA(boolean parseGGA) {
        mSelectedSentenceMap.put(NmeaSentences.GGA, parseGGA);
    }

    public boolean isParseGLL() {
        return mSelectedSentenceMap.get(NmeaSentences.GLL);
    }

    public void setParseGLL(boolean parseGLL) {
        mSelectedSentenceMap.put(NmeaSentences.GLL, parseGLL);
    }

    public boolean isParseGSA() {
        return mSelectedSentenceMap.get(NmeaSentences.GSA);
    }

    public void setParseGSA(boolean parseGSA) {
        mSelectedSentenceMap.put(NmeaSentences.GSA, parseGSA);
    }

    public boolean isParseRMC() {
        return mSelectedSentenceMap.get(NmeaSentences.RMC);
    }

    public void setParseRMC(boolean parseRMC) {
        mSelectedSentenceMap.put(NmeaSentences.RMC, parseRMC);
    }

    public boolean isParseVTG() {
        return mSelectedSentenceMap.get(NmeaSentences.VTG);
    }

    public void setParseVTG(boolean parseVTG) {
        mSelectedSentenceMap.put(NmeaSentences.VTG, parseVTG);
    }

    public boolean isParseGSV() {
        return mSelectedSentenceMap.get(NmeaSentences.GSV);
    }

    public void setParseGSV(boolean parseGSV) {
        mSelectedSentenceMap.put(NmeaSentences.GSV, parseGSV);
    }




}
