package com.babichev.oleg.bioinformatic.utils;

/**
 * Created by olegchuikin on 14/02/16.
 */
public class FibUtils {

    public static long nkFibNumber(int n, int k) {
        switch (n) {
            case 1:
                return 1;
            case 2:
                return 1 + k;
            default:
                return nkFibNumber(n - 1, k) + k * nkFibNumber(n - 2, k);
        }
    }

}
