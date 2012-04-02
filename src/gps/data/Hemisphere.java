package gps.data;

/**
 *
 * @author javarobots
 */
public enum Hemisphere {

    NORTH("N"),
    SOUTH("S"),
    EAST("E"),
    WEST("W");

    private String mCharacter;

    Hemisphere(String character){
        mCharacter = character;
    }

    public String getHemisphere(){
        return mCharacter;
    }

}
