
package gps.nmea;

import java.util.zip.CRC32;

/**
 *
 * @author javarobots
 */
public class Checksum {

        public static void main(String[] args){

            String sentence = "$GPVTG,139.4,T,139.4,M,0.14,N,0.26,K*4F";
            int astriskIndex = sentence.indexOf("*");
            int checksumValue = 0;
            System.out.println("Checksum marker: " + astriskIndex);
            for (int i = 1; i < astriskIndex; i++){
                System.out.print(sentence.charAt(i));
                checksumValue = checksumValue ^ Character.getNumericValue(sentence.charAt(i));
            }
            System.out.println("\nValues to calculate");
            System.out.println("Checksum value: " + checksumValue);

            CRC32 crc = new CRC32();
            byte[] bytes = sentence.getBytes();
            crc.update(bytes, 1, astriskIndex - 1);
            System.out.println("CRC: " + crc.getValue());
        }

}
