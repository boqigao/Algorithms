package cn.boqi.algorithms.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * BubbleSort:冒泡排序代码实现以及优化
 *
 * @author Boqi Gao
 */
public class BubbleSort {
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
        bubbleSort(arr);

        System.out.println("排序后时间");
        Date date2 = new Date();
        String dataStr2 = simpleDateFormat.format(date2);
        System.out.println(dataStr2);
    }

    public static void bubbleSort(int[] arr) {
        int temp = 0;//临时变量
        boolean flag = false; //标识符，标志变量，表示是否进行过交换

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if (flag == false) { //在一堂排序中，一次交换都没有交换过
                System.out.println("交换了" + i + "趟就结束啦！");
                break;
            } else {
                flag = false; //这里必须要重置Flag！！
                //不然flag在一次换完以后就永远是true，就没有意义了
            }
        }
    }
}
