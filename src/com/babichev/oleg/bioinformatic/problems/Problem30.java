package com.babichev.oleg.bioinformatic.problems;

import com.babichev.oleg.bioinformatic.core.BaseGenome;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olegchuikin on 29/03/16.
 */
public class Problem30 {
    public static void main(String[] args) {
        List<String> kmers = new ArrayList<>();
        for (String s : args){
            kmers.add(s);
        }

        System.out.println(BaseGenome.reconstructionFromKmers(kmers));
    }
}
