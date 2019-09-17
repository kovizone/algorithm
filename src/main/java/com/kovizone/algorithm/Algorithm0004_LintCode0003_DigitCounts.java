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

        int k = 2;
        int n = 15615758;

        long start = 0L;

        System.out.println(String.format("k = %d, n = %d", k, n));
        start = System.currentTimeMillis();
        int a = digitCounts(k, n);
        System.out.println("digitCounts result: " + a);
        System.out.println("用时：" + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        long b = stupidMethod(k, n);
        System.out.println("stupidMethod result: " + b);
        System.out.println("用时：" + (System.currentTimeMillis() - start));
        if (a == b) {
            System.out.println("测试通过");
        } else {
            System.out.println("测试不通过");
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

        // 统计结果
        int count = 0;

        // 长度
        int length = String.valueOf(n).length();

        // 计算n的长度减一位数的最大值对k的统计
        // 如：若n是四位数，根据规律获得0~999的k统计
        int lengthFlag = length - 1;
        if (lengthFlag == 1) {
            count++;
        } else if (lengthFlag == 2) {
            count += (k == 0 ? 10 : 20);
        } else {
            if (k == 0) {
                StringBuilder sb = new StringBuilder();
                sb.append(lengthFlag - 2);
                for (int i = 0; i < lengthFlag - 3; i++) {
                    sb.append(8);
                }
                sb.append(90);
                count += Integer.parseInt(sb.toString());
            } else {
                count = (int) Math.pow(10, (lengthFlag - 1)) * lengthFlag;
            }
        }

        // 游标，以为已经获取了n-1位数最大值的统计数，所以游标从n的长度位最小数开始
        // 如n是四位数，则游标从1000开始，这是4位数中的最小数
        int vernier = (int) Math.pow(10, lengthFlag);

        while (lengthFlag > 1) {

            // vernierNext - 游标每次递进的值
            // 刚好该值是规律中统计的增值
            int vernierNext = (int) Math.pow(10, lengthFlag);

            // defaultCount - 每阶段默认增幅
            int defaultCount = (int) Math.pow(10, (lengthFlag - 1)) * lengthFlag;

            while (vernier + vernierNext - 1 <= n) {
                char[] startNumChars = String.valueOf(vernier).toCharArray();
                for (int i = 0; i < length - lengthFlag; i++) {
                    if (Integer.parseInt(String.valueOf(startNumChars[i])) == k) {
                        count += vernierNext;
                    }
                }
                count += defaultCount;
                vernier += vernierNext;
            }
            lengthFlag--;
        }

        // 计算剩余数
        for (long i = vernier; i <= n; i++) {
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
