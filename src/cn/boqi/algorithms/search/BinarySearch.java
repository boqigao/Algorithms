package cn.boqi.algorithms.search;

/**
 * 二分查找
 *
 * @author Boqi Gao
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(binarySearch(arr, 0, arr.length - 1, 444));
    }


    /**
     * @param right   右边的索引
     * @param left    左边的索引
     * @param arr     原来的数组
     * @param findVal 要查找的值
     * @return
     */
    public static int binarySearch(int[] arr, int left, int right, int findVal) {

        //当left > right时候，说明递归整个数组，但是没有找到
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        if (findVal > midVal) {//向右递归
            return binarySearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
            //向左递归
            return binarySearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }

    }
}
