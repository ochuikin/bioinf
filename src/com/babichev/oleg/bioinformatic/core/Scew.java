package com.babichev.oleg.bioinformatic.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olegchuikin on 14/02/16.
 */
public class Scew {

    private List<Integer> scew;

    public Scew (DNA dna){
        this.scew = strToScew(dna.getSymbols());
    }

    private List<Integer> strToScew(String str){
        List<Integer> res = new ArrayList<>();
        res.add(0);
        int current = 0;
        for (char c : str.toCharArray()){
            switch (c){
                case 'C':
                    current--;
                    break;
                case 'G':
                    current++;
                    break;
            }
            res.add(current);
        }
        return res;
    }

    public List<Integer> getScewMinIndexes(){
        if (! (scew.size() > 0)){
            return null;
        }
        int min = scew.get(0);
        for (Integer i : scew){
            if (i < min){
                min = i;
            }
        }

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < scew.size(); i++){
            if (scew.get(i) == min){
                res.add(i);
            }
        }
        return res;
    }

}
