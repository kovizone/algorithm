package com.kovizone.algorithm;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.*;

/**
 * 倒水问题<BR/>
 * 若干个水桶，各个水桶为不同的容量（单位：L），第一个水桶是满的<BR/>
 * 需要通过各个水桶互相倒水，倒出所需的水量<BR/>
 * 每次只能倒空或倒满，不能洒出<BR/>
 * 如有8L、5L、3L三个水桶，8L水桶是满的，需要倒出4L的水在某个容器中<BR/>
 *
 * @author KoviChen
 * @version 0.0.1 20190912 KoviChen 新建类
 */
public class Algorithm_001_PourWater {

    private final String CACHE_SPLIT_SYMBOL = "|";

    @Test
    public void test() {
        Bucket[] bucketGroup = new Bucket[3];

        bucketGroup[0] = new Bucket(8, 8);
        bucketGroup[1] = new Bucket(5, 0);
        bucketGroup[2] = new Bucket(3, 0);

        Set<List<Bucket[]>> resultSet = pourWater(4, bucketGroup);

        for (List<Bucket[]> buckets : resultSet) {
            System.out.println(buckets.size() + ": " + toString(buckets));
        }
    }

    /**
     * 倒水算法，控制台打印倒水过程
     * @param result 需要求出的水
     * @param bucketGroup 不同容量的水
     * @return 结果集
     */
    public Set<List<Bucket[]>> pourWater(int result, Bucket[] bucketGroup) {

        List<String> cache = new ArrayList<>(16);

        List<Bucket[]> process = new ArrayList<>();
        process.add(bucketGroup);

        Set<List<Bucket[]>> resultSet = new HashSet<>();
        for (int i = 0; i < bucketGroup.length; i++) {
            algorithm(bucketGroup, i, result, process, cache, resultSet);
        }

        return resultSet;
    }

    /**
     * 具体算法，递归
     * @param bucketGroup 待操作的水桶组
     * @param index 需要操作的水桶索引
     * @param target 目标值
     * @param process 操作过程（水桶组集）
     * @param cache 已经进行过的操作缓存，作为短路条件
     */
    private void algorithm(Bucket[] bucketGroup, int index, int target, List<Bucket[]> process, List<String> cache, Set<List<Bucket[]>> resultSet) {

        // 需要操作的水桶索引获取的水桶并没有剩余水
        if (bucketGroup[index].getHeight() == 0) {
            return;
        }

        // 判断当前操作是否进行过
        if (cache.contains(toString(bucketGroup) + CACHE_SPLIT_SYMBOL + index)) {
            return;
        }


        // 遍历非需要操作水桶索引的水桶，对其进行倒水
        for (int i = 0; i < bucketGroup.length; i++) {
            if (i == index) {
                continue;
            }

            // 避免引用冲突，克隆出一个新的水桶组
            Bucket[] bucketGroupClone = bucketGroup.clone();

            // 被倒水水桶
            Bucket checkBucket = new Bucket(bucketGroupClone[i].getVolume(), bucketGroupClone[i].getHeight());

            // 被倒水水桶已满时
            if (checkBucket.getVolume() == checkBucket.getHeight()) {
                continue;
            }

            // 获取当前索引水桶
            Bucket restBucket = new Bucket(bucketGroup[index].getVolume(), bucketGroup[index].getHeight());

            // 当前索引的水桶进行倒水后剩余量
            int restBucketCurrent = restBucket.getHeight() - checkBucket.getVolume() + checkBucket.getHeight();
            if (restBucketCurrent < 0) {
                restBucketCurrent = 0;
            }
            // 被倒水容器剩余量
            int checkBucketCurrent = (restBucket.getHeight() - restBucketCurrent) + checkBucket.getHeight();

            // 更新两个水桶的剩余量
            checkBucket.setHeight(checkBucketCurrent);
            restBucket.setHeight(restBucketCurrent);
            bucketGroupClone[index] = restBucket;
            bucketGroupClone[i] = checkBucket;

            // 倒水结束后，判断缓存中是否出现过该操作
            if (cache.contains(toString(bucketGroupClone) + CACHE_SPLIT_SYMBOL + index)) {
                continue;
            }

            // 缓存当前操作
            cache.add(toString(bucketGroupClone) + CACHE_SPLIT_SYMBOL + index);

            // 若操作过程中，在这之前，已经出现当前操作后的水容量情况，重置这部分步骤
            List<Bucket[]> processNew = new ArrayList<>(process);
            int itemIndex = -1;
            for (int j = 0; j < processNew.size(); j++) {
                Bucket[] buckets = processNew.get(j);
                if (toString(buckets).equals(toString(bucketGroupClone))) {
                    itemIndex = j;
                    break;
                }
            }
            if (itemIndex < 0) {
                processNew.add(bucketGroupClone);
            } else {
                processNew.subList(itemIndex + 1, processNew.size()).clear();
            }

            // 倒水后两水桶中若存在目标值，则终止
            if (checkBucket.getHeight() == target || restBucket.getHeight() == target) {
                if (resultSet == null) {
                    resultSet = new HashSet<>();
                }
                resultSet.add(processNew);
                return;
            }

            // 递归
            for (int j = 0; j < bucketGroupClone.length; j++) {
                algorithm(bucketGroupClone, j, target, processNew, cache, resultSet);
            }
        }
    }

    private String toString(Bucket[] bucketGroup) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bucketGroup.length; i++) {
            if (i != 0) {
                sb.append(",");
            }

            Bucket bucket = bucketGroup[i];
            sb.append(bucket.toString());
        }
        return sb.toString();
    }

    private String toString(List<Bucket[]> bucketGroupList) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bucketGroupList.size(); i++) {
            if (i != 0) {
                sb.append(",");
            }

            Bucket[] buckets = bucketGroupList.get(i);
            sb.append("[");
            sb.append(toString(buckets));
            sb.append("]");
        }
        return sb.toString();
    }

    /**
     *水桶对象
     */
    public class Bucket {
        /**
         * 容量
         */
        int volume;

        /**
         * 当前水位
         */
        int height;

        @Override
        public String toString() {
            return height + "/" + volume;
        }

        public Bucket(int volume, int height) {
            this.volume = volume;
            this.height = height;
        }

        public int getVolume() {
            return volume;
        }

        public void setVolume(int volume) {
            this.volume = volume;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }

}