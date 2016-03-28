package com.babichev.oleg.bioinformatic.problems;

import com.babichev.oleg.bioinformatic.core.BaseGenome;
import com.babichev.oleg.bioinformatic.utils.GrapthUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olegchuikin on 26/03/16.
 */
public class Problem24 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (String s : args){
            list.add(s);
        }
//        System.out.println(BaseGenome.reconstructFromGenomPath(list)); //24

        System.out.println(GrapthUtils.constructOverlapGraph(new BaseGenome("AAGATTCTCTAC").getKmerComposition(3)).print());
    }

}
