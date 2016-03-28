package com.babichev.oleg.bioinformatic.problems;

import com.babichev.oleg.bioinformatic.core.BaseGenome;
import com.babichev.oleg.bioinformatic.core.DNA;
import com.babichev.oleg.bioinformatic.utils.MotifUtil;
import com.sun.xml.internal.rngom.parse.host.Base;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olegchuikin on 22/02/16.
 */
public class Problem18 {
    public static void main(String[] args) {
        int k = 12;
        int t = 5;

        List<BaseGenome> dnas = new ArrayList<>();
        for (String s : args){
            dnas.add(new DNA(s));
        }

        for (String s : MotifUtil.greedyMotifSearch(dnas, k, t)){
            System.out.println(s);
        }
    }
}
