package cn.boqi.algorithms.search;

/**
 * 差值查找
 *
 * @author Boqi Gao
 */
public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }
        System.out.println(insertValueSearch(arr, 0, arr.length - 1, 3));

    }

    /**
     * @param arr     原数组
     * @param left    左指针
     * @param right   右指针
     * @param findVal 查找值
     * @return 如果找到，就返回 对应的下标，如果没有找到，就返回-1
     */
    public static int insertValueSearch(int[] arr, int left, int right, int findVal) {

        //注意，后面两个条件必须需要，不然会数组越界
        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {
            return -1;
        }

        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];

        if (findVal > midVal) {//向右边查找
            insertValueSearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
            insertValueSearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
        return -1;
    }


}
