package utils;

public class IntegerUtils {
    /**
     * Checks if the string is an Integer by using Regex
     */
    public static boolean isInteger(String s) {
        return s.matches("-?\\d+");
    }
}
