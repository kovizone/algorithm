package com.kovizone.algorithm;

import org.junit.jupiter.api.Test;

public class Algorithm0014_LeetCode0233_NumberOfDigitOne {

    @Test
    public void test() {

        int n = 1000;

        long start;

        System.out.println(String.format("n = %d", n));
        start = System.currentTimeMillis();
        int a = countDigitOne(n);
        System.out.println("digitCounts result: " + a);
        System.out.println("用时：" + (System.currentTimeMillis() - start));

    }

    public int countDigitOne(int n) {
        int countr = 0;
        for (int i = 1; i <= n; i *= 10) {
            int divider = i * 10;
            countr += ((n / divider) * i + Math.min(Math.max(n % divider - i + 1, 0), i));
        }
        return countr;
    }
}
