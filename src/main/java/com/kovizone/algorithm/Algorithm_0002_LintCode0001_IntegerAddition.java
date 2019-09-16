package com.kovizone.algorithm;

import org.junit.jupiter.api.Test;

/**
 * 不使用加运算符进行整形计算
 */
public class Algorithm_0002_LintCode0001_IntegerAddition {

    @Test
    public void test() {
        int a = 3;
        int b = -6;

        int sum = add(a, b);

        System.out.println(String.format("    a = %d (%s)", a, Integer.toBinaryString(a)));
        System.out.println(String.format("    b = %d (%s)", b, Integer.toBinaryString(b)));
        System.out.println(String.format("a + b = %d (%s)", sum, Integer.toBinaryString(sum)));
    }

    public int add(int a, int b) {
        return b == 0 ? a : add(a ^ b, (a & b) << 1);
    }

}
