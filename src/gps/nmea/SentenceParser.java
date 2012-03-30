
package gps.nmea;

import gps.data.Coordinate;
import gps.data.GpsDataModel;
import gps.data.Hemisphere;
import gps.data.UTCTime;

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
            
            //Set GGA UTC time
            int[] timeArray = parseUtcTime(values[1]);
            mDataModel.setGGATime(new UTCTime(timeArray[0], timeArray[1], timeArray[2]));            
            

            //Set GGA Coordinate            
            Coordinate ggaCoordinate = coordinateParser(values[2], values[3], values[4], values[5]);
            mDataModel.setGGACoordinate(ggaCoordinate);

            //Set fix quality
            String fixQuality = values[6];
            if (!fixQuality.isEmpty()){
                mDataModel.setGgaFixQuality(Integer.parseInt(fixQuality));
            }
            
            //Set number of stellites in use
            String numberOfSatelites = values[7];
            if (!numberOfSatelites.isEmpty()){
                mDataModel.setGGANumberOfSatelites(Integer.parseInt(numberOfSatelites));
            }
            
            //Set HDOP
            String hdop = values[8];
            if (!hdop.isEmpty()){
                mDataModel.setGGAHdop(Double.parseDouble(hdop));
            }
            //Set altitude
            String altitude = values[9];
            if (!altitude.isEmpty()){
                mDataModel.setGGAHeightAboveSeaLevel(Double.parseDouble(altitude));
            }
            
            //Set Geoidal height
            String geodialHeight = values[10];
            if (!geodialHeight.isEmpty()){
                mDataModel.setGGAGeoidalHeight(Double.parseDouble(geodialHeight));
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
    
    //!jdp -- Need to add error correction
    private int[] parseUtcTime(String utcTime){
        int[] returnArray = new int[3];
        
        //Parse time from end of utcTime String
        String utcSecond = utcTime.substring(utcTime.length() - 2);
        String utcMinutes = utcTime.substring(utcTime.length() - 4, utcTime.length() - 2);
        String utcHour = utcTime.substring(0, utcTime.length() - 4);
        returnArray[0] = Integer.parseInt(utcHour);
        returnArray[1] = Integer.parseInt(utcMinutes);
        returnArray[2] = Integer.parseInt(utcSecond);
        
        return returnArray;
    }
    
    //!jdp -- Need to add error correction
    private Coordinate coordinateParser(
            String latitude,
            String latitudeHemisphere,
            String longitude,
            String longitudeHemisphere){
            
            Hemisphere latHemisphere;
            Hemisphere lonHemisphere;
            if (latitudeHemisphere.equals("N")){
                latHemisphere = Hemisphere.NORTH;
            } else {
                latHemisphere = Hemisphere.SOUTH;
            }
            
            if (longitudeHemisphere.equals("E")){
                lonHemisphere = Hemisphere.EAST;
            } else {
                lonHemisphere = Hemisphere.WEST;
            }
            
            Coordinate coordinate = new Coordinate(
                    Double.parseDouble(latitude),
                    latHemisphere,
                    Double.parseDouble(longitude),
                    lonHemisphere);

        return coordinate;
    }

}
