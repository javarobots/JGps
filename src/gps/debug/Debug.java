
package gps.debug;

/**
 *
 * @author javarobots
 */
public class Debug {

    private static boolean mConsoleOutput = false;

    public static void setConsoleOutput(boolean outToConsole){
        mConsoleOutput = outToConsole;
    }

    public static void debugOut(String message){
        if (mConsoleOutput){
            System.out.println(message);
        }
    }

}
