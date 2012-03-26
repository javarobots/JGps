
package gps.nmea;

import gps.data.GpsDataModel;

/**
 *
 * @author javarobots
 */
public class SentenceParser {

    private GpsDataModel mDataModel;
    private boolean mBeginOfSentence = false;
    private StringBuilder mCompleteSentence;

    public SentenceParser(GpsDataModel model){
        mDataModel = model;
    }

    public String[] processData(String data){
        if (!mBeginOfSentence){
            mCompleteSentence = new StringBuilder();
            int indexOfStart = data.indexOf("$");
            if (indexOfStart != -1){
                data = data.substring(indexOfStart);
                mBeginOfSentence = true;
            }
        }
        //Parse out the sentences on $
        String[] sentences = data.split("[$]");
        for (String sentence : sentences){
            if (!sentence.isEmpty()){
                parseSentence(sentence);
            }
        }
        return sentences;
    }

    public void parseSentence(String sentence) {
        sentence = sentence.trim();
        String[] splitMessage = sentence.split(",");
        if (splitMessage[0].equals("GPGSA")){
            parseGsa(sentence);
        } else if (splitMessage[0].equals("GPRMC")){
            parseRmc(sentence);
        } else if (splitMessage[0].equals("GPGGA")){
            parseGga(sentence);
        } else if (splitMessage[0].equals("GPGLL")){

        } else if (splitMessage[0].equals("GPGSV")){

        } else if (splitMessage[0].equals("GPVTG")){
            parseVtg(sentence);
        }
    }

    /**
     * Parse the $GPGGA message
     * @param message
     */
    private void parseGga(String message){
        String[] values = message.split(",");

        //Ensure message is proper length
        if (values.length == 15){

            //Set Latitude
            String latitude = values[2];
            if (!latitude.isEmpty()){
                mDataModel.setGgaLatitude(Double.parseDouble(latitude));
                mDataModel.setGgaLatitudeHemisphere(values[3]);
            }

            //Set longitude
            String longitude = values[4];
            if (!longitude.isEmpty()){
                mDataModel.setGgaLongitude(Double.parseDouble(longitude));
                mDataModel.setGgaLongitudeHemispere(values[5]);
            }

            //Set fix quality
            String fixQuality = values[6];
            if (!fixQuality.isEmpty()){
                mDataModel.setGgaFixQuality(Integer.parseInt(fixQuality));
            }

            //Set altitude
            String altitude = values[9];
            if (!altitude.isEmpty()){
                mDataModel.setGgaAltitude(Double.parseDouble(altitude));
            }

            //Set number of tracked satellites
            String numberOfSatellites = values[7];
            if (!numberOfSatellites.isEmpty()){
                mDataModel.setGsaNumberOfSatelites(Integer.parseInt(numberOfSatellites));
            }

            mDataModel.notifyObservers();
        }
    }

    /**
     * Parse the $GPRMC message
     * @param message
     */
    private void parseRmc(String message){
        String[] values = message.split(",");
        if (values.length == 13){            

            //Set course over ground
            String course = values[8];
            if (!course.isEmpty()){
                mDataModel.setRmcTrueCourse(Double.parseDouble(course));
            }
        }
    }

    /**
     * Parse the $GPGSA message
     * @param message
     */
    private void parseGsa(String message) {
        String[] values = message.split(",");
        if (values.length == 18){

            //Set PDOP
            String pdop = values[15];
            if (!pdop.isEmpty()){
                mDataModel.setGsaPdop(Double.parseDouble(pdop));
            }

            //Set HDOP
            String hdop = values[16];
            if (!hdop.isEmpty()){
                mDataModel.setGsaHdop(Double.parseDouble(hdop));
            }

            //Set VDOP
            String vdop = values[17];
            String[] vdopSplit = vdop.split("\\*");
            vdop = vdopSplit[0];
            if (!vdop.isEmpty()){
                mDataModel.setGsaVdop(Double.parseDouble(vdop));
            }

            //Set fix mode
            String fixMode = values[2];
            if (!fixMode.isEmpty()){
                mDataModel.setGsaFixMode(Integer.parseInt(fixMode));
            }
        }
    }

    private void parseVtg(String message) {
        String[] values = message.split(",");
        if (values.length == 9){
            
            //Set ground speed
            String groundSpeed = values[7];
            if (!groundSpeed.isEmpty()){
                mDataModel.setRmcSpeedOverGround(Double.parseDouble(groundSpeed));
            }
        }
    }

}
