package cn.boqi.algorithms.devideandconquer;

public class HanoiTower {
    public static void main(String[] args) {
        hanoiTower(3, 'A', 'B', 'C');
    }

    //汉诺塔的移动方案
    //使用分值算法解决

    /**
     * @param num 盘子的数量
     * @param a   a柱子
     * @param b   b柱子
     * @param c   c柱子
     */
    public static void hanoiTower(int num, char a, char b, char c) {
        if (num == 1) {
            System.out.println("第1个盘子从 " + a + "->" + c);
        } else {
            //如果我们有n 》=2的情况，我们做是可以看做两个盘子
            //1.最下面的一个盘子，
            //2.上面的所有盘子
            //1.先把上面的所有盘子a->b
            hanoiTower(num - 1, a, c, b);
            //2.再把最下面的一个盘子移动到c
            System.out.println("第" + num + "个盘子从 " + a + "->" + c);
            //3.再把b塔的所有盘子从b-c，移动过程中用到a
            hanoiTower(num - 1, b, a, c);
        }

    }
}
