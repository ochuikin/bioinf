package com.babichev.oleg.bioinformatic.problems;

import com.babichev.oleg.bioinformatic.core.DNA;
import com.babichev.oleg.bioinformatic.core.Profile;

import java.util.List;

/**
 * Created by olegchuikin on 22/02/16.
 */
public class Problem17 {
    public static void main(String[] args) {

        DNA dna = new DNA("CCCTACTACATACAGGAGCTCCCGTAAGGATCCAGCTCGGATAAGCAACCGTGTTTACCGCACGTAACACGAAGGACAGCGTGTGACGGGTGAAGCAGCACAGATAATTTCCAGCACACACATGGTGAAGGCGTCCGCCGAATATTATCTCAGTTCACCATCGTCCGGCGAAAGGGCTCCTTGTTAAACCTCCTCATCAA");

        Profile profile = new Profile(
                "0.2 0.16 0.16 0.32 0.32 0.12 0.28 0.28",
                "0.28 0.28 0.4 0.16 0.16 0.24 0.28 0.12",
                "0.28 0.16 0.12 0.24 0.32 0.4 0.24 0.28",
                "0.24 0.4 0.32 0.28 0.2 0.24 0.2 0.32");

        List<String> mostProbableKmer = profile.findMostProbableKmer(dna);
        for (String s : mostProbableKmer){
            System.out.println(s);
        }

    }
}
