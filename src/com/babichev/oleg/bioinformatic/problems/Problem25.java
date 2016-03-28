package com.babichev.oleg.bioinformatic.problems;

import com.babichev.oleg.bioinformatic.utils.GraphUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olegchuikin on 26/03/16.
 */
public class Problem25 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (String s : args){
            list.add(s);
        }
        System.out.println(GraphUtils.constructOverlapGraph(list).print());
    }
}
