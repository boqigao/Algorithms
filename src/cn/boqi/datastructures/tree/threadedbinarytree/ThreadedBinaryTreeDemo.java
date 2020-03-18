package cn.boqi.datastructures.tree.threadedbinarytree;


public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {

    }
}

//中序线索化二叉树
class BinaryTree {
    private HeroNode root;


    //为了实现线索化，需要创建要给指向当前节点的前驱结点
    //在递归线索化时候，总是保存前一个节点
    private HeroNode preNode;

    public void setRoot(HeroNode root) {
        this.root = root;
    }


    public void threadedNodes(HeroNode node) {
        //如果node == null， 不能线索化
        if (node == null) {
            return;
        }

        // （一）先线索化左子树
        threadedNodes(node.getLeft());
        // (二) 再线索化当前节点
        // 先处理当前节点的前驱结点
        if (node.getLeft() == null) {
            //让当前节点的左指针指向前驱结点
            node.setLeft(preNode);
            //要修改当前节点左指针的类型
            node.setLeftType(1);
        }

        // 处理后继节点
        if (preNode != null && preNode.getRight() == null) {
            //让前驱结点的右指针指向当前节点
            preNode.setRight(node);
            //修改前驱节点的右指针类型
            preNode.setRightType(1);
        }

        //！！！每处理一个节点后，让当前节点成为下一个节点的前驱结点
        preNode = node;

        // （三）再线索化右子树
        threadedNodes(node.getRight());


    }

    //前序遍历（其实各种遍历的触发的时候是从根节点开始的）
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("当前二叉树为空，无法遍历");
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("当前二叉树为空，无法遍历");
        }
    }

    //后序遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("当前二叉树为空，无法遍历");
        }
    }

    //前序查找
    public HeroNode preOrderSearch(int no) {
        if (this.root != null) {
            return root.preOrderSearch(no);
        } else {
            return null;
        }
    }

    //中序查找
    public HeroNode infixOrderSearch(int no) {
        if (this.root != null) {
            return root.infixOrderSearch(no);
        } else {
            return null;
        }
    }


    //后续查找
    public HeroNode postOrderSearch(int no) {
        if (this.root != null) {
            return root.postOrderSearch(no);
        } else {
            return null;
        }
    }
}


//先创建HeroNode节点
class HeroNode {
    private int no;
    private String name;
    private HeroNode left; //默认null
    private HeroNode right; //默认null

    //说明
    //1. 如果leftType == 0, 表示指向的是左子树
    //如果1则表示指向前驱结点
    //2. 如果rightType == 0， 表示指向的是右子树，如果是1则表示后继节点
    private int leftType;
    private int rightType;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name +
                '}';
    }

    //递归删除节点
    public void delNode(int no) {
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }

        //4.如果上面没有删除节点，那就要将左子树递归删除
        if (this.left != null) {
            this.left.delNode(no);
        }
        if (this.right != null) {
            this.right.delNode(no);
        }
    }

    //编写前序遍历的方法
    public void preOrder() {
        System.out.println(this); //先输出父节点
        //递归向左子树前序遍历
        if (this.left != null) {
            this.left.preOrder();
        }

        //递归向右子树前序遍历
        if (this.right != null) {
            this.right.preOrder();
        }

    }

    //中序遍历
    public void infixOrder() {

        //递归向左子树中序遍历
        if (this.left != null) {
            this.left.infixOrder();
        }

        //输出当前节点
        System.out.println(this);

        //递归向右子树中序遍历
        if (this.right != null) {
            this.right.infixOrder();
        }
    }


    //后序遍历
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }

        if (this.right != null) {
            this.right.postOrder();
        }

        System.out.println(this);
    }

    //前序遍历查找

    /**
     * @param no 查找的编号
     * @return 如果找到就返回这个node，如果没找到就返回null
     */
    public HeroNode preOrderSearch(int no) {

        //比较当前节点是不是
        if (this.no == no) {
            return this;
        }

        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }

        if (resNode != null) {
            return resNode;
        }

        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }

        return resNode;
    }

    public HeroNode infixOrderSearch(int no) {
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }

        if (resNode != null) {
            return resNode;
        }

        if (this.no == no) {
            return this;
        }

        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }

        return resNode;
    }


    public HeroNode postOrderSearch(int no) {
        HeroNode resNode = null;

        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }

        if (resNode != null) {
            return resNode;
        }

        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }

        if (resNode != null) {
            return resNode;
        }

        if (this.no == no) {
            return this;
        }

        return null;

    }
}