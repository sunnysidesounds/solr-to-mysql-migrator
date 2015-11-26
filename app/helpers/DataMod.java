package helpers;

import java.util.regex.*;
import java.util.*;

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

    /**
     * This pulls links from a text-like string
     * @param text
     * @return
     */
    public static ArrayList pullLinks(String text) {
        ArrayList links = new ArrayList();

        String regex = "\\(?\\b(http://|www[.])[-A-Za-z0-9+&@#/%?=~_()|!:,.;]*[-A-Za-z0-9+&amp;@#/%=~_()|]";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(text);
        while(m.find()) {
            String urlStr = m.group();
            if (urlStr.startsWith("(") && urlStr.endsWith(")"))
            {
                urlStr = urlStr.substring(1, urlStr.length() - 1);
            }
            links.add(urlStr);
        }
        return links;
    }

    /**
     * This turns a string into a title case string
     * @param input
     * @return
     */
    public static String toTitleCase(String input) {
        StringBuilder titleCase = new StringBuilder();
        boolean nextTitleCase = true;

        for (char c : input.toCharArray()) {
            if (Character.isSpaceChar(c)) {
                nextTitleCase = true;
            } else if (nextTitleCase) {
                c = Character.toTitleCase(c);
                nextTitleCase = false;
            } else {
                c = Character.toLowerCase(c);
            }

            titleCase.append(c);
        }
        return titleCase.toString();
    }

    /**
     * This checks if a string is uppercase.
     * @param s
     * @return
     */
    public static boolean isUpperCased(String s)
    {
        for(char c : s.toCharArray())
        {
            if(! Character.isUpperCase(c)
                    && c != ' '
                    && Character.isLetterOrDigit(c))
                return false;
        }

        return true;
    }


}
