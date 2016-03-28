package com.babichev.oleg.bioinformatic.utils;

/**
 * Created by olegchuikin on 18/02/16.
 */
public class ConvertUtils {

    public static Long DNAStringToInteger(String str) {
        return DNAStringToInteger(str, 4);
    }

    public static Long DNAStringToInteger(String str, int base) {
        long result = 0;
        long currentBase = 1;
        for (int i = str.length() - 1; i >= 0; i--) {
            char c = str.charAt(i);
            result += currentBase * ((c == 'A') ? 0 : (c == 'C') ? 1 : (c == 'G') ? 2 : 3);
            currentBase *= base;
        }

        return result;
    }

}
