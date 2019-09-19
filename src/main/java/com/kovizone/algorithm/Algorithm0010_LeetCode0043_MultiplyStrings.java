package com.kovizone.algorithm;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 *
 * @author KoviChen
 * @version 0.0.1 20190919 KoviChen 新建类
 */
public class Algorithm0010_LeetCode0043_MultiplyStrings {

    @Test
    public void test() {
        System.out.println(multiply("123", "456"));
    }

    public String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        int length1 = num1.length();
        int length2 = num2.length();
        char[] chars1 = num1.toCharArray();
        char[] chars2 = num2.toCharArray();
        StringBuilder result = new StringBuilder();
        int[] array = new int[length1 + length2 - 1];
        int carry = 0;
        for (int i = length1 - 1; i >= 0; i--) {
            for (int j = length2 - 1; j >= 0; j--) {
                array[(length1 - 1 - i) + (length2 - 1 - j)] += (chars1[i] - 48) * (chars2[j] - 48);
            }

            int num = array[length1 - 1 - i] + carry;
            result.insert(0, num % 10);
            carry = num / 10;
        }
        for (int i = length1; i < array.length; i++) {
            int num = array[i] + carry;
            result.insert(0, num % 10);
            carry = num / 10;
        }
        if (carry > 0) {
            result.insert(0, carry);
        }
        return result.toString();
    }
}
