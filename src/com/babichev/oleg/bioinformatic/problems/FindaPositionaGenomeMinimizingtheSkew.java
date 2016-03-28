package com.babichev.oleg.bioinformatic.problems;

import com.babichev.oleg.bioinformatic.core.DNA;
import com.babichev.oleg.bioinformatic.core.Scew;

/**
 * Created by olegchuikin on 14/02/16.
 */
public class FindaPositionaGenomeMinimizingtheSkew {
    public static void main(String[] args) {
        DNA dna = new DNA(args[0]);
        Scew scew = new Scew(dna);
        for (Integer i : scew.getScewMinIndexes()){
            System.out.printf(i + " ");
        }
    }
}
