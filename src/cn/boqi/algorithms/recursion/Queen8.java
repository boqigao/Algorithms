package cn.boqi.algorithms.recursion;

import javax.swing.*;

/**
 * 使用递归解决八皇后问题
 *
 * @author Boqi Gao
 */
public class Queen8 {

    //我们先定义一个max表示共有多少个皇后
    int max = 8;
    //定义数组array，保存皇后放置位置的结果比如
    //arr = {0, 4, 7, 5, 2, 6, 1, 3}
    int[] array = new int[max];

    public static void main(String[] args) {
        Queen8 q = new Queen8();
        q.check(0);

    }

    //查看当我们放置第n个皇后时候，就去检测该皇后是否和前面已经摆放的皇后冲突

    /**
     * @param n 表示放置第n个皇后
     * @return 是否冲突
     */
    private boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            if (array[i] == array[n] ||
                    //表示第n个皇后是否和前面的n-1个皇后在同一列
                    Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                //其实很好理解，行的差值等于列的差值，不就是在同一条斜线上吗
                return false;
            }
        }
        return true;
    }

    //编写一个方法，放置第n个皇后
    //特别注意：check是每一次递归时候，进入check都有for循环，因此会有回溯
    private void check(int n) {
        if (n == max) { //n=8, 就是8个皇后都已经放好了
            print();
            return;
        }
        //依次放入皇后，并判断是否冲突
        for (int i = 0; i < max; i++) { //第n个皇后就放在第n行，i是指的是第i列
            //把当前这个皇后n，放到n行的第i列
            array[n] = i;
            //判断当放置第n个皇后到i列时候，是否冲突。
            if (judge(n)) {//不冲突
                //接着放n+1个皇后，即开始递归
                check(n + 1);
            }
            //如果冲突，就继续执行array【n】=i++去了；即将第n个皇后放在第n行的后移的一个位置
        }
    }


    //写一个方法，可以将皇后摆放的位置输出
    private void print() {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
