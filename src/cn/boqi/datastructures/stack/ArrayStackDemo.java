package cn.boqi.datastructures.stack;

import java.util.Scanner;

/**
 * 用数组实现栈
 *
 * @author gaobo
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        //测试一下ArrayStack 是否正确
        //先创建一个ArrayStack的一个对象-》表示栈
        ArrayStack stack = new ArrayStack(4);
        String key = "";
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);

        while (loop) {
            System.out.println("Show: 表示显示栈");
            System.out.println("Exit: 表示退出程序");
            System.out.println("Push: 添加数据到栈");
            System.out.println("Pop: 表示出栈");
            System.out.println("请输入你的选择: ");
            key = scanner.next();
            switch (key) {
                case "s":
                    stack.list();
                    break;
                case "push":
                    System.out.println("请输入一个数字:");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int res = stack.pop();
                        System.out.println("出栈的数据是：" + res);
                    } catch (Exception e) {
                        e.getMessage();
                    }
                    break;
                case "e":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出了！");
    }
}

//定义一个ArrayStack表示栈结构
class ArrayStack {
    private int maxSize; //栈的大小
    private int[] stack; //用数组模拟栈，数据就放在这个数组中
    private int top = -1; //top表示栈顶，初始化为-1

    //Constructor
    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //栈满了
    public boolean isFull() {
        return top == maxSize - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    //入栈的操作
    public void push(int value) {
        //先判断栈是否是满了
        if (isFull()) {
            System.out.println("栈满了！无法放东西！");
            return;
        }

        top++;
        stack[top] = value;
    }

    //出栈-pop，将栈顶的数据返回
    public int pop() {
        //先判断是否空了
        if (isEmpty()) {
            //需要有返回值，所以我们选择抛出异常
            throw new RuntimeException("栈空！没有数据！");
        }
        int value = stack[top];
        top--;
        return value;
    }

    public void list() {
        if (isEmpty()) {
            System.out.println("栈空，没有数据！");
        }
        for (int i = top; i >= 0; i--) {
            System.out.println("stack[" + stack[i] + "]\n");
        }

    }
}