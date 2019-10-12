package com.kovizone.algorithm;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class Algorithm0022_LeetCode0003_LongestSubstringWithoutRepeatingCharacters {
    @Test
    public void test() {
        System.out.println(lengthOfLongestSubstring("dvdf"));
    }

    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int max = 0;
        int left = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                left = Math.max(left, map.get(c) + 1);
            }
            map.put(c, i);
            max = Math.max(max, i - left + 1);
        }
        return max;
    }
}
