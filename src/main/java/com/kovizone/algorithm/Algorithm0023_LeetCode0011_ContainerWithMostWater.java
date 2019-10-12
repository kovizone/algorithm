package com.kovizone.algorithm;

import org.junit.jupiter.api.Test;

public class Algorithm0023_LeetCode0011_ContainerWithMostWater {

    @Test
    public void test() {
        System.out.println(maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
    }

    public int maxArea(int[] height) {
        int maxArea = 0;
        int l = 0;
        int r = height.length - 1;
        while (l < r) {
            int leftBorder = height[l];
            int rightBorder = height[r];
            maxArea = Math.max(maxArea, Math.min(leftBorder, rightBorder) * (r - l));
            if (leftBorder < rightBorder) {
                l++;
            } else {
                r--;
            }
        }
        return maxArea;
    }
}
