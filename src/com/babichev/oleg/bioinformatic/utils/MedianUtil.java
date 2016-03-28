package com.babichev.oleg.bioinformatic.utils;

import com.babichev.oleg.bioinformatic.core.BaseGenome;
import com.babichev.oleg.bioinformatic.core.DNA;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by olegchuikin on 21/02/16.
 */
public class MedianUtil {

    public static List<String> median(int k, List<BaseGenome> dnas){
        List<String> strings = GeneratorUtils.generateDNAStrings(k);
        Map<String, Integer> all = new HashMap<>();

        for (String s : strings){
            all.put(s, d(s, dnas));
        }

        Integer min = Integer.MAX_VALUE;
        for (Integer i : all.values()){
            min = Integer.min(min, i);
        }

        List<String> res = new ArrayList<>();
        for (String s : all.keySet()){
            if (all.get(s).equals(min)){
                res.add(s);
            }
        }
        return res;
    }

    private static int d(String pattern, List<BaseGenome> dnas){
        int res = 0;
        for (BaseGenome dna : dnas){
            res += dna.d(pattern);
        }
        return res;
    }

}
