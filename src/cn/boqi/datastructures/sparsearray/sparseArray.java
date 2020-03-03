package cn.boqi.datastructures.sparsearray;

/**
 * 稀疏数组保存五子棋
 * @author gaobo
 */
public class sparseArray {
    public static void main(String[] args) {
        //先创建一个原始的二维数组
        //0表示没有棋子，1表示黑子，2表示白子
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][4] = 2;
        System.out.println("原始的二维数组：");
        for(int row[]:chessArr1){
            for(int data:row){
                System.out.print(data +"\t");
            }
            System.out.println();
        }

        //将二维数组转稀疏数组的思想
        //1.先遍历二维数组，得到非0数据的个数
        int sum = 0;
        for(int i = 0; i < 11; i++){
            for (int j = 0; j <11; j ++){
                if(chessArr1[i][j] != 0){
                    sum+=1;
                }
            }
        }

        //2. 创建对应的稀疏数组
        int[][] sparseArray = new int[sum+1][3];

        //3. 给稀疏数组加数据
        sparseArray[0][0] = 11;
        sparseArray[0][1] = 11;
        sparseArray[0][2] = sum;

        //4. 遍历二维数组，将非0的值存放到稀疏数组中。
        int count = 0;//count用于记录第几个非零数据
        for(int i = 0; i < 11; i++){
            for (int j = 0; j <11; j ++){
                if(chessArr1[i][j] != 0){
                    count++;
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = chessArr1[i][j];
                }
            }
        }

        //输出稀疏数组
        System.out.println("------------------");
        System.out.println("得到的稀疏数组为：");

        for (int i = 0; i<sparseArray.length; i++){
            System.out.print(sparseArray[i][0]+"\t"+sparseArray[i][1] +"\t" + sparseArray[i][2]);
            System.out.println();
        }

        //将稀疏数组恢复成为原始的二维数组
        /*
        1.	先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组，比如上面的chessArr2 = int[11][11].
        2.	在读取稀疏数组后几行的数据，并赋值给原始的二维数组。
        */

        int rowNum = sparseArray[0][0];
        int columnNum = sparseArray[0][1];

        int[][] chessArray2 = new int[rowNum][columnNum];
        for (int i= 1; i<sparseArray.length; i++){
            chessArray2[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }

        //print回复过的二维数组
        System.out.println("#-----------------------------------#");
        System.out.println("恢复出来的二维数组是：");
        for(int row[]:chessArray2){
            for(int data:row){
                System.out.print(data +"\t");
            }
            System.out.println();
        }

    }
}
