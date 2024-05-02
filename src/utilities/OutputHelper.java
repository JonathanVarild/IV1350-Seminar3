package utilities;

public class OutputHelper {

    public static String getFloatWithColon(float value) {
        int integer = (int)value;
        int decimal = Math.round((value - integer) * 100);
        return String.format("%d:%d", integer, decimal);
    }

}
