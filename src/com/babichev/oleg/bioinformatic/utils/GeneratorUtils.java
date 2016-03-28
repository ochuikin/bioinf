package com.babichev.oleg.bioinformatic.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olegchuikin on 18/02/16.
 */
public class GeneratorUtils {

    public static List<String> generateDNAStrings(int length){

        List<String> result = new ArrayList<>();

        internGenerateDNAString("", length, result);
        return result;
    }

    private static void internGenerateDNAString(String s, int l, List<String> result){
        if (l == 0){
            result.add(s);
        } else {
            l--;
            internGenerateDNAString(s + 'A', l, result);
            internGenerateDNAString(s + 'C', l, result);
            internGenerateDNAString(s + 'G', l, result);
            internGenerateDNAString(s + 'T', l, result);
        }
    }

    public static List<String> Neighbors(String pattern, int d){
        List<String> res = new ArrayList<>();
        for (String s : generateDNAStrings(pattern.length())){
            if (HammingDistanceUtil.countHummingDistance(s, pattern) <= d){
                res.add(s);
            }
        }
        return res;
    }
}

