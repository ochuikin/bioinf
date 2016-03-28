package com.babichev.oleg.bioinformatic.utils;

import com.babichev.oleg.bioinformatic.core.BaseGenome;

import java.util.*;

/**
 * Created by olegchuikin on 26/03/16.
 */
public class GrapthUtils {

    private Map<String, Set<String>> data = new HashMap<>();


    private GrapthUtils() {
    }

    public static GrapthUtils constructOverlapGraph(List<String> strings) {
        GrapthUtils g = new GrapthUtils();

        ArrayList<String> source = new ArrayList<>(strings);

        Collections.sort(source);

        for (String left : source) {
            Set<String> value = new TreeSet<>();
            for (String right : source) {
                if (!left.equals(right)) {
                    if (right.startsWith(left.substring(1))) {
                        value.add(right);
                    }
                }
            }
            g.data.put(left, value);
        }

        return g;
    }

    public static GrapthUtils constructDeBruijnGraph(BaseGenome genome, int k){
        GrapthUtils gu = new GrapthUtils();

        List<String> kmerComposition = genome.getKmerComposition(k);
        for (String s : kmerComposition){
            String left = s.substring(0, s.length() - 1);
            String right = s.substring(1);
            if (!gu.data.containsKey(left)){
                gu.data.put(left, new HashSet<>());
            }
            gu.data.get(left).add(right);
        }
        return gu;
    }

    public String print() {
        StringBuilder sb = new StringBuilder();
        List<String> strs = new ArrayList<>(data.keySet());
        Collections.sort(strs);
        for (String left : strs) {
            Set<String> rights = data.get(left);
            if (rights.size() != 0) {
                sb.append(left + " -> ");
                for (String right : rights) {
                    sb.append((right.equals(rights.toArray()[0]) ? "" : ",") + right);
                }
                sb.append("\n");
            }
        }
        return sb.toString();
    }

}
