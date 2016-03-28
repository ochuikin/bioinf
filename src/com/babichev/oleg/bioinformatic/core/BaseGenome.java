package com.babichev.oleg.bioinformatic.core;

import com.babichev.oleg.bioinformatic.utils.GeneratorUtils;
import com.babichev.oleg.bioinformatic.utils.HammingDistanceUtil;

import java.util.*;

/**
 * Created by olegchuikin on 14/02/16.
 */
public class BaseGenome {

    protected String symbols;

    public BaseGenome(String symbols) {
        this.symbols = symbols;
    }

    @Override
    public String toString() {
        return symbols;
    }

    protected char getComplementSymbol(char c){
        switch (c){
            case 'A':
                return 'T';
            case 'T':
                return 'A';
            case 'C':
                return 'G';
            case 'G':
                return 'C';
        }
        return ' ';
    }

    public int numberOfSubstringAppearsInText(String substr, List<Integer> listForIndexes){
        return numberOfSubstringAppearsInText(substr, listForIndexes, 0);
    }

    public int numberOfSubstringAppearsInText(String substr){
        return numberOfSubstringAppearsInText(substr, null, 0);
    }

    public int numberOfSubstringAppearsInText(String substr, List<Integer> listForIndexes, int d) {

        int result = 0;

        for (int i = 0; i < symbols.length() + 1 - substr.length(); i++){
            String originSubstr = symbols.substring(i, i + substr.length());
            if (HammingDistanceUtil.countHummingDistance(substr, originSubstr) <= d){
                result++;
                if (listForIndexes != null){
                    listForIndexes.add(i);
                }
            }
        }

        return result;
    }

    public List<String> getTheMostFrequentWordsOfLenghtK(int k, int d) {
        return getTheMostFrequentWordsOfLenghtK(k, d, false);
    }

    public List<String> getTheMostFrequentWordsOfLenghtK(int k, int d, boolean isWithReverseComponent){
        if (d == 0){
            return getTheMostFrequentWordsOfLenghtK(k);
        }

        Map<String, Integer> allWords = new HashMap<>();

        List<String> subs = GeneratorUtils.generateDNAStrings(k);
        for (String sub : subs) {
            allWords.put(sub, numberOfSubstringAppearsInText(sub, null, d)
                    + ((isWithReverseComponent)?numberOfSubstringAppearsInText(makeReverseComponent(sub), null, d):0) );
        }

        int max = 0;
        for (String s : allWords.keySet()){
            if (allWords.get(s) > max){
                max = allWords.get(s);
            }
        }

        List<String> res = new ArrayList<>();
        for (String s : allWords.keySet()){
            if (allWords.get(s) == max){
                res.add(s);
            }
        }
        return res;
    }

    public List<String> getTheMostFrequentWordsOfLenghtK(int k){

        Map<String, Integer> allWords = new HashMap<>();

        for (int i = 0; i < symbols.length() - k + 1; i++){
            String subStr = symbols.substring(i, i + k);
            if (!allWords.containsKey(subStr)){
                allWords.put(subStr, numberOfSubstringAppearsInText(subStr));
            }
        }

        int max = 0;
        for (String s : allWords.keySet()){
            if (allWords.get(s) > max){
                max = allWords.get(s);
            }
        }

        List<String> res = new ArrayList<>();
        for (String s : allWords.keySet()){
            if (allWords.get(s) == max){
                res.add(s);
            }
        }
        return res;
    }

    public List<String> getPatternsFormingClumpInMolecule(int k, int l, int t){

        Set<String> res = new HashSet<>();

        for (int i = 0; i < symbols.length() + 1 - l; i++){
            String dnaSubstr = symbols.substring(i, i + l);
            DNA tmpDNA = new DNA(dnaSubstr);
            for (int j = 0; j < dnaSubstr.length() + 1 - k; j++){
                String putternSubstr = dnaSubstr.substring(j, j + k);
                if (tmpDNA.numberOfSubstringAppearsInText(putternSubstr) >= t){
                    res.add(putternSubstr);
                }
            }
        }
        return new ArrayList<>(res);
    }

    protected String makeReverseComponent(String mainComponent) {
        StringBuilder sb = new StringBuilder();
        for (char c : mainComponent.toCharArray()) {
            sb.insert(0, getComplementSymbol(c));
        }
        return sb.toString();
    }

    public int d(String pattern){
        int res = Integer.MAX_VALUE;
        for (String s : getAllKMers(pattern.length())){
            res = Integer.min(res, HammingDistanceUtil.countHummingDistance(pattern, s));
        }

        return res;
    }

    public List<String> getAllKMers(int k){
        List<String> res = new ArrayList<>();
        for (int i = 0 ; i < symbols.length() - k + 1; i++){
            res.add(symbols.substring(i, i + k));
        }
        return res;
    }

    public List<String> getKmerComposition(int k){
        List<String> allKMers = getAllKMers(k);
        Collections.sort(allKMers);
        return allKMers;
    }

    public static BaseGenome reconstructFromGenomPath(List<String> composition){
        StringBuilder sb = new StringBuilder();
        for (String s : composition) {
            if (sb.length() == 0){
                sb.append(s);
            } else {
                sb.append(s.charAt(s.length() - 1));
            }
        }
        return new BaseGenome(sb.toString());
    }

    public String getSymbols() {
        return symbols;
    }
}
