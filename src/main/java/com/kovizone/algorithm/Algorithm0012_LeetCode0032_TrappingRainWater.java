package com.kovizone.algorithm;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 * @author KoviChen
 * @version 0.0.1 20190920 KoviChen 新建类
 */
public class Algorithm0012_LeetCode0032_TrappingRainWater {

    @Test
    public void test() {
        System.out.println(trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    }

    /**
     * 官方最优解
     * 时间：O(n)
     * 空间：O(1)
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int sum = 0;
        int left = 0;
        int right = height.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        while (left < right) {
            if (height[left] <= height[right]) {
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    sum += leftMax - height[left];
                }
                ++left;
            } else {
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    sum += rightMax - height[right];
                }
                --right;
            }
        }
        return sum;
    }

    /**
     * 原创
     *
     * 时间 O(n)
     * 空间 O(n)
     * @param height
     * @return
     */
    public int trap2(int[] height) {
        int[] array = new int[height.length];

        int sum = 0;
        int max = 0;
        for (int i = 0; i < height.length; i++) {

            if (height[i] > max) {
                max = height[i];
            } else if (max - height[i] > 0) {
                array[i] = max - height[i];
                sum += array[i];
            }
        }

        max = 0;
        for (int i = height.length - 1; i >= 1; i--) {
            if (height[i] > max) {
                max = height[i];
                sum -= array[i];
            } else if (max - height[i] >= 0 && max - height[i] < array[i]) {
                sum -= array[i] - max + height[i];
            }
        }

        return sum;
    }
}
