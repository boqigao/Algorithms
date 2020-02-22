package cn.boqi.datastructures.linkedlist;

import java.util.Stack;

/**
 * 创建单链表来存储水浒英雄人物
 *
 * @author gaobo
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义","玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "公孙胜","入云龙");

        SingleLinkedList sll = new SingleLinkedList();
        sll.addNode(hero1);
        sll.addNode(hero3);
        sll.addNode(hero2);
        sll.addNode(hero4);

        sll.showList();
        sll.reverseShow();
    }
}

//定义一个SingleLinkedList 来管理我们的英雄人物
class SingleLinkedList {
    //先初始化一个头结点，头结点不要动，不存放具体的数据
    private HeroNode head = new HeroNode(0, "", "");

    //添加节点到单向链表
    //如果想实现有序添加，那必须判定后一个节点大于这个新加的node
    public void addNode(HeroNode heroNode){
        //因为头节点不能动，所以我们需要一个辅助变量temp
        HeroNode temp = head;
        boolean flag = false;
        // 遍历节点，找到最后
        while (true){
            if(temp.next == null){
                break;
            }
            if (temp.next.no > heroNode.no){ //要直接让temp后面的一个节点和要添加的node比
                //位置找到，就在temp的后面插入
                break;
            }
            temp = temp.next;
        }

        heroNode.next = temp.next;
        temp.next = heroNode;
    }

    /**
     * 方法1.现将单链表进行反转操作，然后在遍历，但是这个方式不建议，因为会影响到原本的单链表
     * 方法2.运用栈这种数据结构，将各个节点压入到栈中，然后利用栈的先进后出的特点，就实现了逆序打印的效果。
     * 建议使用方法2
     */
    public void reverseShow(){
        if (this.head == null){
            System.out.println("空链表！无法打印！");
        }

        Stack<HeroNode> s = new Stack<>();

        HeroNode cur = head.next;
        //将链表的所有节点压入栈中
        while (cur!=null){
            s.push(cur);
            cur = cur.next; //让current 后移，这样就可以压入下一个节点。注意！！cur完全不会影响原链表
        }

        //将栈中的节点进行打印
        //用pop方法
        while (s.size()>0){
            System.out.println(s.pop().toString());
        }

    }

    //删除节点，也必须要找到删除的个节点的前一个节点/
    //因为这是单向链表，你真的找到了要删除的节点反而没办法删除了
    public void delete(HeroNode delNode){
        if(head.next == null){
            System.out.println("链表为空！");
        }

        HeroNode temp = head;
        while (true){
            if (temp.next.no == delNode.no){
                break;
            }
            temp = temp.next;
        }
        temp.next = temp.next.next;
    }

    //更新信息
    public void update(HeroNode newHeroNode){
        //判断是否为空
        if(head.next == null){
            System.out.println("链表为空！");
        }
        //找到需要修改的节点，根据no编号修改
        //先定义一个辅助变量
        HeroNode temp = head.next;
        boolean flag = false;

        while (true){
            if(temp == null){
                break;//说明到达了链表的最后，已经遍历完链表
            }
            if(temp.no == newHeroNode.no){
                System.out.println("找到了！");
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag判断是否找到要修改的节点
        if(flag){
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        }else {
            System.out.println("没有找到编号为" + newHeroNode.no +"的节点！");
        }

    }

    /**
     * 查找出单链表中倒数第k个节点
     * 先遍历一遍，然后再找到倒数第k个就可以
     * @param k 倒数第k个节点
     * @return 倒数的第k个节点
     */
    public HeroNode showReverseKNode(int k){
        int nodeNum = this.getNodeNum(); // 先求出单链表中有多少节点
        int i = nodeNum - k + 1; // i就是相对于这个单链表正数第几个节点

        HeroNode temp = head;

        for (int j = 0; j < i; j++){
            temp = temp.next;
        }
        return temp;
    }

    /**
     * 获得一个反转的单链表: 要求是必须是地址空间里面的同一个node，而不能新建node（包括头结点）
     * 1.先定义一个新的头结点 reverseHead.next
     * 2.从头到尾遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表的最前端
     * 3.原来的链表head.next = reverseHead.next
     * @param sll 输入的单链表
     * @return sll 反转后的单链表
     */
    public SingleLinkedList reverseSingleLinkedList (SingleLinkedList sll){

        int length = sll.getNodeNum();
        HeroNode reverseHead = new HeroNode();

        HeroNode temp = sll.head.next;
        sll.head.next = sll.head.next.next;

        temp.next = null;//因为把宋江拿出来放到最后了，宋江的屁股一定要擦干净！！= null！
        reverseHead.next = temp;

        while (sll.head.next !=null){
            temp = sll.head.next;
            sll.head.next = sll.head.next.next;

            temp.next = reverseHead.next;
            reverseHead.next = temp;
        }
        sll.head.next = reverseHead.next;
        return sll;
    }



    //获取单链表中有效节点的个数
    public int getNodeNum(){
        HeroNode temp = head;
        int j = 0;
        while (temp.next!=null){
            temp = temp.next;
            j += 1;
        }

        return j;
    }

    //显示链表
    public void showList(){
        //判断链表是否为空
        if(head.next==null){
            return;
        }

        //如果不为空，因为头结点不能动，因此我们需要一个辅助变量来遍历
        HeroNode temp = head.next;

        while (true){
            if (temp == null){
                break;
            }
            //输出这个节点的信息
            System.out.println(temp.toString());
            temp = temp.next;
        }
    }
}


//定义一个HeroNode，每个HeroNode对象就是一个节点
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    public HeroNode(){
        this.no = 0;
        this.name = "";
        this.nickname = "";
    }
    @Override
    public String toString() {
        return "HeroNode [no=" + no + ", name=" + name + ", nickname = " + nickname + "]";
    }
}