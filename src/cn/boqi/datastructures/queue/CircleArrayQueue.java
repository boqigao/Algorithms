package cn.boqi.datastructures.queue;

import java.util.Scanner;

/**
 * 用数组形成队列的环形复用
 */
public class CircleArrayQueue {
    public static void main(String[] args) {
        CircleArray q = new CircleArray(4); //说明设置4，其最大有效数据是3
        char key = ' ';
        Scanner s = new Scanner(System.in);

        boolean loop = true;
        while (loop) {
            System.out.println("s(show):显示队列");
            System.out.println("e(exit)：退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从数据取出数据");
            System.out.println("h(head):查看队列头的数据");
            key = s.next().charAt(0);

            switch (key) {
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
                        System.out.println("取出的数据是：" + result);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = q.headQueue();
                        System.out.println("队列头的数据是：" + res);

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    break;
            }
        }


    }
}

class CircleArray {
    private int maxSize; //表示数组最大容量
    private int front; //指向队列头的前一个位置
    private int rear;  //指向队列尾部的指针
    private int[] arr; //该数组用于存放数据，模拟队列

    public CircleArray(int arrMaxSize) {
        this.maxSize = arrMaxSize;
        this.arr = new int[maxSize];
        front = 0;
        rear = 0;
    }

    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    public boolean isEmpty() {
        return this.rear == this.front;
    }

    /**
     * 添加数据只要考虑队尾指针
     *
     * @param n 添加的数据
     */
    public void addQueue(int n) {
        //判断队列是否满了
        if (isFull()) {
            System.out.println("队列满了，不能加入数据！");
            return;
        }

        //直接将数据加入
        arr[rear] = n;
        //将rear后移，这里必须考虑取模
        //其实就是相当于讨论是否满了
        rear = (rear + 1) % maxSize;
    }

    public int getQueue() {
        //判断队列是否是空的
        if (isEmpty()) {
            throw new RuntimeException("队列空的，无法取数据！");
        }

        // 这里需要分析出front是指向队列的第一个元素
        // 1.先把front对应的值保存到一个临时变量
        // 2.将front后移
        int temp = arr[front];

        // 后移的时候也应该考虑取模
        front = (front + 1) % maxSize;
        return temp;
    }

    // 显示队列的所有数据
    // 要从front开始遍历，遍历多少个元素
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列空的，没有数据！");
            return;
        }

        for (int i = front; i < front + size(); i++) {//其实主要理解了他是个环形的就可以了，后面绕一圈再取模就不费劲
            System.out.println("arr[" + i % maxSize + "]:" + arr[i % maxSize]);
        }
    }

    //求出当前队列有效数据的个数，不然无法遍历
    public int size() {
        return (rear + maxSize - front) % maxSize;
    }

    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空的！没有数据！");
        }
        return arr[front];
    }
}