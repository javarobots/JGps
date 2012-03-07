
package gps.nmea;

import gps.data.GpsDataModel;

/**
 *
 * @author javarobots
 */
public class SentenceParser {

    private GpsDataModel mDataModel;
    private boolean mBeginOfSentence = false;

    public SentenceParser(GpsDataModel model){
        mDataModel = model;
    }

    public String processDataString(String data){
        if (!mBeginOfSentence){
            int indexOfStart = data.indexOf("$");
            if (indexOfStart != -1){
                data = data.substring(indexOfStart);
                mBeginOfSentence = true;
            }
        }
        return data;
    }

    public synchronized void parseSentence(String sentence) {
        sentence = sentence.trim();
        String[] splitMessage = sentence.split(",");
        switch (splitMessage[0]) {
            case "$GPGSA":
                parseGsa(sentence);
                break;
            case "$GPRMC":
                parseRmc(sentence);
                break;
            case "$GPGGA":
                parseGga(sentence);
                break;
            case "$GPGLL":
                break;
            case "$GPGSV":
                break;
            case "$GPVTG":
                break;
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
            mDataModel.setLatitude(Double.parseDouble(latitude));
            mDataModel.setLatitudeHemisphere(values[3]);

            //Set longitude
            String longitude = values[4];
            mDataModel.setLongitude(Double.parseDouble(longitude));
            mDataModel.setLongitudeHemispere(values[5]);

            //Set fix quality
            String fixQuality = values[6];
            mDataModel.setFixQuality(Integer.parseInt(fixQuality));

            //Set altitude
            String altitude = values[9];
            mDataModel.setAltitude(Double.parseDouble(altitude));

            //Set number of tracked satellites
            String numberOfSatellites = values[7];
            mDataModel.setmNumberOfSatelites(Integer.parseInt(numberOfSatellites));
        }
    }

    /**
     * Parse the $GPRMC message
     * @param message
     */
    private void parseRmc(String message){
        String[] values = message.split(",");
        if (values.length == 13){

            //Set ground speed
            String groundSpeed = values[7];
            mDataModel.setSpeedOverGround(Double.parseDouble(groundSpeed));

            //Set course over ground
            String course = values[8];
            mDataModel.setTrueCourse(Double.parseDouble(course));
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
            mDataModel.setPdop(Double.parseDouble(pdop));

            //Set HDOP
            String hdop = values[16];
            mDataModel.setHdop(Double.parseDouble(hdop));

            //Set VDOP
            String vdop = values[17];
            String[] vdopSplit = vdop.split("\\*");
            vdop = vdopSplit[0];
            mDataModel.setVdop(Double.parseDouble(vdop));

            //Set fix mode
            String fixMode = values[2];
            mDataModel.setFixMode(Integer.parseInt(fixMode));
        }
    }

}
