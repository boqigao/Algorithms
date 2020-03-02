package cn.boqi.datastructures.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {
        // 完成将一个中缀表达式转成后缀表达式的代码
        // 说明
        // 1.因为直接对一个字符串进行操作不太方便，因此，先将原字符串转换成中缀list
        // 2.将得到的中缀表达式对应的list = 》后缀表达式对应的list
        String expression = "1+((2+3)*4)-5";
        List<String> infixExpressionList = toInfixExpressionList(expression);
        System.out.println(infixExpressionList);
        List<String> pas = parseSuffixExpressionList(infixExpressionList);
        System.out.println("后缀表达式是"+pas);


        //先定义一个逆波兰表达式
        //(3+4)*5 -6 => 3 4 + 5 * 6 -
        //为了方便，将符号和数字用空格隔开

        String suffixExpression = "3 4 + 5 * 6 -";

        /*
        思路
        1. 先将3 4 + 5 * 6 -放入ArrayList中
        2. 将ArrayList传递给一个方法，配合栈，完成计算
         */
        List<String> rpnList = getListString(suffixExpression);
        System.out.println(rpnList);
        System.out.println(calculate(rpnList));

    }

    //将中缀表达式的list转换成后缀表达式的list
    public static List<String> parseSuffixExpressionList(List<String> ls) {
        //定义两个栈
        Stack<String> s1 = new Stack<String>(); //符号栈
        //因为S2再整个转换过程中，没有pop操作，而且后面我们还需要逆序输出，
        //因此比较麻烦，这里我们不用栈而直接使用List
        List<String> s2 = new ArrayList<String>();

        //遍历ls
        for (String item : ls) {
            if (item.matches(("\\d+"))) {
                s2.add(item);
            }
            else if (item.equals("(")) {
                s1.push(item);
            }
            else if (item.equals(")")) {
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();//!！！！将（小括号弹出s1栈，消除小括号
            } else {
                //当item的优先级小于等于栈顶运算符的优先级，将s1栈顶的运算符弹出并加入到s2中
                //再次转到4.1中与s1中新的栈顶运算符相比较
                //问题：我们缺少一个计算优先级高低的方法
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)){
                    s2.add(s1.pop());
                }
                //还需要将item最后压入栈中
                s1.push(item);
            }
        }
        //将s1中剩余的运算符依次弹出并加入s2
        while (!s1.isEmpty()){
            s2.add((s1.pop()));
        }

        return s2;
    }

    //方法：将中缀表达式转成对应的list
    public static List<String> toInfixExpressionList(String s) {
        //定义一个list，存放中缀表达式对应的内容
        List<String> ls = new ArrayList<>();
        int i = 0; //这是一个指针，用于遍历s
        String str; // 对多位数做拼接工作
        char c;// 每遍历到一个字符，就放入到c中
        do {
            //如果c是一个非数字，就需要加入到ls里面去
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                ls.add("" + c);
                i++;
            } else { //如果是一个数字，那么需要考虑多位数的问题
                str = ""; //先将string清空
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
                    str += c;
                    i++;
                }
                ls.add(str);
            }
        } while (i < s.length());
        return ls;

    }

    //将一个逆波兰表达式，依次数据和运算符放入到ArrayList当中
    public static List<String> getListString(String suffixExpression) {
        //将suffixExpression 分割
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();
        for (String temp : split) {
            list.add(temp);
        }

        return list;
    }

    //完成对逆波兰表达式的运算

    public static int calculate(List<String> ls) {
        //创建给栈，只需要一个栈即可
        Stack<String> stack = new Stack<>();
        //遍历ls
        for (String temp : ls) {
            //这里使用正则表达式来去输出
            if (temp.matches("\\d+")) { //匹配的是多位数
                //入栈
                stack.push(temp);
            } else {
                //pop出两个数，并运算，再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (temp.equals("+")) {
                    res = num1 + num2;
                } else if (temp.equals("-")) {
                    res = num1 - num2;//先弹出来的数字用num2接受的
                } else if (temp.equals("*")) {
                    res = num1 * num2;
                } else if (temp.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符号有问题");
                }
                //把res入栈
                stack.push("" + res);
            }
        }
        //最后留在stack中的就是运算结果
        return Integer.parseInt(stack.pop());
    }
}

//编写一个Operation类， 可以返回一个运算符对应的优先级
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    //写一个方法，返回对应的优先级数字
    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
            default:
                break;
        }
        return result;
    }
}
