package gps.data;

/**
 *
 * @author javarobots
 */
public class UTCTime {

    private int mHour;
    private int mMinute;
    private int mSecond;

    public UTCTime(){
        mHour = 0;
        mMinute = 0;
        mSecond = 0;
    }

    public UTCTime(int hour, int minute, int second){
        mHour = hour;
        mMinute = minute;
        mSecond = second;
    }

    public int getHour() {
        return mHour;
    }

    public void setHour(int hour) {
        mHour = hour;
    }

    public int getMinute() {
        return mMinute;
    }

    public void setMinute(int minute) {
        mMinute = minute;
    }

    public int getSecond() {
        return mSecond;
    }

    public void setSecond(int second) {
        mSecond = second;
    }

}
