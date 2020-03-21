package cn.boqi.algorithms.search;

public class BinarySearchNoRecursion {
    public static void main(String[] args) {
        int[] arr = {1, 4, 78, 5413, 74314, 73652};
        System.out.print(BinarySearchNoRecur(arr, 781));
    }

    //二分查找的非递归实现

    /**
     * @param arr    待查找的数组
     * @param target 想要查找的数字
     * @return 返回想要查找数字的下标
     */
    public static int BinarySearchNoRecur(int[] arr, int target) {

        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {//说明继续查找
            int mid = (left + right) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return -1;
    }
}
