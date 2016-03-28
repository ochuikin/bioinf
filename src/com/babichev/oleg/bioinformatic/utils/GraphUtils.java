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

    public static GraphUtils constructDeBruijnGraph(BaseGenome genome, int k) {
        List<String> kmerComposition = genome.getKmerComposition(k);

        return constructDeBruijnGraph(kmerComposition);
    }

    public static GraphUtils constructDeBruijnGraph(List<String> kmerComposition) {
        GraphUtils gu = new GraphUtils();

        for (String s : kmerComposition) {
            String left = s.substring(0, s.length() - 1);
            String right = s.substring(1);
            if (!gu.data.containsKey(left)) {
                gu.data.put(left, new ArrayList<>());
            }
            gu.data.get(left).add(right);
        }
        return gu;

    }

    public static GraphUtils parseDirectedGraph(List<String> lines) {
        GraphUtils gu = new GraphUtils();
        for (String line : lines) {
            String left = line.split(" ")[0];
            String[] rights = line.split(" ")[2].split(",");
            if (!gu.data.containsKey(left)) {
                gu.data.put(left, new ArrayList<>());
            }
            for (String right : rights) {
                gu.data.get(left).add(right);
            }
        }
        return gu;
    }

    private GraphUtils makeCopy() {
        GraphUtils gu = new GraphUtils();
        for (String left : data.keySet()) {
            gu.data.put(left, new ArrayList<>());
            for (String s : data.get(left)) {
                gu.data.get(left).add(s);
            }
        }
        return gu;
    }

    public List<String> findEulerPath() {
        Map<String, List<String>> copy = makeCopy().data;

        List<String> circuit = new ArrayList<>();
        List<String> stack = new ArrayList<>();
        String location = new ArrayList<String>(copy.keySet()).get(0);
        String start = location;

        int edgesCount = 0;

        while (edgesCount != edgesCount()) {
            List<String> ways = copy.get(location);
            if (ways.size() != 0) {
                stack.add(location);
                location = ways.get(0);
                ways.remove(0);
            } else {
                circuit.add(location);
                location = stack.get(stack.size() - 1);
                stack.remove(stack.size() - 1);
                edgesCount++;
            }
        }
        Collections.reverse(circuit);
        circuit.add(circuit.get(0));
        return circuit;
    }

    public String printEulerPath(){
        StringBuilder sb = new StringBuilder();
        for (String s : findEulerPath()){
            sb.append("->" + s);
        }
        return sb.toString().substring(2);
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

    private int edgesCount() {
        int count = 0;
        for (String s : data.keySet()) {
            count += data.get(s).size();
        }
        return count;
    }

}
