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

    public List<String> findEulerCycle() {
        String current = (String) data.keySet().toArray()[0];

        List<String> path = new ArrayList<>();
        path.add(current);

        GraphUtils gu = makeCopy();

        while (true){
            path.add(gu.data.get(current).get(0));

            gu.data.get(current).remove(0);

            if (gu.data.containsKey(path.get(path.size() - 1))
                    && gu.data.get(path.get(path.size() - 1)).size() != 0){
                current = path.get(path.size() - 1);
            } else {
                break;
            }
        }

        while (gu.edgesCount() > 0){
            for (int i = 0; i < path.size(); i++){
                if (gu.data.containsKey(path.get(i))
                        && gu.data.get(path.get(i)).size() != 0){
                    current = path.get(i);
                    List<String> cycle = new ArrayList<>();
                    cycle.add(current);

                    while (true){
                        cycle.add(gu.data.get(current).get(0));

                        gu.data.get(current).remove(0);

                        if (gu.data.containsKey(cycle.get(cycle.size()-1))
                                && gu.data.get(cycle.get(cycle.size()-1)).size() != 0){
                            current = cycle.get(cycle.size() - 1);
                        } else {
                            break;
                        }
                    }

                    List<String> tmp = new ArrayList<>();
                    for (int j = 0; j < i; j++) tmp.add(path.get(j));
                    tmp.addAll(cycle);
                    for (int j = i + 1; j < path.size(); j++) tmp.add(path.get(j));
                    path = tmp;
                    break;
                }
            }
        }

        return path;
    }

    public List<String> findEulerPath() {
        GraphUtils gu = makeCopy();

        List<String> outValues = new ArrayList<>();
        for (String s : gu.data.keySet()){
            for (String value : gu.data.get(s)){
                outValues.add(value);
            }
        }
        Collections.sort(outValues);

        Set<String> distinctValues = new HashSet<>();
        distinctValues.addAll(outValues);
        distinctValues.addAll(gu.data.keySet());

        String unbalancedFrom = "";
        String unbalancedTo = "";

        for (String node : distinctValues){
            int outValue = countInList(outValues, node);
            int inValue;
            if (gu.data.containsKey(node) && gu.data.get(node).size() != 0){
                inValue = gu.data.get(node).size();
            } else {
                inValue = 0;
            }

            if (inValue < outValue){
                unbalancedFrom = node;
            } else if (inValue > outValue) {
                unbalancedTo = node;
            }
        }

        if (gu.data.containsKey(unbalancedFrom)){
            gu.data.get(unbalancedFrom).add(unbalancedTo);
        } else {
            List<String> tmp = new ArrayList<>();
            tmp.add(unbalancedTo);
            gu.data.put(unbalancedFrom, tmp);
        }

        List<String> cycle = gu.findEulerCycle();

        int divide = -1;
        for (int i = 0; i < cycle.size() - 2; i++){
            if (cycle.get(i).equals(unbalancedFrom) && cycle.get(i + 1).equals(unbalancedTo)){
                divide = i;
                break;
            }
        }


        List<String> res = new ArrayList<>();
        for (int i = divide + 1; i < cycle.size(); i++) res.add(cycle.get(i));
        for (int i = 1; i < divide + 1; i++) res.add(cycle.get(i));

        return res;
    }

    private int countInList(List<String> list, String s){
        int i = 0;
        for (String str : list)
            if (str.equals(s))
                i++;
        return i;
    }

    public String printEulerPath() {
        StringBuilder sb = new StringBuilder();
        for (String s : findEulerPath()) {
            sb.append("->" + s);
        }
        if (sb.length() == 0) return "";
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
