package cn.boqi.datastructures.stack;

/**
 * 使用双栈做加减乘除表达式的运算
 * @author Gaoboqi
 */
public class CalculatorStack {
    public static void main(String[] args) {
        //根据前面老师的思路完成
        String exression = "11+12";//如何处理多位数的问题？？

        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);

        // 定义需要的相关变量, 用于扫描
        int index = 0;
        // 定义弹出数字的num1和num2
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' '; //将每次扫描得到的char保存到ch
        String keepNum = ""; //用于拼接多位数

        //开始while循环扫描expression

        while (true){
            //依次得到expression 的每一个字符
            //从index开始取，用substring 方法取一个字符，然后用charAt（0）拿出
            //这个字符
            ch = exression.substring(index, index+1).charAt(0);
            //判断ch是什么，然后做相应的处理
            if(operStack.isOper(ch)){//如果是运算符
                //判断当前的符号栈是否为空
                if(!operStack.isEmpty()){//如果不为空
                    //处理
                    if(operStack.priority(ch) <= operStack.priority(operStack.peek())){
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        //把运算的结果入数栈
                        numStack.push(res);
                        operStack.push(ch);
                    }
                    else {
                        operStack.push(ch);
                    }
                }else {
                    //如果为空，则直接入栈
                    operStack.push(ch);
                }
            } else { // 如果是数字，则直接入数字栈
                // char值和十进制的值差了48
                // 1.当处理多位数时候，不能发现一个数就立即入栈，因为他可能是多位数
                // 2.在处理数字时候，需要向expression的表达式的index后再看一位
                //   如果是数字，就继续扫描，如果是符号，才入栈
                // 3.因此我们需要定义一个字符串变量，用于拼接

                // 处理多位数
                keepNum += ch;

                // 如果ch已经是expression的最后一位，就直接入栈
                if (index == exression.length()-1){
                    numStack.push(Integer.parseInt(keepNum));
                }else {
                    // 判断下一个字符是不是数字
                    // 如果是数字，就继续扫描
                    // 如果是运算符，则入数栈
                    if(operStack.isOper(exression.substring(index+1, index+2).charAt(0))){
                        //如果后一位是运算符，则入栈 keepNum = "1" 或者 "123";
                        //parse的意思就是解析器，把string解析成int
                        numStack.push(Integer.parseInt(keepNum));
                        //重要！！！清空keepNum
                        keepNum = "";
                    }
                }
            }
            //让index+1判断是否扫描到expression的最后
            index ++ ;
            if (index >= exression.length()){
                break;
            }
        }

        //当表达式扫描完毕，就顺序从数栈和符号栈中取出相应的数字和符号并运行
        while (true){
            //如果符号栈为空，则计算到最后的结果，数栈中只有一个数字【结果】
            if (operStack.isEmpty()){
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            //把运算的结果入数栈
            numStack.push(res);
        }
        System.out.println("表达式的结果是： "+numStack.pop());
    }
}

//定义一个ArrayStack表示栈结构
class ArrayStack2{
    private int maxSize; //栈的大小
    private int[] stack; //用数组模拟栈，数据就放在这个数组中
    private int top = -1; //top表示栈顶，初始化为-1

    //Constructor
    public ArrayStack2(int maxSize){
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
        }
        int value = stack[top];
        top--;
        return value;
    }

    public void list(){
        if (isEmpty()){
            System.out.println("栈空，没有数据！");
        }
        for (int i = top; i>=0; i--){
            System.out.println("stack["+stack[i]+"]\n");
        }
    }

    //增加一个方法，可以返回当前栈顶的值，但是不是真正的pop
    public int peek(){
        return stack[top];
    }

    //返回运算符的优先级， 优先级是程序员来确定， 优先级用数字表示
    //数字越大，则优先级越高
    public int priority(int oper){
        if(oper == '*' || oper =='/'){
            return 1;
        } else if (oper=='+'||oper=='-'){
            return 0;
        } else {
            return -1; //假设目前的表达式里只有+, -, *, /
        }
    }

    //判断是不是一个运算符
    public boolean isOper(char val){
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //计算方法
    public  int cal(int num1, int num2, int oper){
        int res = 0; //用于存放计算的结果
        switch (oper){
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;  // 比如算式6-3, 入栈的时候先入6，再入3
                                    // 出栈的时候先出3，再出6
                                    // 因此6是后出的，为num2， 3先出，为num1
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1; // 同减法
                break;

            default:
                break;
        }
        return res;
    }
}