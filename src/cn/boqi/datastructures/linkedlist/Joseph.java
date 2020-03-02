package cn.boqi.datastructures.linkedlist;

/**
 * 使用环形列表解决joseph问题
 * @author Gao Boqi
 */
public class Joseph {
    public static void main(String[] args) {
        CircleSingleLinkedList l = new CircleSingleLinkedList();
        l.addBoy(25);
        l.showBoy();
        l.countBoy(1,2,25);
    }
}

//创建一个环形的单向链表
class CircleSingleLinkedList {
    //创建一个first节点，当前没有编号
    //这个first很关键，就有点类似于别的链表的head，因为你没有这个first或者head你是无法调用这个链表的
    private Boy first = null;

    //添加小孩节点，构建成一个环形链表
    public void addBoy(int num) { //表示要添加多少个小孩
        //对numbers做一个简单地数据校验
        if (num < 1) {
            System.out.println("小孩儿数量不能小于一！");
            return;
        }
        Boy curBoy = new Boy(); //辅助指针，帮助我们构建环形链表
        //使用for循环创建环形链表
        for (int i = 1; i <= num; i++) {
            //根据编号创建小孩节点
            Boy boy = new Boy(i);
            //如果是第一个小孩
            if (i == 1) {
                first = boy;
                first.setNext(first);
                curBoy = first;//让currentboy指向第一个小孩，因为我们first不能动，有点类似于单双链表的head
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    //根据用户的输入，计算出小孩出圈的顺序

    /**
     * @param startNo  表示从第几个小孩开始数数
     * @param countNum 表示每个小孩数几下
     * @param nums     表示最开始有多少小孩在圈中
     */
    public void countBoy(int startNo, int countNum, int nums) {
        //先对数据进行校验
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("您的参数输入有误，请重新输入");
            return;
        }

        //先创建辅助指针，帮助完成小孩出圈的过程
        Boy helper = first;
        while (true) {
            if (helper.getNext() == first) {//说明helper指向了最后一个小孩节点
                break;
            }
            helper = helper.getNext();
        }

        for (int i = 0; i < startNo-1; i++){//让指针移动到初始小孩的位置
            first = first.getNext();
            helper = helper.getNext();
        }

        //当小孩报数时候，让first和helper指针同时移动m-1次，然后除权
        //这是一个循环操作，直到权重只有一个节点
        while (true){
            if(helper == first){
                System.out.println("圈中只剩下最后一个人了！他是第"+helper.getNo()+"号小朋友");
                break;
            }
            //让first 和helper柱子很同时移动countNum-1次，
            for (int i = 0; i< countNum-1; i ++){
                first = first.getNext();
                helper = helper.getNext();
            }

            System.out.println("第"+first.getNo()+"号小朋友出圈");
            first = first.getNext();
            helper.setNext(first);
        }
    }

    //遍历当前这个环形链表
    public void showBoy() {
        //判断链表是否为空
        if (first == null) {
            System.out.println("空链表！没必要打印了！");
        }
        Boy curBoy = first;
        while (true) {
            System.out.println("小孩的编号：" + curBoy.getNo());
            if (curBoy.getNext() != first) {
                curBoy = curBoy.getNext();
            } else {
                break;
            }
        }
    }

}

//创建一个boy类，表示一个节点
class Boy {
    private int no;//编号
    private Boy next; //下一个节点

    public Boy(int no) {
        this.no = no;
    }

    public Boy() {
        this.no = 0;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
