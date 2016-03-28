package com.babichev.oleg.bioinformatic.problems;

import com.babichev.oleg.bioinformatic.utils.GraphUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by olegchuikin on 29/03/16.
 */
public class Problem29 {
    public static void main(String[] args) {
        File file = new File("data");

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            List<String> lines = new ArrayList<>();
            while ((line = br.readLine()) != null){
                lines.add(line);
            }

            GraphUtils graphUtils = GraphUtils.parseDirectedGraph(lines);
            System.out.println(graphUtils.print());

//            System.out.println(graphUtils.findEulerPath());
            System.out.println(graphUtils.printEulerPath());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
