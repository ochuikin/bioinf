package com.babichev.oleg.bioinformatic.utils;

import com.babichev.oleg.bioinformatic.core.BaseGenome;

import java.util.*;

/**
 * Created by olegchuikin on 26/03/16.
 */
public class GraphUtils {

    private Map<String, List<String>> data = new HashMap<>();


    private GraphUtils() {
    }

    public static GraphUtils constructOverlapGraph(List<String> strings) {
        GraphUtils g = new GraphUtils();

        ArrayList<String> source = new ArrayList<>(strings);

        Collections.sort(source);

        for (String left : source) {
            List<String> value = new ArrayList<>();
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

    public static GraphUtils constructDeBruijnGraph(BaseGenome genome, int k){
        List<String> kmerComposition = genome.getKmerComposition(k);

        return constructDeBruijnGraph(kmerComposition);
    }

    public static GraphUtils constructDeBruijnGraph(List<String> kmerComposition) {
        GraphUtils gu = new GraphUtils();

        for (String s : kmerComposition){
            String left = s.substring(0, s.length() - 1);
            String right = s.substring(1);
            if (!gu.data.containsKey(left)){
                gu.data.put(left, new ArrayList<>());
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
            List<String> rights = data.get(left);
            Collections.sort(rights);
            boolean first = true;
            if (rights.size() != 0) {
                sb.append(left + " -> ");
                for (String right : rights) {
                    sb.append((first ? "" : ",") + right);
                    first = false;
                }
                sb.append("\n");
            }
        }
        return sb.toString();
    }

}
