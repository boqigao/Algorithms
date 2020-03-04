package cn.boqi.algorithms.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 选择排序SelectSort的代码实现以及测试
 * 80000个元素大约耗时2秒，是交换排序的1/10
 *
 * @author Boqi Gao
 */
public class SelectSort {
    public static void main(String[] args) {

        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 800000);
        }
        System.out.println("排序前时间");
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String data1Str = simpleDateFormat.format(date1);
        System.out.println(data1Str);
        selectSort(arr);

        System.out.println("排序后时间");
        Date date2 = new Date();
        String dataStr2 = simpleDateFormat.format(date2);
        System.out.println(dataStr2);

    }

    public static void selectSort(int[] arr) {


        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i; //一定要在每次开始时候重置！！！！
            int min = arr[i];
            for (int j = i; j < arr.length; j++) {
                if (min > arr[j]) {//说明假定的最小值并不是最小
                    min = arr[j]; //重置min
                    minIndex = j; //重置minIndex

                }
            }
            //进行交换，将最小值放在arr【0】这个位置
            arr[minIndex] = arr[i];
            arr[i] = min;
        }
    }
}
