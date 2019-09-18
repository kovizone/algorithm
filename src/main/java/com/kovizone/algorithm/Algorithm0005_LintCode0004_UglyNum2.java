package com.kovizone.algorithm;

import org.junit.jupiter.api.Test;

/**
 * 设计一个算法，找出只含素因子2，3，5 的第 n 小的数。
 *
 * 符合条件的数如：1, 2, 3, 4, 5, 6, 8, 9, 10, 12...
 *
 * 我们可以认为 1 也是一个丑数。
 *
 * @author KoviChen
 * @version 0.0.1 20190917 KoviChen x新建类
 */
public class Algorithm0005_LintCode0004_UglyNum2 {

    @Test
    public void test() {
        System.out.println(nthUglyNumber(41));
    }

    public int nthUglyNumber(int n) {

        int num2index = 0;
        int num3index = 0;
        int num5index = 0;

        int[] ugly = new int[n];
        ugly[0] = 1;

        int minNum = 1;
        for (int i = 1; i < n; i++) {
            int mul2Num = ugly[num2index] * 2;
            int mul3Num = ugly[num3index] * 3;
            int mul5Num = ugly[num5index] * 5;

            minNum = mul2Num < mul3Num ? mul2Num : mul3Num;
            minNum = minNum < mul5Num ? minNum : mul5Num;
            ugly[i] = minNum;

            if (mul2Num == minNum) {
                num2index++;
            }
            if (mul3Num == minNum) {
                num3index++;
            }
            if (mul5Num == minNum) {
                num5index++;
            }
        }

        return minNum;
    }
}
