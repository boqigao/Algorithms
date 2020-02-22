package cn.boqi.datastructures.queue;

import java.util.Scanner;

/**
 * 实现队列：先进先出
 * 应用场景：各种地方的排队取号系统
 * 队列是一个有序表，可以用数组或者链表来实现。
 * @author gaobo
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue q = new ArrayQueue(4);
        char key = ' ';
        Scanner s = new Scanner(System.in);

        boolean loop = true;
        while (loop){
            System.out.println("s(show):显示队列");
            System.out.println("e(exit)：退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从数据取出数据");
            System.out.println("h(head):查看队列头的数据");
            key = s.next().charAt(0);

            switch (key){
                case 's':
                    q.showQueue();
                    break;
                case 'e':
                    loop = false;
                    System.out.println("程序退出");
                    break;
                case 'a':
                    System.out.println("输入一个数：");
                    int value = s.nextInt();
                    q.addQueue(value);
                    break;
                case 'g':
                    try {
                        int result = q.getQueue();
                        System.out.println("取出的数据是："+result);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try{
                        int res = q.headQueue();
                        System.out.println("队列头的数据是："+res);

                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    break;
            }
        }



    }
}

//使用数组模拟队列，编写一个ArrayQueue的类
class ArrayQueue{
    private int maxSize; //表示数组最大容量
    private int front; //指向队列头的前一个位置
    private int rear;  //指向队列尾部的指针
    private int[] arr; //该数组用于存放数据，模拟队列

    //创建队列的构造器
    public ArrayQueue(int arrMaxSize){
        this.maxSize = arrMaxSize;
        this.arr = new int[maxSize];
        this.front = -1; //指向队列头的前一个位置
        this.rear = -1;  //指向队列尾部【就是队列的最后一个数据】
    }

    public boolean isFull(){
        return this.rear == this.maxSize - 1;
    }

    public boolean isEmpty(){
        return this.rear == this.front;
    }

    /**
     * 添加数据只要考虑队尾指针
     * @param n 添加的数据
     */
    public void addQueue(int n){
        //判断队列是否满了
        if(isFull()){
            System.out.println("队列满了，不能加入数据！");
            return;
        }

        rear++;//让尾部指针后移
        arr[rear] = n;
    }

    public int getQueue(){
        //判断队列是否是空的
        if(isEmpty()){
            throw new RuntimeException("队列空的，无法取数据！");
        }

        front++;//front后移一下
        arr[front] = 0;
        return arr[front];

    }

    //显示队列的所有数据
    public void showQueue(){
        if (isEmpty()){
            System.out.println("队列空的，没有数据！");
            return;
        }

        for(int i = 0; i<arr.length; i++){
            System.out.println("arr["+i+"]:" +arr[i] );
        }
    }

    //显示队列的头部，注意！不是取出数据，仅仅是显示头部数据
    public int headQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列空的！没有数据！");
        }
        return arr[front+1];
    }

}