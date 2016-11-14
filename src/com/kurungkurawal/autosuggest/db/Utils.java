package com.kurungkurawal.autosuggest.db;

/**
 * Created with IntelliJ IDEA.
 * Author: Konglie
 */
public class Utils {

    public static String pgEscape(String s) {
        if (s == null) {
            return "";
        }
        s = s.replaceAll("'", "''");
        return s;
    }

    public static String pgEscape(int i) {
        return pgEscape(i + "");
    }
}
