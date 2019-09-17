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
        System.out.println(nthSuperUglyNumber(11, new int[]{2, 3, 5}));
    }

    public int nthSuperUglyNumber(int n, int[] primes) {

        Map<Integer, Integer> map = new HashMap<>(16);
        for (int i = 0; i < primes.length; i++) {
            map.put(primes[i], 0);
        }

        int[] ugly = new int[n];
        ugly[0] = 1;

        int minNum = 1;
        for (int i = 1; i < n; i++) {

            int[] minNums = new int[primes.length];
            for (int j = 0; j < primes.length; j++) {
                minNums[j] = ugly[map.get(primes[j])] * primes[j];
            }

            minNum = minNums[0];
            for (int j = 1; j < minNums.length; j++) {
                if (minNum > minNums[j]) {
                    minNum = minNums[j];
                }
            }
            if (i == n - 1) {
                return minNum;
            }

            for (int j = 0; j < minNums.length; j++) {
                if (minNum == minNums[j]) {
                    map.put(primes[j], map.get(primes[j]) + 1);
                }
            }
            ugly[i] = minNum;
        }
        return minNum;
    }
}
