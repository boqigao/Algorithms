package cn.boqi.datastructures.tree.huffmantree;

import java.util.ArrayList;
import java.util.Collections;

public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
    }

    //创建霍夫曼树的方法
    public static Node createHuffmanTree(int[] arr) {

        //第一步为了操作方便
        //1.遍历arr数组
        //2.将arr数组的每个元素构成一个node
        //3.将node 放到ArrayList中

        ArrayList<Node> nodes = new ArrayList<>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }

        //我们处理的过程是一个循环的过程
        while (nodes.size() > 1) {

            //排序，从小到大
            Collections.sort(nodes);

            //取出根节点权值做小的两个二叉树节点
            //(1)取出权值最小的节点
            Node left = nodes.get(0);
            Node right = nodes.get(1);

            //(3)构建一棵新的二叉树
            Node parent = new Node(left.value + right.value);
            parent.left = left;
            parent.right = right;

            //(4)从arraylist中删除处理过的二叉树
            nodes.remove(left);
            nodes.remove(right);

            //(5) 将parent加入到nodes
            nodes.add(parent);
        }

        return nodes.get(0);

    }
}


// 创建节点类
// 为了让Node对象持续排序Collections集合排序
// 实现comparable接口
class Node implements Comparable<Node> {
    int value;
    char c;//字符
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node [value=" + " value " + "]";
    }

    @Override
    public int compareTo(Node o) {
        //如果将this写在前面，o写在后面，这是从小到大排序
        return this.value - o.value;
    }

}
