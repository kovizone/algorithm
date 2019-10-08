package com.kovizone.algorithm;

import org.junit.jupiter.api.Test;

/**
 * 实现一个基本的计算器来计算一个简单的字符串表达式的值。
 *
 * 字符串表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格  。 整数除法仅保留整数部分。
 *
 * @author KoviChen
 * @version 0.0.1 20191008 KoviChen 新建类
 */
public class Algorithm0016_LeetCode0227_BasicCalculatorII {

    @Test
    public void test() {
        String s = "";
        s = "1-(2-(1-2))";
        System.out.println(String.format("%s = %d", s, calculate(s)));
    }

    public int calculate(String s) {
        char[] numbers = s.toCharArray();

        // 结果
        int result = 0;

        //运算符
        char operator = '+';

        // 栈
        char[] stack = new char[numbers.length / 2];
        int stackTop = 0;

        int num = 0;

        for (int i = 0; i < numbers.length; i++) {
            char point = numbers[i];
            if (point >= 48 && point <= 57) {
                num = num * 10 + (point - 48);
            } else if (point != ' ') {
                result = compute(result, operator, num);
                num = 0;
            }
            if (point == '+') {
                if (stackTop > 0) {
                    if (stack[stackTop - 1] == '-') {
                        operator = '-';
                    }
                    if (stack[stackTop - 1] == '+') {
                        operator = '+';
                    }
                } else {
                    operator = '+';
                }
            }
            if (point == '-') {
                if (stackTop > 0) {
                    if (stack[stackTop - 1] == '-') {
                        operator = '+';
                    }
                    if (stack[stackTop - 1] == '+') {
                        operator = '-';
                    }
                } else {
                    operator = '-';
                }
            }
            if (point == '(') {
                stack[stackTop++] = operator;
            }
            if (point == ')') {
                stackTop--;
            }
        }
        return compute(result, operator, num);
    }

    /**
     * 计算
     * @param left 左数
     * @param operator 计算符
     * @param right 右数
     * @return 计算结果
     */
    private int compute(int left, char operator, int right) {
        if (right != 0) {
            if (operator == '+') {
                return left + right;
            }
            if (operator == '-') {
                return left - right;
            }
        }
        return left;
    }
}
