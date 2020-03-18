package cn.boqi.datastructures.tree;

/**
 * 堆排序
 *
 * @author BoqiGao
 */
public class HeapSort {
    public static void main(String[] args) {
        //要求将数组进行升序排序
        int[] arr = {4, 6, 8, 5, 9};
    }

    //编写一个堆排序的方法
    public static void heapSort(int[] arr) {

        int temp = 0;
        //完成我们的代码
        //将无序序列构成一个堆，根据升序降序需求选择大顶对或者小顶堆
        //i从大到小的原因是，从下到上,从右到左调整，下方的i比较大
        //相当于每次只调整一层
        for (int i = arr.length / 2 - 1; i > 0; i--) {
            adjustHeap(arr, i, arr.length);
        }

        //将堆顶元素与末尾元素交换，将最大元素沉到数组末端
        for (int j = arr.length - 1; j > 0; j--) {
            temp = arr[j];//交换
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, j);
        }
    }

    /**
     * 完成将以i对应的非叶子节点的树调整成为大顶堆
     * 举例 将{4,6,8,5,9}， i=1，=>调整成为得到{4,9,8,5,6}
     * 如果我们再次调用adjustHeap 传入i=0，那么我们会得到
     * {4,9,8,5,6}->{9,6,8,5,4}
     *
     * @param arr    待调整的数组
     * @param i      表示非叶子节点在数组中的索引
     * @param length 对多少个元素继续调整，length是在逐渐的减少
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i]; //先取出当前元素的值，保存在临时变量
        //开始调整
        //说明
        //1. k = i*2+1, k是i节点的左子节点
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {//这里非常关键，说明只调整了一层
            if (arr[k] < arr[k + 1]) {//说明左子节点的值小于右子节点的值
                k++; //k指向右子节点
            }
            if (arr[k] > temp) {//如果子节点大于父节点
                //进行交换处理
                arr[i] = arr[k]; //把较大的值赋值给当前节点

                i = k; //！！！！让i指向k，继续循环比较
            } else {
                break;//!为什么敢于break,因为是从右到左，从下到上调整的
                // ，下方的一定已经调整过了
            }
        }
        //当for循环结束后，我们已经将以i为父节点的树的最大值放在了最顶上
        //意思就是说，以i（e.g。 1）为父节点的一部分的子树，
        // 能确保arr【1】现在是他所拥有子树的最大值
        arr[i] = temp;

    }
}
