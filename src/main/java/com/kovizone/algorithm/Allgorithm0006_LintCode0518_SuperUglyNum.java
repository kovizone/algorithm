package com.kovizone.algorithm;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 写一个程序来找第 n 个超级丑数。
 *
 * 超级丑数是所有的质数因子都在给定的的质数集合内的正整数。
 *
 * 比如给定质数集合 [2, 7, 13, 19], 那么 [1, 2, 4, 7, 8, 13, 14, 16, 19, 26, 28, 32] 是前 12 个超级丑数。
 *
 * @author KoviChen
 * @version 0.0.1 20190917 KoviChen 新建类
 */
public class Allgorithm0006_LintCode0518_SuperUglyNum {

    @Test
    public void test() {
        System.out.println(nthSuperUglyNumber(6, new int[]{2, 7, 13, 19}));
    }

    public int nthSuperUglyNumber(int n, int[] primes) {

        int[] indexArray = new int[primes.length];
        int[] ugly = new int[n];
        ugly[0] = 1;

        for (int i = 1; i < n; i++) {
            int min = 1;
            int[] bucket = new int[primes.length];
            for (int j = 0; j < primes.length; j++) {
                bucket[j] = ugly[indexArray[j]] * primes[j];
            }

            min = bucket[0];
            for (int j = 1; j < bucket.length; j++) {
                if (min > bucket[j]) {
                    min = bucket[j];
                }
            }

            for (int j = 0; j < bucket.length; j++) {
                if (min == bucket[j]) {
                    indexArray[j] = indexArray[j] + 1;
                }
            }
            ugly[i] = min;
        }
        return ugly[n - 1];
    }
}
