package cn.boqi.algorithms.sort;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 插入排序InsertSort的代码实现和速度测试
 *
 * @author Boqi Gao
 */
public class InsertSort {
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
        insertSort(arr);

        System.out.println("排序后时间");
        Date date2 = new Date();
        String dataStr2 = simpleDateFormat.format(date2);
        System.out.println(dataStr2);
    }

    public static void insertSort(@NotNull int[] arr) {
        //使用逐步推到的方式来讲解，便于理解
        //第一轮{101}

        //定义待插入的数
        for (int i = 1; i < arr.length; i++) {
            //insertVal 是无序部分的第一个数字, 我们用insertVal来保存这个值，然后让比他大的都后移
            //insertIndex 是无需部分前面的一个数字的下标
            int insertVal = arr[i];
            int insertIndex = i - 1;

            //给insertVal找到插入的位置
            //说明
            //1. insertIndex >=0 保证给insertVal找到插入的位置，不越界!
            //2. insertVal <arr[insertIndex]带插入的数字，还没有招待插入的位置!
            //3.  需要将arr【insertIndex】后移
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            if (insertIndex + 1 == i) {
                arr[insertIndex + 1] = insertVal;
            }
        }

    }
}
