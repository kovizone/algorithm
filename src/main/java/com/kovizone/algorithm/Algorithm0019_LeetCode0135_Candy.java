package com.kovizone.algorithm;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class Algorithm0019_LeetCode0135_Candy {

    @Test
    public void test() {
        System.out.println(candy(new int[]{3, 1, 4, 3, 2, 1, 4}));
    }

    public int candy(int[] ratings) {
        int length = ratings.length;
        if (length == 1) {
            return 1;
        }
        if (length == 2) {
            if (ratings[0] == ratings[1]) {
                return 2;
            }
            return 3;
        }

        int result = 0;

        int[] candy = new int[length];
        for (int i = 0; i < candy.length; i++) {
            candy[i]++;
        }

        candy = myMethod(ratings, candy, 0);
        System.out.println(Arrays.toString(candy));
        for (int c : candy) {
            result += c;
        }

        return result;
    }

    /**
     * 失败，内存溢出
     * @param ratings
     * @param candy
     * @param index
     * @return
     */
    private int[] myMethod(int[] ratings, int[] candy, int index) {
        // 头
        if (index == 0) {
            if (ratings[0] > ratings[1]) {
                candy[0]++;
            }
        }

        // 尾
        else if (index == ratings.length - 1) {
            if (ratings[index] > ratings[index - 1] && candy[index - 1] <= candy[index]) {
                candy[index] = candy[index - 1] + 1;
            }
            return candy;
        }

        // 中间
        else {
            if (ratings[index] > ratings[index + 1]) {
                if (candy[index] <= candy[index + 1]) {
                    candy[index] = candy[index + 1] + 1;
                }
            }
            if (ratings[index] > ratings[index - 1]) {
                if (candy[index] <= candy[index - 1]) {
                    candy[index] = candy[index - 1] + 1;
                }
            }
            if (ratings[index] < ratings[index - 1] && candy[index] >= candy[index - 1]) {
                candy = myMethod(ratings, candy, index - 1);
            }
        }

        return myMethod(ratings, candy, index + 1);
    }
}
