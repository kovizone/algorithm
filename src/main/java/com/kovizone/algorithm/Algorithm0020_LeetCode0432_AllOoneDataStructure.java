package com.kovizone.algorithm;

/**
 * 全 O(1) 的数据结构
 *
 * @author KoviChen
 * @version 0.0.1 20191010 KoviChen 新建类
 */
public class Algorithm0020_LeetCode0432_AllOoneDataStructure {

    public static void main(String[] args) {
        String key = "test";
        AllOne obj = new AllOne();
        obj.inc(key);
        obj.dec(key);
        String param_3 = obj.getMaxKey();
        String param_4 = obj.getMinKey();
    }
}


class AllOne {

    private String maxKey;
    private String minKey;

    public AllOne() {

    }

    public void inc(String key) {

    }

    public void dec(String key) {

    }

    public String getMaxKey() {
        return maxKey;
    }

    public String getMinKey() {
        return minKey;
    }
}