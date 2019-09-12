package com.kovizone.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 倒水问题<BR/>
 * 若干个水桶，各个水桶为不同的容量（单位：L），第一个水桶是满的<BR/>
 * 需要通过各个水桶互相倒水，倒出所需的水量<BR/>
 * 每次只能倒空或倒满，不能浪费水<BR/>
 * 如有8L、5L、3L三个水桶，8L水桶是满的，需要倒出4L的水在某个容器中<BR/>
 *
 * @author KoviChen
 * @version 0.0.1 20190912 KoviChen 新建类
 */
public class Algorithm_001_PourWater {

    public static void main(String[] args) {
        ArrayList<Bucket> buckets = new ArrayList<>();

        buckets.add(new Algorithm_001_PourWater().new Bucket(8, 8));
        buckets.add(new Algorithm_001_PourWater().new Bucket(5, 0));
        buckets.add(new Algorithm_001_PourWater().new Bucket(3, 0));
        //buckets.add(new Algorithm_001_PourWater().new Bucket(1, 0));

        pourWater(4, buckets);
    }

    /**
     * 倒水算法，控制台打印倒水过程
     * @param result 需要求出的水
     * @param buckets 不同容量的水
     */
    public static void pourWater(int result, List<Bucket> buckets) {

        List<String> cache = new ArrayList<>();


        int bucketsSize = buckets.size();

        List<String> process = new ArrayList<>();
        process.add(buckets.toString());

        for (int i = 0; i < bucketsSize; i++) {
            algorithm(buckets, i, result, process, cache);
        }

    }

    private static void algorithm(List<Bucket> buckets, int index, int result, List<String> process, List<String> cache) {
        Bucket currentBucket = buckets.get(index);


        if (currentBucket.getHeight() == 0) {
            if (result == 0) {
                System.out.println(process);
            }
            return;
        }

        if (cache.contains(buckets.toString() + "|" + index)) {
            return;
        }


        for (int i = 0; i < buckets.size(); i++) {
            if (i == index) {
                continue;
            }

            ArrayList<Bucket> bucketNew = new ArrayList<>(buckets);
            Bucket checkBucket = new Algorithm_001_PourWater().new Bucket(bucketNew.get(i).getVolume(), bucketNew.get(i).getHeight());
            Bucket restBucket = new Algorithm_001_PourWater().new Bucket(currentBucket.getVolume(), currentBucket.getHeight());

            if (checkBucket.getVolume() == checkBucket.getHeight()) {
                continue;
            }

            int restBucketCurrent = restBucket.getHeight() - checkBucket.getVolume() + checkBucket.getHeight();
            if (restBucketCurrent < 0) {
                restBucketCurrent = 0;
            }
            int checkBucketCurrent = (restBucket.getHeight() - restBucketCurrent) + checkBucket.getHeight();

            checkBucket.setHeight(checkBucketCurrent);
            restBucket.setHeight(restBucketCurrent);

            bucketNew.set(index, restBucket);
            bucketNew.set(i, checkBucket);

            if (cache.contains(bucketNew.toString() + "|" + index)) {
                continue;
            } else {
                cache.add(bucketNew.toString() + "|" + index);
            }

            List<String> processNew = new ArrayList<>(process);
            String processItem = bucketNew.toString();
            if (!processNew.contains(processItem)) {
                processNew.add(processItem);

            } else {
                int itemIndex = processNew.indexOf(processItem);
                for (int j = processNew.size() - 1; j > itemIndex; j--) {
                    processNew.remove(j);
                }
            }
            if (checkBucket.getHeight() == result || restBucket.getHeight() == result) {
                System.out.println(processNew.toString());
                return;
            }

            for (int j = 0; j < bucketNew.size(); j++) {
                algorithm(bucketNew, j, result, processNew, cache);
            }
        }
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