package com.babichev.oleg.bioinformatic.problems;

import com.babichev.oleg.bioinformatic.core.DNA;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olegchuikin on 14/02/16.
 */
public class FindAllApproximateOccurrencesofaPatterninaString {
    public static void main(String[] args) {
        DNA dna = new DNA(args[0]);
        String pattern = "CTTTAGGAGGG";
        List<Integer> res = new ArrayList<>();
        dna.numberOfSubstringAppearsInText(pattern, res, 4);
        for (Integer i : res){
            System.out.printf(i + " ");
        }
    }
}
