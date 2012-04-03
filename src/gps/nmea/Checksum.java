
package gps.nmea;

/**
 *
 * @author javarobots
 */
public class Checksum {
    
    private String mSentence;
    
    public Checksum(String sentence){
        mSentence = sentence;
    }
    
    public boolean isValid(){
        boolean valid = true;
        byte[] bytes = mSentence.getBytes();
        int astriskIndex = mSentence.indexOf("*");
        byte checksumValue = 0;
        int checksumFromSentence;
        
        //Check to see if first character is $
        if (mSentence.charAt(0) != '$'){
            valid = false;
        }
        
        //If valid calculate checksum
        if (valid){
            String val = mSentence.substring(astriskIndex + 1, mSentence.length());
            checksumFromSentence = Integer.parseInt(val, 16);
            for (int i = 1; i < astriskIndex; i++){
                checksumValue = (byte) (checksumValue ^ bytes[i]);
            }
            if (checksumFromSentence != checksumValue){
                valid = false;
            }
        }        
        return valid;
    }

}
