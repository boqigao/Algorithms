package cn.boqi.datastructures.stack;

/**
 * 用数组实现栈
 * @author gaobo
 */
public class ArrayStackDemo {
    public static void main(String[] args) {

    }
}

//定义一个ArrayStack表示栈结构
class ArrayStack{
    private int maxSize; //栈的大小
    private int[] stack; //用数组模拟栈，数据就放在这个数组中
    private int top = -1; //top表示栈顶，初始化为-1

    //Constructor
    public ArrayStack(int maxSize){
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //栈满了
    public boolean isFull(){
        return top == maxSize - 1;
    }

    public boolean isEmpty(){
        return top == -1;
    }

    //入栈的操作
    public void push(int value){
        //先判断栈是否是满了
        if(isFull()){
            System.out.println("栈满了！无法放东西！");
            return;
        }

        top++;
        stack[top] = value;
    }

    //出栈-pop，将栈顶的数据返回
    public int pop(){
        //先判断是否空了
        if(isEmpty()){
            //需要有返回值，所以我们选择抛出异常
            throw new RuntimeException("栈空！没有数据！");
        }else {
            return stack[top];
        }



    }
}