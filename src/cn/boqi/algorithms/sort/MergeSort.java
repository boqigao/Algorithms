package cn.boqi.algorithms.sort;

import java.util.Arrays;

/**
 * 归并排序MergeSort
 *
 * @author Boqi Gao
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
        //int[] arr = {2,1};

        int[] temp = new int[arr.length]; //归并需要一个额外空间
        mergeSort(arr, 0, arr.length - 1, temp);
        System.out.println(Arrays.toString(arr));


    }

    //分+合方法
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        //如果left>=right，下文就不执行！！！相当于直接返回上一层递归进行上一层的下一条语句
        System.out.println("数组" + Arrays.toString(arr) + "， 调用的左界" + left + ", 调用的右界" + right);
        if (left < right) {
            System.out.println("执行if语句");
            int mid = (left + right) / 2; // 中间索引
            // 向左递归进行分解
            System.out.println("向左递归的左界:" + left + ", 向左递归的右边界:" + mid);
            mergeSort(arr, left, mid, temp);
            // 向右递归进行分解
            int r = mid + 1;
            System.out.println("向右递归的左界:" + r + ", 向右递归的右边界:" + right);
            mergeSort(arr, mid + 1, right, temp);
            // 到合并
            System.out.println("合并的左界:" + left + ", 合并的右边界" + right);
            merge(arr, left, mid, right, temp);
        }
    }

    //先写合并的方法

    /**
     * @param arr   排序的原始数组
     * @param left  左边有序序列的初始索引
     * @param right 最右边的索引
     * @param mid   中部的索引
     * @param temp  放置排完序的中转数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left; // 初始化i，左边有序序列的初始索引
        int j = mid + 1; // 初始化j， 右边你有序序列的初始索引
        int t = 0; //指向temp中转数组的索引

        // （一）
        // 先把左右两边（有序）的数据按照规则填充到temp数组
        // 直到左右两边的有序序列，有一边处理完毕为止
        while (i <= mid && j <= right) { //继续
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                t += 1;
                i += 1;
            } else {
                temp[t] = arr[j];
                t += 1;
                j += 1;
            }
        }

        // (二)
        // 把有剩余数据的一边的数据依次全部填充到temp

        while (i <= mid) {//说明左边的有序序列还有剩余的元素，就全部填充到temp
            temp[t] = arr[i];
            t++;
            i++;
        }

        while (j <= right) {//说明左边的有序序列还有剩余的元素，就全部填充到temp
            temp[t] = arr[j];
            t++;
            j++;
        }


        // （三）
        // 将temp数组的元素拷贝到arr
        // 注意，并不是每次都拷贝所有的
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right) {//第一次合并，temp = 0， right = 1
            arr[tempLeft] = temp[t];
            tempLeft++;
            t++;
        }


    }
}
