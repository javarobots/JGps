
package gps.nmea;

import gps.data.*;
import gps.debug.Debug;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author javarobots
 */
public class SentenceParser {

    private GpsDataModel mDataModel;
    private boolean mBeginOfSentence = false;
    private int mNumberOfGSVSentences = 0;
    private int mCurrentGSVSentence = 0;
    private int mNumberParsedSatellites = 0;
    private List<SatelliteInView> mSatellitesInView;

    public SentenceParser(GpsDataModel model){
        mDataModel = model;
    }

    public String[] processData(String data){
        String[] sentences = null;
        if (data.contains("$")){
            //Trim any data leading $
            if (data.indexOf("$") > 0){
                data = data.substring(data.indexOf("$"));
            }

            //Parse out the sentences on $
            sentences = data.split("[$]");
            for (String sentence : sentences){
                if (!sentence.isEmpty()){
                    parseSentence("$" + sentence);
                }
            }
        }
        return sentences;
    }

    @SuppressWarnings("ConvertToStringSwitch")
    public boolean parseSentence(String sentence) {
        boolean sentenceParsed = false;
        sentence = sentence.trim();
        String[] splitMessage = sentence.split(",");
        if (splitMessage[0].equals("$GPGSA")){
            if (mDataModel.getSelectedSentences().isParseGSA()){
                Debug.debugOut("GSA message found");
                parseGsa(sentence);
                sentenceParsed = true;
            }
        } else if (splitMessage[0].equals("$GPRMC")){
            if (mDataModel.getSelectedSentences().isParseRMC()){
                Debug.debugOut("RMC message found");
                parseRmc(sentence);
                sentenceParsed = true;
            }
        } else if (splitMessage[0].equals("$GPGGA")){
            if (mDataModel.getSelectedSentences().isParseGGA()){
                Debug.debugOut("GGA message found");
                parseGga(sentence);
                sentenceParsed = true;
            }
        } else if (splitMessage[0].equals("$GPGLL")){
            if (mDataModel.getSelectedSentences().isParseGLL()){
                Debug.debugOut("GLL message found");
                parseGll(sentence);
                sentenceParsed = true;
            }
        } else if (splitMessage[0].equals("$GPGSV")){
            if (mDataModel.getSelectedSentences().isParseGSV()){
                Debug.debugOut("GSV message found");
                parseGsv(sentence);
                sentenceParsed = true;
            }
        } else if (splitMessage[0].equals("$GPVTG")){
            if (mDataModel.getSelectedSentences().isParseVTG()){
                Debug.debugOut("VTG message found");
                parseVtg(sentence);
                sentenceParsed = true;
            }
        }
        return sentenceParsed;
    }

    /**
     * Parse the $GPGGA message
     * @param message
     */
    private void parseGga(String message){
        Checksum checksum = new Checksum(message);
        if (checksum.isValid()){
            Debug.debugOut("GGA Checksum Passed");
            String[] values = message.split(",");

            //Set GGA UTC time
            UTCTime time = parseUtcTime(values[1]);
            mDataModel.setGGATime(time);
            Debug.debugOut("UTC");

            //Set GGA Coordinate
            Coordinate ggaCoordinate = parseCoordinate(values[2], values[3], values[4], values[5]);
            mDataModel.setGGACoordinate(ggaCoordinate);
            Debug.debugOut("Coordinate");

            //Set fix quality
            String fixQuality = values[6];
            if (!fixQuality.isEmpty()){
                mDataModel.setGgaFixQuality(Integer.parseInt(fixQuality));
            }
            Debug.debugOut("Fix");

            //Set number of stellites in use
            String numberOfSatelites = values[7];
            if (!numberOfSatelites.isEmpty()){
                mDataModel.setGGANumberOfSatelites(Integer.parseInt(numberOfSatelites));
            }
            Debug.debugOut("Num Sat");

            //Set HDOP
            String hdop = values[8];
            if (!hdop.isEmpty()){
                mDataModel.setGGAHdop(Double.parseDouble(hdop));
            }
            Debug.debugOut("HDOP");

            //Set altitude
            String altitude = values[9];
            if (!altitude.isEmpty()){
                mDataModel.setGGAHeightAboveSeaLevel(Double.parseDouble(altitude));
            }
            Debug.debugOut("Altitude");

            //!jdp - commented to see if this was cause of error
            //Set Geoidal height
//            String geodialHeight = values[11];
//            if (!geodialHeight.isEmpty()){
//                try{
//                    mDataModel.setGGAGeoidalHeight(Double.parseDouble(geodialHeight));
//                } catch (NumberFormatException ex){
//                    //Do nothing
//                }
//            }

            Debug.debugOut("GGA message parsed");
            mDataModel.setLogCoordinate(true);
            mDataModel.notifyObservers();
        }
    }

    /**
     * Parse the $GPRMC message
     * @param message
     */
    private void parseRmc(String message){
        Checksum checksum = new Checksum(message);
        if (checksum.isValid()){
            String[] values = message.split(",");

            //Set UTC Time
            String utcTime = values[1];
            if (!utcTime.isEmpty()){
                UTCTime time = parseUtcTime(utcTime);
                mDataModel.setRMCTime(time);
            }

            //Set status
            String status = values[2];
            if (!status.isEmpty()){
                mDataModel.setRMCStatus(status);
            }

            //Set coordinate
            Coordinate coordinate = parseCoordinate(values[3], values[4], values[5], values[6]);
            mDataModel.setRMCCoordinate(coordinate);

            //Set speed over ground
            String speed = values[7];
            if (!speed.isEmpty()){
                mDataModel.setRmcSpeedOverGround(Double.parseDouble(speed));
            }

            //Set course over ground
            String course = values[8];
            if (!course.isEmpty()){
                mDataModel.setRmcTrueCourse(Double.parseDouble(course));
            }

            //Set the UTC date
            String date = values[9];
            if (!date.isEmpty()){
                mDataModel.setRMCDate(parseUtcDate(date));
            }

            //Set magnetic variation
            String variation = values[10];
            if (!variation.isEmpty()){
                mDataModel.setRMCMagneticVariation(Double.parseDouble(variation));
            }

            //Set magentic variation direcion
            String varDirection = values[11];
            if (!varDirection.isEmpty()){
                mDataModel.setRMCMagneticVariationDirection(parseFromChecksum(varDirection));
            }

            //Set mode indicator
            if (values.length == 13){
                String mode = values[12];
                if (!mode.isEmpty()){
                    mDataModel.setRMCModeIndicator(parseFromChecksum(mode));
                }
            }

            Debug.debugOut("RMC message parsed");
            mDataModel.notifyObservers();

        }
    }

    /**
     * Parse the $GPGSA message
     * @param message
     */
    private void parseGsa(String message) {
        String[] values = message.split(",");
        if (values.length == 18){
            //Set fix mode
            String fixMode = values[1];
            mDataModel.setmGSAMode(fixMode);

            //Set fix type
            String fixType = values[2];
            if (!fixType.isEmpty()){
                mDataModel.setGsaFixType(Integer.parseInt(fixType));
            }

            //Set PRN numbers
            int[] prnArray = new int[12];
            for (int i = 3; i < 15; i++){
                if (!values[i].isEmpty()){
                    prnArray[i-3] = Integer.parseInt(values[i]);
                }
            }
            mDataModel.setmGSAPRNNumber(prnArray);

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
            if (!vdop.isEmpty()){
                mDataModel.setGsaVdop(Double.parseDouble(parseFromChecksum(vdop)));
            }

            Debug.debugOut("GSA message parsed");
            mDataModel.notifyObservers();

        }
    }

    private void parseVtg(String message) {
        String[] values = message.split(",");
        if (values.length == 9){
            //Set true course over ground
            String trueCourse = values[1];
            if (!trueCourse.isEmpty()){
                mDataModel.setVTGTrueCourse(Double.parseDouble(trueCourse));
            }

            //Set magnetic course
            String magneticCourse = values[3];
            if (!magneticCourse.isEmpty()){
                mDataModel.setVTGMagneticCourse(Double.parseDouble(magneticCourse));
            }

            //Set ground speed in knots
            String speedInKnots = values[5];
            if (!speedInKnots.isEmpty()){
                mDataModel.setVTGSpeedInKnots(Double.parseDouble(speedInKnots));
            }

            //Set ground speed in KPH
            String speedInKPH = values[7];
            if (!speedInKPH.isEmpty()){
                mDataModel.setVTGSpeedInKilometers(Double.parseDouble(speedInKPH));
            }

            //!jdp - the mode indicator value is not present from etrx legend
            //Set mode indicator
//            String modeIndicator = values[9];
//            if (!modeIndicator.isEmpty()){
//                String[] splitMode = modeIndicator.split("*");
//                mDataModel.setVTGModeIndicator(splitMode[0]);
//            }

            Debug.debugOut("VTG message parsed");
            mDataModel.notifyObservers();

        }
    }

    private void parseGll(String message){
        String[] values = message.split(",");

        //Ensure proper length
        if (values.length == 7){

            //Set coordinate
            Coordinate coordinate = parseCoordinate(values[1], values[2], values[3], values[4]);
            mDataModel.setGLLCoordinate(coordinate);

            //Set UTC time
            UTCTime time = parseUtcTime(values[5]);
            mDataModel.setGLLTime(time);

            //Set status
            String status = values[6];
            if (status.contains("*")){
                String[] splitStatus = status.split("\\*");
                mDataModel.setGLLStatus(splitStatus[0]);
            } else {

            }

            Debug.debugOut("GLL message parsed");
            mDataModel.notifyObservers();

        }
    }

    private UTCTime parseUtcTime(String utcTime){
        try {
            Debug.debugOut("UTC: " + utcTime);
            String[] splitTime = utcTime.split("[.]");
            //Parse time from end of utcTime String
            String utcSecond = splitTime[0].substring(splitTime[0].length() - 2);
            String utcMinutes = splitTime[0].substring(splitTime[0].length() - 4, splitTime[0].length() - 2);
            String utcHour = splitTime[0].substring(0, splitTime[0].length() - 4);
            UTCTime time = new UTCTime(
                    Integer.parseInt(utcHour),
                    Integer.parseInt(utcMinutes),
                    Integer.parseInt(utcSecond));

            return time;
        } catch (NumberFormatException ex){
            Debug.debugOut("Number format exception in parseUTCTime");
            //Return the default UTC Time
            return new UTCTime();
        }
    }

    private UTCDate parseUtcDate(String utcDate){
        try {
            //Parse date from end
            String utcYear = utcDate.substring(utcDate.length() - 2);
            String utcMonth = utcDate.substring(utcDate.length() - 4, utcDate.length() - 2);
            String utcDay = utcDate.substring(0, utcDate.length() - 4);

            UTCDate date = new UTCDate(Integer.parseInt(utcDay), Integer.parseInt(utcMonth), Integer.parseInt(utcYear));
            return date;
        } catch (NumberFormatException ex){
            Debug.debugOut("Number format exception in parseUtcDate");
            //Return the default UTC Date
            return new UTCDate();
        }
    }

    private Coordinate parseCoordinate(
            String latitude,
            String latitudeHemisphere,
            String longitude,
            String longitudeHemisphere){
        try {
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
        } catch (NumberFormatException ex){
            Debug.debugOut("Number format exception in parseCoordinate");
            //Return the default coordinate
            return new Coordinate();
        }
    }

    //Parse values from checksum
    private String parseFromChecksum(String value){
        String[] splitVale = value.split("\\*");
        return splitVale[0];
    }

    private void parseGsv(String message) {
        Checksum checksum = new Checksum(message);
        if (checksum.isValid()){
            String[] values = message.split(",");
            mNumberOfGSVSentences = Integer.parseInt(values[1]);
            mCurrentGSVSentence++;
            int totalSatellites = Integer.parseInt(values[3]);

            //Parse message
            if (mCurrentGSVSentence == 1){
                mSatellitesInView = new ArrayList<>();
            }
            for (int i = 0; i < 4; i++){
                int prn = Integer.parseInt(values[4+(i*4)]);
                int elevation = Integer.parseInt(values[5+(i*4)]);
                int azimuth = Integer.parseInt(values[6+(i*4)]);
                int carrierToNoise = Integer.parseInt(parseFromChecksum(values[7+(i*4)]));
                SatelliteInView satInView = new SatelliteInView(prn,elevation,azimuth,carrierToNoise);
                mSatellitesInView.add(satInView);
                if (totalSatellites == mSatellitesInView.size()){
                    break;
                }
            }

            if (mCurrentGSVSentence == mNumberOfGSVSentences){
                mCurrentGSVSentence = 0;
                mDataModel.setSatellitesInView(mSatellitesInView);
            }

            mDataModel.notifyObservers();

        }
    }

}
