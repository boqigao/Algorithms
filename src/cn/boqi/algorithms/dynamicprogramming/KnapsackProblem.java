package cn.boqi.algorithms.dynamicprogramming;

public class KnapsackProblem {
    public static void main(String[] args) {
        int[] w = {1, 4, 3}; //物品的重量
        int[] val = {1500, 3000, 2000}; //物品的价值
        int m = 4; //背包的容量
        int n = val.length; //物品的个数

        //创建一个二维数组，表示
        //在前i个物体中，能够装入容量为j的背包中的最大价值
        int[][] v = new int[n + 1][m + 1];
        //为了记录物品放入的情况，我们定义一个二维数组
        int[][] path = new int[n + 1][m + 1];

        //初始化第一行和第一列，这里在本程序中，可以不去处理，因为默认就是0
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;//将第一列设置为0
        }

        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0;//将第一行设置为0
        }

        //根据前面得到的公式来进行动态规划处理
        for (int i = 1; i < v.length; i++) {
            for (int j = 1; j < v[0].length; j++) {
                //公式
                if (w[i - 1] > j) { // 因为我们的程序是从1开始的，因此原来公式中的w[i]修改成w[i-1]
                    v[i][j] = v[i - 1][j];
                } else {
                    //因为，我们的i是从1开始的，因此公式要调整成下面的情况
                    //v[i][j] = Math.max(v[i-1][j], val[i-1]+v[i-1][j-w[i-1]]);
                    //为了记录商品存放到背包的情况，我们直接简单的使用公式
                    //需要使用if，else来处理
                    if (v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]) {
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
                        //把当前的情况记录到path
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }
    }
}
