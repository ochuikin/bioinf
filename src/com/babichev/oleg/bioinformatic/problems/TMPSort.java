package com.babichev.oleg.bioinformatic.problems;

/**
 * Created by olegchuikin on 22/02/16.
 */
public class TMPSort {

    public static void main(String[] args) {
        int[] arr =  new int[]{1, 4, 2, 9, 3, 8, 0, 7, 6, 5, 10};


        int result[] = mergeSort(arr);

        for (int a : result){
            System.out.printf(a + " ");
        }
    }

    public static int[] mergeSort(int[] data){
        if (data.length > 1) {
            int[][] splited = split(data);
            int[] left = mergeSort(splited[0]);
            int[] right = mergeSort(splited[1]);
            return merge(left, right);
        } else {
            return data;
        }
    }

    public static int[][] split(int[] data){
        int[][] result = new int[2][];
        result[0] = new int[data.length/2];
        result[1] = new int[data.length/2+data.length%2];
        for(int i = 0; i < data.length/2; i++){
            result[0][i] = data[i];
        }
        for(int i = data.length/2; i < data.length; i++){
            result[1][i-data.length/2] = data[i];
        }
        return result;
    }

    public static int[] merge(int[] data1, int[] data2){
        int[] result = new int[data1.length+data2.length];
        int i = 0;
        int j = 0;
        while(i+j < data1.length + data2.length) {
            if (j >= data2.length || i < data1.length && data1[i] < data2[j]) {
                result[i + j] = data1[i];
                i++;
            } else if (j < data2.length) {
                result[i + j] = data2[j];
                j++;
            }
        }
        return result;
    }

    public static int[] sort (int[] data){
        for(int i = 0; i < data.length; i++){
            for(int j = i; j > 0; j--){
                if(data[j-1] < data[j]){
                    int h = data[j-1];
                    data[j-1] = data[j];
                    data[j] = h;
                }else{
                    break;
                }
            }

        }
        return data;
    }


}
