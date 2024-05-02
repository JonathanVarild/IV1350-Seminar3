package utilities;

public class OutputHelper {

    /**
     * A static method to format a float with 2 decimals and a colon as separator.
     * 
     * @param value The float value to format.
     * 
     * @return The formatted string.
     */
    public static String getFloatWithColon(float value) {
        int integer = (int)value;
        int decimal = Math.abs(Math.round((value - integer) * 100));
        return String.format("%d:%d", integer, decimal);
    }

}
