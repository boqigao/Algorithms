package cn.boqi.datastructures.linkedlist;

/**
 使用双向链表
 @author Gao Boqi
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {

    }
}

class DoubleLinkedList {
    //先初始化一个头结点，头结点不要动，不存放具体的数据
    private DoubleHeroNode head = new DoubleHeroNode(0, "", "");

    public DoubleHeroNode getHead() {
        return head;
    }

    //遍历双向列表的方法
    //显示链表
    public void showList() {
        //判断链表是否为空
        if (head.next == null) {
            return;
        }

        //如果不为空，因为头结点不能动，因此我们需要一个辅助变量来遍历
        DoubleHeroNode temp = head.next;

        while (true) {
            if (temp == null) {
                break;
            }
            //输出这个节点的信息
            System.out.println(temp.toString());
            temp = temp.next;
        }
    }

    public void addNode(DoubleHeroNode heroNode) {
        //因为头节点不能动，所以我们需要一个辅助变量temp
        DoubleHeroNode temp = head;
        boolean flag = false;
        // 遍历节点，找到最后
        while (true) {
            if (temp.next == null) {
                temp.next = heroNode;
                heroNode.prev = temp;
                break;
            }
            if (temp.next.no > heroNode.no) { //要直接让temp后面的一个节点和要添加的node比
                //位置找到，就在temp的后面插入
                heroNode.next = temp.next.next;
                temp.next.prev = heroNode.next;
                temp.next = heroNode;
                heroNode.prev = temp.next;
                break;
            }
            temp = temp.next;
        }
    }


    //和单向列表基本一样
    public void update(DoubleHeroNode newHeroNode){
        //判断是否为空
        if(head.next == null){
            System.out.println("链表为空！");
        }
        //找到需要修改的节点，根据no编号修改
        //先定义一个辅助变量
        DoubleHeroNode temp = head.next;
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
     * 在双向链表中，可以直接找到想要删除的那个节点
     * @param delNode: 要删除的节点
     */
    public void delete(DoubleHeroNode delNode){
        if(head.next == null){
            System.out.println("链表为空！");
        }

        DoubleHeroNode temp = head;
        while (true){
            if (temp.no == delNode.no){
                break;
            }
            temp = temp.next;
        }
        temp.prev.next = temp.next;
        //这里我们的代码有问题，如果删除的是最后一个节点，这句话不对
        if (temp.next != null){
            temp.next.prev = temp.prev;
        }
    }
}

class DoubleHeroNode {
    public int no;
    public String name;
    public String nickname;
    public DoubleHeroNode next;
    public DoubleHeroNode prev;

    public DoubleHeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    public DoubleHeroNode() {
        this.no = 0;
        this.name = "";
        this.nickname = "";
    }

    @Override
    public String toString() {
        return "DoubleHeroNode [no=" + no + ", name=" + name + ", nickname = " + nickname + "]";
    }
}