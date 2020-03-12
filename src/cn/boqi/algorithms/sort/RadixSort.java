package cn.boqi.algorithms.sort;

import java.util.Arrays;

/**
 * 基数排序
 * 注意点：基数排序数据过多的时候，很可能会内存不足
 *
 * @author Boqi Gao
 */
public class RadixSort {
    public static void main(String[] args) {
        int[] arr = {53, 3, 542, 748, 14, 214};
        radixSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    //基数排序方法
    public static void radixSort(int[] arr) {

        // 1. 得到数组中最大的数的位数
        int max = arr[0]; //假设第一数就是最大数
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        int maxLength = ("" + max).length();

        // 第一轮（针对每个元素的各位进行排序处理）

        // 定义一个二维数组，表示10个同，每个桶就是一个一维数组
        // 说明
        // 1. 二维数组包含10个一维数组
        // 2. 为了防止在放入数的时候数据溢出，则每个一维数组定义为arr.length
        // 3. 很明显，基数排序是使用空间换时间的经典算法
        int[][] bucket = new int[10][arr.length]; //一个桶最多也只可能有arr.length这么多数字

        // 为了记录每个桶中， 实际存放了多少个数据，我们定义一个一维数组来记录各个
        // 桶每次放入的数据的个数
        // bucketElementCount[0]记录的就是bucket[0]桶中放入数据的个数
        int[] bucketElementCounts = new int[10];
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) { //很关键！！
            for (int j = 0; j < arr.length; j++) {
                //取出每个元素的各位进行排序，第一次是各位，第二次是十位，第三次是百位
                int digitOfElement = arr[j] / n % 10;
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }

            int index = 0;
            //遍历每一个桶，并将桶中的数据，放入到原数组
            for (int k = 0; k < bucketElementCounts.length; k++) {
                //如果桶中有数据，我们才放入到原数组
                if (bucketElementCounts[k] != 0) {
                    //循环该桶，即第k个桶（即第k个一维数组），放入
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        //取出元素放入到arr中
                        arr[index++] = bucket[k][l];
                    }
                }

                //第i+1轮处理后，需要将原来的数字归零
                bucketElementCounts[k] = 0;
            }
        }


    }
}
