package cn.boqi.algorithms.sort;

import java.util.Arrays;

/**
 * 快速排序代码实现
 *
 * @author Boqi Gao
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {-9, 78, 0, 0, 23, -567, 0, 0, 0, 70};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));

    }

    public static void quickSort(int[] arr, int left, int right) {
        int l = left; // 左边的下标
        int r = right; // 右边的下标
        // pivot是中轴的意思
        // pivot是中轴值
        int pivot = arr[(left + right) / 2];
        int temp = 0;//临时变量，作为交换时候使用
        // while循环的目的是让比pivot小的值放到左边，比pivot大的值放到右边
        while (l < r) {

            while (arr[l] < pivot) {
                // 在pivot的左边一直找，直到找到一个比pivot大于等于的值才退出
                l += 1;
            }
            while (arr[r] > pivot) {
                // 在pivot的右边一直找，找到一个小于等于pivot的值才退出
                r -= 1;
            }
            if (l >= r) {
                // 如果l>=r成立，说明pivot左右两边的值已经按照
                // 左边全部小于pivot的值，
                // 右边全部大于等于pivot的值
                break;
            }
            //如果条件没有满足，那么就交换左右的值
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //如果交换完后，如果发现arr[l] == pivot的值，右指针前移一步
            if (arr[l] == pivot) { //如果这里移动了l，就没有办法保证最后l和r会集中在中心
                //l++;
                r--;
            }

            if (arr[r] == pivot) {
                //r--;
                l++;
            }
        }

        //如果说l == r，必须让l++，r--，否则会出现栈溢出
        if (l == r) {
            l += 1;
            r -= 1;
        }

        //向左递归

        if (left < r) { //z这里的意思是还没有递归到数组头，
            quickSort(arr, left, r);
        }
        if (right > l) {//这里的意思是还没有递归到数组尾
            quickSort(arr, l, right);
        }
    }
}
