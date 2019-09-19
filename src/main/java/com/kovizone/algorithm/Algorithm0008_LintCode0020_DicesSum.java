package com.kovizone.algorithm;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 骰子求合
 *
 * 扔 n 个骰子，向上面的数字之和为 S。给定 n，请列出所有可能的 S 值及其相应的概率。
 *
 * @author KoviChen
 * @version 0.0.1 20190919 KoviChen 新建类
 */
public class Algorithm0008_LintCode0020_DicesSum {

    @Test
    public void test() {

        int n = 5;

        int[] ns = mul(null, n);
        Arrays.sort(ns);

        int[] array = new int[6 * n + n - 1];
        for (int i = 0; i < ns.length; i++) {
            array[ns[i]]++;
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i] > 0) {
                System.out.println(i + "\t: \t" + array[i]);
            }
        }
        System.out.println(Arrays.toString(ns));
    }

    public List<Map.Entry<Integer, Double>> dicesSum(int n) {

        int m = n * 6;

        int[] nums = new int[m];

        int[] dices = new int[]{1, 2, 3, 4, 5, 6};


        int index = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= 6; j++) {

            }
        }

        return null;
    }

    public int[] mul(int[] nums, int n) {
        if (n <= 1) {
            return nums;
        }
        if (nums == null) {
            nums = new int[]{1, 2, 3, 4, 5, 6};
        }

        int[] ns = new int[nums.length * 6];
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 1; j <= 6; j++) {
                ns[index++] = nums[i] + j;
            }
        }

        return mul(ns, n - 1);
    }
}
