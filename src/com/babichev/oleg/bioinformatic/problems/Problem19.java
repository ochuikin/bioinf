package com.babichev.oleg.bioinformatic.problems;

import com.babichev.oleg.bioinformatic.core.BaseGenome;
import com.babichev.oleg.bioinformatic.core.DNA;
import com.babichev.oleg.bioinformatic.utils.MotifUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olegchuikin on 27/02/16.
 */
public class Problem19 {
    public static void main(String[] args) {
        int k = 12;
        int t = 5;

        List<BaseGenome> dnas = new ArrayList<>();
        for (String s : args){
            dnas.add(new DNA(s));
        }

        for (String s : MotifUtil.greedyMotifSearch(dnas, k, t, true)){
            System.out.println(s);
        }
    }
}
