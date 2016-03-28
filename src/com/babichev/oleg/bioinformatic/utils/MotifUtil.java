package com.babichev.oleg.bioinformatic.utils;

import com.babichev.oleg.bioinformatic.core.BaseGenome;
import com.babichev.oleg.bioinformatic.core.Profile;
import com.sun.tools.javac.util.Pair;

import java.util.*;

/**
 * Created by olegchuikin on 21/02/16.
 */
public class MotifUtil {

    private static Random rnd = new Random(100);

    public static List<String> countMotifEnumeration(int k, int d, List<BaseGenome> genomes) {
        List<String> result = new ArrayList<>();

        List<String> allPatterns = GeneratorUtils.generateDNAStrings(k);
        for (String pattern : allPatterns) {
            boolean inAll = true;
            for (BaseGenome bg : genomes) {
                if (bg.numberOfSubstringAppearsInText(pattern, null, d) == 0) {
                    inAll = false;
                }
            }
            if (inAll) {
                result.add(pattern);
            }
        }
        return result;
    }

    public static List<String> greedyMotifSearch(List<BaseGenome> dnas, int k, int t){
        return greedyMotifSearch(dnas, k, t, false);
    }

    public static List<String> greedyMotifSearch(List<BaseGenome> dnas, int k, int t, boolean withPseudoCounts) {

        List<String> bestMotifs = new ArrayList<>();
        for (BaseGenome bg : dnas) {
            bestMotifs.add(bg.getSymbols().substring(0, k));
        }

        List<String> allKMers = dnas.get(0).getAllKMers(k);
        for (String s : allKMers.subList(1, allKMers.size())) {
            List<String> motifs = new ArrayList<>();
            motifs.add(s);

            for (int i = 1; i < dnas.size(); i++) {
                Profile profile = (withPseudoCounts?formProfileFromMotifs(motifs, true):formProfileFromMotifs(motifs));

                List<String> mostProbableKmer = profile.findMostProbableKmer(dnas.get(i));

                motifs.add(mostProbableKmer.get(0));
            }
            if (score(motifs) < score(bestMotifs)){
                bestMotifs = motifs;
            }
        }

        return bestMotifs;
    }

    public static List<String> randomizedMotifSearch(List<BaseGenome> dnas, int k, int t, boolean withPseudoCounts){
        Random random = new Random();

        List<String> motifs = new ArrayList<>();
        for (BaseGenome bg : dnas) {
            int rnd = random.nextInt(bg.getSymbols().length() - k);
            motifs.add(bg.getSymbols().substring(rnd, rnd + k));
        }

        List<String> bestMotifs = motifs;

        for (int inf = 0; inf < 1000; inf++){
            Profile profile = (withPseudoCounts?formProfileFromMotifs(motifs, true):formProfileFromMotifs(motifs));
            motifs = profile.makeMotifsFromDnas(dnas, k);
            if (score(motifs) < score(bestMotifs)){
                bestMotifs = motifs;
            } else {
                //return bestMotifs;
            }
        }
        return bestMotifs;
    }

    private static int score(List<String> motifs){
        String consensuns = consensuns(motifs);
        int score = 0;
        for (String s : motifs){
            score += HammingDistanceUtil.countHummingDistance(s, consensuns);
        }
        return score;
    }

    private static String consensuns(List<String> motifs){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < motifs.get(0).length(); i++){
            Map<Character, Integer> count = new HashMap<>();
            for (String s : motifs){
                count.put(s.charAt(i), count.getOrDefault(s.charAt(i), 0) + 1);
            }
            int max = -1;
            Character c_max = ' ';
            for (Character c : count.keySet()){
                if (count.get(c) > max){
                    c_max = c;
                    max = count.get(c);
                }
            }
            sb.append(c_max.charValue());
        }
        return sb.toString();
    }

    private static Profile formProfileFromMotifs(List<String> motifs){
        return formProfileFromMotifs(motifs, false);
    }

    private static Profile formProfileFromMotifs(List<String> motifs, boolean withPseudoCounts) {

        Profile profile = new Profile(motifs.get(0).length());

        for (int i = 0; i < motifs.get(0).length(); i++) {
            Map<Character, Double> symbolsDistribution = new HashMap<>();
            for (String motif : motifs) {
                symbolsDistribution.put(motif.charAt(i), symbolsDistribution.getOrDefault(motif.charAt(i), 0.) + 1);
            }

            int numberMotifs = motifs.size() + (withPseudoCounts?4:0);

            profile.set('A', i, (symbolsDistribution.getOrDefault('A', 0.) + (withPseudoCounts?1:0)) / numberMotifs);
            profile.set('C', i, (symbolsDistribution.getOrDefault('C', 0.) + (withPseudoCounts?1:0)) / numberMotifs);
            profile.set('G', i, (symbolsDistribution.getOrDefault('G', 0.) + (withPseudoCounts?1:0)) / numberMotifs);
            profile.set('T', i, (symbolsDistribution.getOrDefault('T', 0.) + (withPseudoCounts?1:0)) / numberMotifs);
        }
        return profile;
    }

    public static List<String> gibbsSamplerIter(List<BaseGenome> dnas, int k, int t, int N, int count){

        int bestScore = Integer.MAX_VALUE;
        List<String> bestMotifs = new ArrayList<>();

        for (int i = 0; i < count; i++){
            List<String> motifs = new ArrayList<>();
            int score = gibbsSampler(dnas, k, t, N, motifs);
            if (score < bestScore){
                bestMotifs.clear();
                bestMotifs.addAll(motifs);
                bestScore = score;
//                for (String s : bestMotifs) System.out.println(s);
//                System.out.println("\n");
            }
        }

        return bestMotifs;
    }

    private static int gibbsSampler(List<BaseGenome> dnas, int k, int t, int N, List<String> forResultMotifs){
        int bestScore = Integer.MAX_VALUE;
        List<String> bestMotifs = new ArrayList<>();
        for (BaseGenome dna : dnas){
            List<String> allKMers = dna.getAllKMers(k);
            bestMotifs.add(allKMers.get(rnd.nextInt(allKMers.size())));
        }

        List<String> motifs = new ArrayList<>(bestMotifs);
        for (int i = 0; i < N; i++){
            int j = rnd.nextInt(motifs.size());
            motifs.remove(j);
            Profile profile = formProfileFromMotifs(motifs, true);
            motifs.add(profile.randomProfileKmer(dnas.get(j), k, rnd));
            int score = score(motifs);
            if (score < bestScore){
                bestMotifs.clear();
                bestMotifs.addAll(motifs);
                bestScore = score;
            }
        }

        forResultMotifs.addAll(bestMotifs);
        return bestScore;
    }

}
