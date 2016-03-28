package com.babichev.oleg.bioinformatic.problems;

import com.babichev.oleg.bioinformatic.enums.GNASymbol;

import java.util.HashMap;
import java.util.Map;

import static com.babichev.oleg.bioinformatic.enums.GNASymbol.*;

/**
 * Created by olegchuikin on 14/02/16.
 */
public class CountingDNANucleotides {

    public static Map<GNASymbol, Integer> countDNANucleotides(String dna) {
        Map<GNASymbol, Integer> result = new HashMap<>();
        for (char c : dna.toCharArray()) {
            switch (c) {
                case 'A':
                    result.put(A, result.getOrDefault(A, 0) + 1);
                    break;
                case 'C':
                    result.put(C, result.getOrDefault(C, 0) + 1);
                    break;
                case 'G':
                    result.put(G, result.getOrDefault(G, 0) + 1);
                    break;
                case 'T':
                    result.put(T, result.getOrDefault(T, 0) + 1);
                    break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Map<GNASymbol, Integer> res = countDNANucleotides("CGCTGTAATATCACGCACCCTTCCTAAAAGGCGATGCGCTGATGCAGCGTGCCCGCATCTGCATAGCTCACAATCGGTAGTGGAATGAGTCTATTATTTTCAGGTAAGAAACACACTGAGCGTGAGGGTGTAACGACACTCAACCGATTAACCCCACATGTGTGTTGCAAACACCTGTGTTGGTAGTTGAGGGTAGCTCAACGACAGCGAGATAAGCTGGCGGCGGTCTTCCGATCCGATCTCAGCAGCAAGACTAGAAGCACGACAGCCCAAAACTGAGATAGAGCTCAAGTTTATCGTAGAGATTATTCTTGCCTGAACAGTAAGCCTGGTTTTACGGAACTCGCAGAAGGGAAAAGTATGTTCAGAACATCCGCCAGGTTGGATTATCGCACTACTAAACTTTATAGCGAAGATCCTGTATCACAAATATCCTGTTTCTGACCATCAATGTCAAGTGATAATCCCAAAGTTGGTTAACCAAAGAGCTAGGTACCCTCTCTGATTGACGAACGATCTGGGTAAGATCGGTGGCGGCAATCAGACCATCGCCCACTAGTTCTTTGTTTGCGGGCGATTTAACCCCAAGTATTCCCAGCAGATATGTAGAACCGCTCTCTTACCCAGCGATTCTGTCTTCTCGGGGCTGAACCTCTCGGTCAAGTCCTGCGGGGGGAACAATGATATGGGATGAATATTTGGTCTTGAGGGTACCACAAACAAGCATTCTTACTGTCAGATAAGCTTGGTGTACAGCGCAAAACCCTATAGAAATGAGCCCGCTATTTTCGTGGGTGGACCGCATAGGCGGACTAGATGACGAACATCACTACCGAAGACTTAGGCA\n");
        System.out.println(res.get(A) + " " + res.get(C) + " " + res.get(G) + " " + res.get(T));

    }

}
