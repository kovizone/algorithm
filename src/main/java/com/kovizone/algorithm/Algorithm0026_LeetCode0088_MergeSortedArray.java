package com.kovizone.algorithm;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author <a href="mailto:kovichen@163.com">KoviChen</a>
 * @version 1.0
 */
public class Algorithm0026_LeetCode0088_MergeSortedArray {

    @Test
    public void test() {
        int[] nums1 = new int[]{2, 0};
        int[] nums2 = new int[]{1};
        merge(nums1, nums1.length - nums2.length, nums2, nums2.length);
        System.out.println("结果");
        System.out.println(Arrays.toString(nums1));
        System.out.println(Arrays.toString(nums2));
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1;
        int p2 = n - 1;
        int p = nums1.length - 1;
        while (p1 >= 0 && p2 >= 0) {
            nums1[p--] = nums1[p1] >= nums2[p2] ? nums1[p1--] : nums2[p2--];
        }
        System.arraycopy(nums2, 0, nums1, 0, p2 + 1);
    }
}
