package cn.boqi.algorithms.recursion;

public class recursion {
    public static void main(String[] args) {
        System.out.println(factorial(5));
    }

    //打印问题
    public static void test(int n){
        if(n>2){
            test(n-1);
        } else {
            System.out.println("n="+n);
        }
    }

    //阶乘问题
    public static int factorial(int n){
        if (n == 1){
            return 1;
        }else {
            return factorial(n-1)*n;
        }
    }
}
