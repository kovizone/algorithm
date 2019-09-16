package com.kovizone.algorithm;

import org.junit.jupiter.api.Test;

/**
 * 计算数字 k 在 0 到 n 中的出现的次数，k 可能是 0~9 的一个值。
 *
 * @author KoviChen
 * @version 0.0.1 20190916 KoviChen 新建类
 */
public class Algorithm0004_LintCode0003_DigitCounts {

    @Test
    public void test() {

        long start = 0L;
        int k = 2;
        int n = 15615758;
        System.out.println(String.format("k = %d, n = %d", k, n));
        start = System.currentTimeMillis();
        int a = digitCounts(k, n);
        System.out.println(a);
        System.out.println("用时：" + (System.currentTimeMillis() - start));
        start = System.currentTimeMillis();
        long b = stupidMethod(k, n);
        System.out.println(b);
        System.out.println("用时：" + (System.currentTimeMillis() - start));
        if (a == b) {
            System.out.println("测试通过");
        }
    }

    public int digitCounts(int k, int n) {

        if (n <= 9) {
            if (k <= n) {
                return 1;
            } else {
                return 0;
            }
        }

        // 长度
        int length = String.valueOf(n).length();

        // 游标
        int startNum = 0;
        int count = 0;

        int flag = 1;
        while (flag < length - 1) {

            // x - 涨幅
            int x = 1;
            for (int i = 0; i < length - flag; i++) {
                x *= 10;
            }

            // defaultDetailCount - 每阶段默认增幅
            int defaultDetailCount = x / 10 * (length - flag);

            for (; startNum + x - 1 <= n; startNum += x) {

                String startNumStr = String.valueOf(startNum);
                char[] startNumChars = startNumStr.toCharArray();

                int detailCount = defaultDetailCount;
                for (int i = 0; i < flag; i++) {
                    if (Integer.parseInt(String.valueOf(startNumChars[i])) == k) {
                        detailCount += x;
                    }
                }
                count += detailCount;
            }
            flag++;
        }

        for (long i = startNum; i <= n; i++) {
            char[] chars = String.valueOf(i).toCharArray();
            for (char c : chars) {
                if (Integer.parseInt(String.valueOf(c)) == k) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 笨方法，包证可行，用于验证
     * @param k 需要统计的数
     * @param n 结尾数
     * @return 统计k出现的次数
     */
    public int stupidMethod(int k, int n) {
        return stupidMethod(k, 0, n);
    }

    /**
     * 笨方法，包证可行，用于验证
     * @param k 需要统计的数
     * @param m 起始数
     * @param n 结尾数
     * @return 统计k出现的次数
     */
    public int stupidMethod(int k, int m, int n) {
        int count = 0;

        if (m > n) {
            int flag = m;
            m = n;
            n = flag;
        }

        for (int i = m; i <= n; i++) {
            char[] chars = String.valueOf(i).toCharArray();
            for (char c : chars) {
                if (Integer.parseInt(String.valueOf(c)) == k) {
                    count++;
                }
            }
        }
        return count;
    }
}
