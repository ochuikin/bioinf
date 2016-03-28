package com.babichev.oleg.bioinformatic.problems;

import com.babichev.oleg.bioinformatic.core.BaseGenome;
import com.babichev.oleg.bioinformatic.core.DNA;
import com.babichev.oleg.bioinformatic.utils.HammingDistanceUtil;
import com.babichev.oleg.bioinformatic.utils.MotifUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olegchuikin on 17/03/16.
 */
public class Problem21and22 {
    public static void main(String[] args) {
        int k = 15;
        int t = 20;
        int N = 2000;

        List<BaseGenome> dnas = new ArrayList<>();
        for (String s : args){
            dnas.add(new DNA(s));
        }

        String pattern = "GAAAT";

        /*System.out.println("Answer:");
        for (String s : MotifUtil.gibbsSamplerIter(dnas, k, t, N, 20)) {
            System.out.println(s);
        }*/

        System.out.println(HammingDistanceUtil.distanceBetweenPatternAndStrings(pattern, dnas));
    }
}
