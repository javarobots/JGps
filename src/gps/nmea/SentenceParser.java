
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

        System.out.println(data);

        //Parse out the sentences on $
        String[] sentences = data.split("[$]");
        for (String sentence : sentences){
            if (!sentence.isEmpty()){
                parseSentence(sentence);
            }
        }
        return sentences;
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
            if (!latitude.isEmpty()){
                mDataModel.setLatitude(Double.parseDouble(latitude));
                mDataModel.setLatitudeHemisphere(values[3]);
            }

            //Set longitude
            String longitude = values[4];
            if (!longitude.isEmpty()){
                mDataModel.setLongitude(Double.parseDouble(longitude));
                mDataModel.setLongitudeHemispere(values[5]);
            }

            //Set fix quality
            String fixQuality = values[6];
            if (!fixQuality.isEmpty()){
                mDataModel.setFixQuality(Integer.parseInt(fixQuality));
            }

            //Set altitude
            String altitude = values[9];
            if (!altitude.isEmpty()){
                mDataModel.setAltitude(Double.parseDouble(altitude));
            }

            //Set number of tracked satellites
            String numberOfSatellites = values[7];
            if (!numberOfSatellites.isEmpty()){
                mDataModel.setmNumberOfSatelites(Integer.parseInt(numberOfSatellites));
            }
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
            if (!groundSpeed.isEmpty()){
                mDataModel.setSpeedOverGround(Double.parseDouble(groundSpeed));
            }

            //Set course over ground
            String course = values[8];
            if (!course.isEmpty()){
                mDataModel.setTrueCourse(Double.parseDouble(course));
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
                mDataModel.setPdop(Double.parseDouble(pdop));
            }

            //Set HDOP
            String hdop = values[16];
            if (!hdop.isEmpty()){
                mDataModel.setHdop(Double.parseDouble(hdop));
            }

            //Set VDOP
            String vdop = values[17];
            String[] vdopSplit = vdop.split("\\*");
            vdop = vdopSplit[0];
            if (!vdop.isEmpty()){
                mDataModel.setVdop(Double.parseDouble(vdop));
            }

            //Set fix mode
            String fixMode = values[2];
            if (!fixMode.isEmpty()){
                mDataModel.setFixMode(Integer.parseInt(fixMode));
            }
        }
    }

}
