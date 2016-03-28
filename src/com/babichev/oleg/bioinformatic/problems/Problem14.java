package com.babichev.oleg.bioinformatic.problems;

import com.babichev.oleg.bioinformatic.utils.GeneratorUtils;

/**
 * Created by olegchuikin on 18/02/16.
 */
public class Problem14 {

    public static void main(String[] args) {
        String pattern = "GGAGAGTACT";
        int d = 2;
        for (String s : GeneratorUtils.Neighbors(pattern, d)){
            System.out.println(s);
        }
    }

}
