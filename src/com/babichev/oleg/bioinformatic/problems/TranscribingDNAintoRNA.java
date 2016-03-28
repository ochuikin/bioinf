package com.babichev.oleg.bioinformatic.problems;

import com.babichev.oleg.bioinformatic.core.DNA;

/**
 * Created by olegchuikin on 14/02/16.
 */
public class TranscribingDNAintoRNA {
    public static void main(String[] args) {
        DNA dna = new DNA(args[0]);
        System.out.println(dna.makeRNA());
    }
}
