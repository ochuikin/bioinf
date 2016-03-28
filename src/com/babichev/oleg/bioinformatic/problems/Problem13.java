package com.babichev.oleg.bioinformatic.problems;

import com.babichev.oleg.bioinformatic.utils.GeneratorUtils;

import java.util.List;

/**
 * Created by olegchuikin on 18/02/16.
 */
public class Problem13 {

    public static void main(String[] args) {
        int i = 8147;
        int k = 11;

        List<String> strings = GeneratorUtils.generateDNAStrings(k);
        System.out.println(strings.get(i));
    }

}
