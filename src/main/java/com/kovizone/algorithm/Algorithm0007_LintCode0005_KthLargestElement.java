package com.kovizone.algorithm;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 在数组中找到第 k 大的元素。
 * @author KoviChen
 * @version 0.0.1 20190918 KoviChen 新建类
 */
public class Algorithm0007_LintCode0005_KthLargestElement {

    @Test
    public void test() {
        int[] nums = {2, 88, 8, 105, 3, 5, 16, 4, 1, 36, 13, 44};
        System.out.println(kthLargestElement(2, nums));
        for (int i : nums) {
            System.out.print(i + " ");
        }
    }

    public int kthLargestElement(int n, int[] nums) {
        quickSort(nums, 0, nums.length - 1, n - 1);
        return nums[n - 1];
    }


    private void quickSort(int[] nums, int start, int end, int n) {

        if (start >= end || start >= n) {
            return;
        }
        System.out.println(Arrays.toString(nums));

        int i = start, j = end, x = nums[i];
        while (i < j) {
            while (i < j && nums[j] <= x) {
                j--;
            }
            if (i < j) {
                nums[i++] = nums[j];
            }

            while (i < j && nums[i] > x) {
                i++;
            }
            if (i < j) {
                nums[j--] = nums[i];
            }
        }
        nums[i] = x;

        // 递归调用
        quickSort(nums, start, i - 1, n);
        quickSort(nums, i + 1, end, n);
    }
}
