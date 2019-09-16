package com.kovizone.algorithm;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Algorithm0003_LintCode0002_TrailingZeros {

    @Test
    public void test() {
        int n = 126;

        String line = factorial(n);
        System.out.println(String.format("factorial(%d): %s", n, line));
        String pattern = "0+\\b";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(line);
        if (m.find()) {
            System.out.println(String.format("Trailing Zeros: %d", m.group(0).length()));
        }

        System.out.println(String.format("\ntrailingZeros(%d): %d", n, trailingZeros(n)));
    }

    public int trailingZeros(int n) {
        return n < 5 ? 0 : n / 5 + trailingZeros(n / 5);
    }

    /**
     * 阶乘
     * @param n 阶乘
     * @return 阶乘结果
     */
    public String factorial(int n) {
        return n == 1 ? "1" : new BigDecimal(n).multiply(new BigDecimal(factorial(n - 1))).toString();
    }
}
