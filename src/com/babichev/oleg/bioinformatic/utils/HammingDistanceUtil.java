package com.babichev.oleg.bioinformatic.utils;

import com.babichev.oleg.bioinformatic.core.BaseGenome;

import java.util.List;

/**
 * Created by olegchuikin on 14/02/16.
 */
public class HammingDistanceUtil {
    public static int countHummingDistance(String str1, String str2){
        int res = 0;
        for (int i = 0; i < Integer.min(str1.length(), str2.length()); i++){
            if (str1.charAt(i) != str2.charAt(i)){
                res++;
            }
        }
        return res;
    }

    public static int distanceBetweenPatternAndStrings(String pattern, List<BaseGenome> dnas){
        int k = pattern.length();
        int distance = 0;
        for (BaseGenome dna : dnas) {
            int hDistance = Integer.MAX_VALUE;
            for (String kmer : dna.getAllKMers(k)){
                if (hDistance > countHummingDistance(kmer, pattern)){
                    hDistance = countHummingDistance(kmer, pattern);
                }
            }
            distance += hDistance;
        }
        return distance;
    }
}
