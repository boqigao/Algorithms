package cn.boqi.algorithms.sort;

import java.util.Arrays;

/**
 * 希尔排序：插入排序的进步版本
 *
 * @author Boqi Gao
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};

        ShellSortExchangeVersion(arr);
        System.out.println(Arrays.toString(arr));
    }

    //使用逐步推导的方式来编写，学习希尔排序
    public static void ShellSortLearn(int[] arr) {
        int temp = 0;
        //希尔排序的第一轮排序
        //因为第一轮排序，是将十个数据分成了5组，
        for (int i = 5; i < arr.length; i++) {
            //遍历各组中所有的元素（共有5组，每组有2个元素），步长5， j要到大于0为止
            for (int j = i - 5; j >= 0; j -= 5) {
                //如果当前元素大于加上步长后的那个元素，说明需要交换
                if (arr[j] > arr[j + 5]) {
                    temp = arr[j];
                    arr[j] = arr[j + 5];
                    arr[j + 5] = temp;
                }
            }
        }

        for (int i = 2; i < arr.length; i++) {
            //遍历各组中所有的元素（共有5组，每组有2个元素），步长5， j要到大于0为止
            for (int j = i - 2; j >= 0; j -= 2) {
                //如果当前元素大于加上步长后的那个元素，说明需要交换
                if (arr[j] > arr[j + 2]) {
                    temp = arr[j];
                    arr[j] = arr[j + 2];
                    arr[j + 2] = temp;
                }
            }
        }

        for (int i = 1; i < arr.length; i++) {
            //遍历各组中所有的元素（共有5组，每组有2个元素），步长5， j要到大于0为止
            for (int j = i - 1; j >= 0; j -= 1) {
                //如果当前元素大于加上步长后的那个元素，说明需要交换
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }


    //用交换的方式来做希尔排序：其实就是希尔+冒泡
    public static void ShellSortExchangeVersion(int[] arr) {
        int temp = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                //遍历各组中所有的元素（共有gap组，每组有arr.length/2个元素），步长gap， j要到大于0为止
                for (int j = i - gap; j >= 0; j -= gap) {
                    //如果当前元素大于加上步长后的那个元素，说明需要交换
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
        }
    }

    //对交换式希尔排序进行优化->移位法
    public static void ShellSortChangePosition(int[] arr) {

        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            //从第gap个元素，逐个对其所在的组进行插入排序
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];
                if (arr[j] < arr[j - gap]) {
                    //下面其实就是插入排序
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        //移动
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    //当提出while循环以后，就相当于给temp找到了插入的位置j
                    arr[j] = temp;
                }
            }
        }
    }
}
