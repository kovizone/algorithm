package com.kovizone.algorithm;

import org.junit.jupiter.api.Test;

/**
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
 *
 * @author KoviChen
 * @version 0.0.1 20190919 KoviChen 新建类
 */
public class Algorithm0011_LeetCode0415_AddStrings {

    @Test
    public void test() {
        System.out.println(addStrings("623", "0"));
    }

    public String addStrings(String num1, String num2) {
        StringBuilder result = new StringBuilder();
        if ("0".equals(num1)) {
            return num2;
        }
        if ("0".equals(num2)) {
            return num1;
        }

        char[] chars1 = num1.toCharArray();
        char[] chars2 = num2.toCharArray();
        int p = num1.length() - 1;
        int q = num2.length() - 1;

        int carry = 0;
        while (p >= 0 || q >= 0) {
            int number1 = p >= 0 ? chars1[p--] : 48;
            int number2 = q >= 0 ? chars2[q--] : 48;
            int num = number1 + number2 + carry - 96;
            carry = num / 10;
            result.insert(0, num % 10);
        }
        if (carry > 0) {
            result.insert(0, carry);
        }

        return result.toString();
    }
}
