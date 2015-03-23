package helpers;

public class DataMod {

    /**
     * This just checks is a string is a number
     * @param str String imput
     * @return boolean returned
     */
    public static boolean isNumeric(String str) {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }

}
