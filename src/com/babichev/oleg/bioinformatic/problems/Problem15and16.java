package com.babichev.oleg.bioinformatic.problems;

import com.babichev.oleg.bioinformatic.core.BaseGenome;
import com.babichev.oleg.bioinformatic.core.DNA;
import com.babichev.oleg.bioinformatic.utils.MedianUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olegchuikin on 21/02/16.
 */
public class Problem15and16 {

    public static void main(String[] args) {

        int k = 6;
        int d = 1;
        List<BaseGenome> dnas = new ArrayList<>();
        for (int i = 0; i < args.length; i++){
            dnas.add(new DNA(args[i]));
        }

//        List<String> strings = MotifUtil.countMotifEnumeration(k, d, dnas);

        List<String> strings = MedianUtil.median(k, dnas);

        for (String s : strings){
            System.out.printf(s + " ");
        }

    }

}
