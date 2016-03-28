package com.babichev.oleg.bioinformatic.problems;

import com.babichev.oleg.bioinformatic.core.BaseGenome;

/**
 * Created by olegchuikin on 26/03/16.
 */
public class Problem23 {
    public static void main(String[] args) {
        BaseGenome bg = new BaseGenome("ACTACTCTTCGTTCAACGCAATAATACGGAGTATGATAAACCTACCTTCCATCTGCTCCTAATATGGGGAGGATCCCGCGCGGTCTCGTTTCATTTGGGGAAATCGGTTGAGAATTAAGGTACGGATTGGACCCCAAGGATTATTTTCAGTGACGGGCGCCGGCCTGATTGCCTACTGAATTTTTACGCGCGCTAAGTTTGGGTACTTCTAATGGCTTTTACAGTTAGGGGGCTACGATACCAAGCAGAGAGATCCTTAACTCGCTAGAGCCGATTTGGCGTCTCCTGATGAAAGAGCGCATAATGTTCCCCATTACAAACTACCCCGACCGAGCTTTGCTTCAGAATTAGGGACCTATTCGTACCTCAATTGAAACACTGATCACGCTACGGGACATCCAGTGTCGCAGGTGCTAACCTAAACGATTCCAGTTCGCCCAGGACTATTCTAAGTATGCGTTCATATCCGTCGGTCCGCATTCTAAACTTCTCCTACAGAACGAATGAAGATATCGACGATTTCCAGCCATCACCTCCAACTCTAAATTACAAACTTGACTGAGTAAACTCAGACCCGTGAGCGTTCGGTGAACTTTATCACGATAAGGTCTTGTCCGTTCTAGCGGCGGTTGTGGCCTCGAATTGAGATTACGTTTTGCTGCGCTGGCGTGCCATCCGTGACCACCGGAAAAAAACCATGTGTAACAGCGGAATGTTGCAAAAGCAGGCGCATTTCGTCAGCTCATTTCGTAGGAACAGTACGATGCCGACCATGATGTGTGCTTACATGGCTCCAGAGACGTACGGGCTGTCGCCTGGGAAAATACAATCATATTAGTTAATTAATTTCTCTTTAAGAACAAAACGCCGAGACCAAGAGCTCTGTTTCGCAAGCGGCCGAGGTATCTAACATCCGGAGGCCCCCGTGACGGCGAGTTCCAGAGGGTGAAGAAAATGAAAAATTAGGCGACGTGTCTGAGGCGGGTTCCATAGACCTTAG");
        for (String s : bg.getKmerComposition(50)){
            System.out.println(s);
        }
    }
}
