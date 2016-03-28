package com.babichev.oleg.bioinformatic.core;

import com.sun.javafx.binding.StringFormatter;
import com.sun.tools.javac.util.Pair;

import java.util.*;

/**
 * Created by olegchuikin on 22/02/16.
 */
public class Profile {

    private double[][] profile;

    private int size;

    private double comparing_constant = 0.000000000001;

    public Profile(int size){
        profile = new double[4][size];
        this.size = size;
    }

    public Profile(String ... data) {
        profile = new double[4][];
        for (int i = 0; i < data.length; i++){
            String[] strs = data[i].split(" ");
            profile[i] = new double[strs.length];
            for (int j = 0; j < strs.length; j++){
                profile[i][j] = Double.parseDouble(strs[j]);
            }
        }
        this.size = profile[0].length;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++){
            for (double d : profile[i]){
                sb.append(StringFormatter.format("%.2f", d).getValue());
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public double stringProbability(String s){
        if (s.length() != size){
            throw new StringWithWrongLengthException();
        }

        double res = 1;
        for (int i = 0; i < size; i++){
            switch (s.charAt(i)){
                case 'A':
                    res *= profile[0][i];
                    break;
                case 'C':
                    res *= profile[1][i];
                    break;
                case 'G':
                    res *= profile[2][i];
                    break;
                case 'T':
                    res *= profile[3][i];
                    break;
            }
        }
        return res;
    }

    public List<String> makeMotifsFromDnas(List<BaseGenome> dnas, int k){
        List<String> dnasStr = new ArrayList<>();
        for (BaseGenome bg : dnas) dnasStr.add(bg.getSymbols());
        return makeMotifsFromDnasStr(dnasStr, k);
    }

    public List<String> makeMotifsFromDnasStr(List<String> dnas, int k){
        List<String> res = new ArrayList<>();

        for (String s : dnas){
            res.add(findMostProbableKmer(s).get(0));
        }
        return res;
    }

    public List<String> findMostProbableKmer(String s){
        return findMostProbableKmer(new BaseGenome(s));
    }

    public List<String> findMostProbableKmer(BaseGenome bg){
        Map<String, Double> all = new HashMap<>();
        List<String> allKMers = bg.getAllKMers(size);
        for (String s : allKMers){
            all.put(s, stringProbability(s));
        }

        List<String> res = new ArrayList<>();
        Double max = -1.;
        for (Double d : all.values()){
            if (d > max){
                max = d;

            }
        }

        for (String s : allKMers){
            if (stringProbability(s) >= max){
                res.add(s);
            }
        }

/*//        List<String> res = new ArrayList<>();
        for (String s : all.keySet()){
            if (Math.abs(all.get(s) - max) < comparing_constant){
                res.add(s);
            }
            *//*if (all.get(s) > max){
                System.out.println(s);
                max = all.get(s);
                res.clear();
                res.add(s);
            }*//*
        }

        Collections.sort(res);*/

        return res;
    }

    public void set(int i, int j, double value){
        profile[i][j] = value;
    }

    public void set(char c, int j, double value){
        switch (c){
            case 'A':
                profile[0][j] = value;
                break;
            case 'C':
                profile[1][j] = value;
                break;
            case 'G':
                profile[2][j] = value;
                break;
            case 'T':
                profile[3][j] = value;
                break;
        }
    }

    public String randomProfileKmer(BaseGenome dna, int k, Random rnd){
        double totalProbability = 0;

        List<Pair<Double, String>> pairs = new ArrayList<>();

        for (String kmer : dna.getAllKMers(k)){
            double probability = this.stringProbability(kmer);
            totalProbability += probability;

            pairs.add(new Pair(new Double(probability), kmer));

        }

        double randomValue = totalProbability * rnd.nextDouble();

        double probabilityAcc = 0;
        for (Pair<Double, String> pair : pairs){
            probabilityAcc += pair.fst;
            if (probabilityAcc > randomValue) return pair.snd;
        }

        throw new RuntimeException();
    }

    class StringWithWrongLengthException extends RuntimeException{

    }
}
