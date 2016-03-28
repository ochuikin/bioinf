package com.babichev.oleg.bioinformatic.problems;

import com.babichev.oleg.bioinformatic.core.BaseGenome;
import com.babichev.oleg.bioinformatic.core.DNA;
import com.babichev.oleg.bioinformatic.utils.MotifUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olegchuikin on 27/02/16.
 */
public class Problem20 {
    public static void main(String[] args) {
        int k = 15;
        int t = 20;

        List<BaseGenome> dnas = new ArrayList<>();
        for (String s : args){
            dnas.add(new DNA(s));
        }

        MotifUtil.randomizedMotifSearch(dnas, k, t, true).forEach(System.out::println);
    }
}
