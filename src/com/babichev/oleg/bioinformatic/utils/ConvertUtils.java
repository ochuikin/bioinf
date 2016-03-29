package com.babichev.oleg.bioinformatic.utils;

import com.babichev.oleg.bioinformatic.core.BaseGenome;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public static String binaryToString(long number){
        if (number == 0) return "";
        return binaryToString(number / 2) + (number % 2 == 1?"1":"0");
    }

    public static String multipleString(String s, int n){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) sb.append(s);
        return sb.toString();
    }

    public static String universalCircularString(int k){
        List<String> ref = new ArrayList<>();
        for (long i = 0; i < Math.pow(2, k); i++){
            String s = binaryToString(i);
            s = multipleString("0", k - s.length()) + s;
            ref.add(s);
        }

        Collections.sort(ref);

        List<String> ustring = new ArrayList<>();
        int ustringLen = (int)Math.pow(2, k) + k - 1;
        for (long i = 0; i < Math.pow(2, ustringLen); i++){
            String s = binaryToString(i);
            s = multipleString("0", ustringLen - s.length()) + s;
            if (compareLists(new BaseGenome(s).getKmerComposition(k), ref)){
                return s;
            }
        }

        return null;
    }

    private static boolean compareLists(List<String> a, List<String> b){
        if (a.size() != b.size()) return false;
        for (int i = 0; i < a.size(); i++){
            if (!a.get(i).equals(b.get(i))) return false;
        }
        return true;
    }

}
