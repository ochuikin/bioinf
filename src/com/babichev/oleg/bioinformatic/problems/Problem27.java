package com.babichev.oleg.bioinformatic.problems;

import com.babichev.oleg.bioinformatic.utils.GraphUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olegchuikin on 28/03/16.
 */
public class Problem27 {
    public static void main(String[] args) {
        List<String> kmers = new ArrayList<>();
        for (String arg : args) {
            kmers.add(arg);
        }

        GraphUtils graphUtils = GraphUtils.constructDeBruijnGraph(kmers);
        System.out.println(graphUtils.print());
    }
}
