package gps.data;

/**
 *
 * @author javarobots
 */
public class UTCDate {

    private int mDay;
    private int mMonth;
    private int mYear;

    public UTCDate(){
        mMonth = 04;
        mDay = 01;
        mYear = 12;
    }

    public UTCDate(int day, int month, int year){
        mMonth = month;
        mDay = day;
        mYear = year;
    }

    public int getDay() {
        return mDay;
    }

    public void setDay(int day) {
        this.mDay = day;
    }

    public int getMonth() {
        return mMonth;
    }

    public void setMonth(int month) {
        this.mMonth = month;
    }

    public int getYear() {
        return mYear;
    }

    public void setYear(int year) {
        this.mYear = year;
    }



}
