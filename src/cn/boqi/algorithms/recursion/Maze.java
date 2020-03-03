package cn.boqi.algorithms.recursion;

import java.awt.image.ImageProducer;

public class Maze {
    public static void main(String[] args) {
        //先创建一个二维数组，模拟迷宫
        //迷宫
        int[][] maze = new int[8][7];
        //约定：使用1表示墙
        //先把上下全部置为1
        for (int i = 0; i < 7; i++) {
            maze[0][i] = 1;
            maze[7][i] = 1;
        }
        //左右墙壁全部作为1
        for (int i = 1; i < 8; i++) {
            maze[i][0] = 1;
            maze[i][6] = 1;
        }

        //设置挡板
        maze[3][1]=1;
        maze[3][2]=1;
        maze[3][4]=1;
        maze[4][4]=1;
        maze[5][4]=1;
        maze[6][4]=1;
        maze[1][4]=1;
        maze[2][2]=1;
        maze[5][2]=1;


        //输出地图看一下
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }

        //使用递归回溯给小球找路
        //引用类型的话，不管产生多少个栈，都是修改同一张地图！！
        setWay(maze, 1,1);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }

    }

    //使用递归来给小球找路
    //说明
    //1. map表示地图
    //2. ij表示小球初始地点（1,1）
    //3. 如果小球能到map[6][5]为止，则说明通路找到
    //4. 约定：当map[i][j]为0则表示该点没有走过，当为1表示墙，当为2表示通路，可以走
    //   3表示该点走过了，但是走不通
    //5. 我们必须在走迷宫之前确定一个策略（方法）,下->右->上->左,如果改点走不通，再回溯

    /**
     *
     * @param map 表示地图
     * @param i 表示初始地点的横坐标
     * @param j 表示初始地点的纵坐标
     * @return 如果找到通路，就返回true，否则返回false
     */
    public static boolean setWay(int[][] map, int i, int j){
        if(map[6][5]==2){ //说明通路已经找到了
            System.out.println("通路已经找到惹！！");
            return true;
        } else {
            if (map[i][j] == 0){//如果当前这个点等于0
                //按照策略玩一把！下->右->上->左
                map[i][j] = 2; //假定该点是可以走通的
                if(setWay(map, i + 1, j)){ //向下走能走通
                    System.out.println("迷宫的：i= "+i+", j= "+j+" 这个点向下能走通！");
                    return true;
                }else if(setWay(map, i, j+1)){
                    System.out.println("迷宫的：i= "+i+", j= "+j+" 这个点向右能走通！");
                    return true;
                }else if(setWay(map, i-1, j)){
                    System.out.println("迷宫的：i= "+i+", j= "+j+" 这个点向上能走通！");
                    return true;
                }else if(setWay(map, i, j-1)){
                    System.out.println("迷宫的：i= "+i+", j= "+j+" 这个点向左能走通！");
                    return true;
                }else {
                    /*
                    上下左右都走不通的时候，说明这个点就走不通
                    然后回溯回到上面一个栈，将其置为false，然后上面一个栈如果所有的走走完了
                    那再回到上上一个栈也只为false，因此结果会倒着从最后走不通的路开始
                    一路else的false回溯上去。
                     */
                    System.out.println("迷宫的：i= "+i+", j= "+j+" 这个点根本走不通！");
                    map[i][j] = 3;
                    return false;
                }
            }else { //如果当前探测的这个点不等于0，可能是1：墙,2：走过了,
                    //3. 已经走过了，但是不能走
                return false;
            }
        }
    }

}
