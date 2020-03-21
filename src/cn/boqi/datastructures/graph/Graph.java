package cn.boqi.datastructures.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {

    private ArrayList<String> vertexList; //存储顶点的集合
    private int[][] edges; //存储图的对应的邻接矩阵
    private int numOfEdges; //表示边的数量
    //定义数组boolean[], 记录某个节点是否被访问过
    private boolean[] isVisited;

    public static void main(String[] args) {
        //测试一把图是否创建ok
        int n = 5; //顶点的个数
        String[] VertexValue = {"A", "B", "C", "D", "E"};
        //创建图对象
        Graph g = new Graph(n);
        //循环的添加顶点
        for (String value : VertexValue) {
            g.insertVertex(value);
        }

        //添加边
        g.insertEdge(0, 1, 1);
        g.insertEdge(1, 2, 1);

        g.insertEdge(0, 2, 1);
        g.insertEdge(1, 3, 1);
        g.insertEdge(1, 4, 1);

        //显示一把邻接矩阵
        g.showGraph();
        //g.dfs();
        g.bfs();

    }

    //构造器

    /**
     * @param n 顶点的数量
     */
    public Graph(int n) {
        //初始化矩阵
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        numOfEdges = 0;//因为边不知道多少条，所以初始化的时候为0
        isVisited = new boolean[n];
    }

    //插入顶点
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    //添加边

    /**
     * @param v1     顶点一对应的下标
     * @param v2     顶点二对应的下标
     * @param weight 这条边的权值
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }

    //得到第一个邻接顶点的下标

    /**
     * @param index 当前节点的名字
     * @return 如果存在，就返回对应的下标，否则返回-1
     */
    public int getFirstNeighbor(int index) {
        for (int j = 0; j < vertexList.size(); j++) {
            if (edges[index][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    //根据前一个邻接顶点的下标来获取下一个邻接顶点
    public int getNextNeighbor(int v1, int v2) {
        for (int j = v2 + 1; j < vertexList.size(); j++) {
            if (edges[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    //深度优先遍历算法
    //i 第一次进来就是0
    public void dfs(boolean[] isVisited, int i) {
        //首先我们访问该节点
        System.out.println(getValueByIndex(i) + "->");
        //将该节点设置为已经访问过
        isVisited[i] = true;

        //查找节点i的第一个邻接节点
        int w = getFirstNeighbor(i);

        while (w != -1) {//说明节点i有邻接节点
            if (!isVisited[w]) {
                dfs(isVisited, w);
            }

            //如果w这个节点已经被访问过,就访问下一个邻接节点
            w = getNextNeighbor(i, w);
        }
    }

    //对dfs进行一个重载，遍历我们所有的节点，并进行dfs
    public void dfs() {
        //遍历所有的节点，进行dfs（回溯）
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }


    public void bfs(boolean[] isVisited, int i) {
        int u; // 表示队列的头结点对应的下标
        int w; // 邻接节点w
        //队列， 节点访问的顺序
        LinkedList queue = new LinkedList<>();
        //访问节点：输出节点信息
        System.out.print(getValueByIndex(i) + "=>");
        //标记为已访问
        isVisited[i] = true;
        //将节点加入队列
        queue.addLast(i);

        //只要队列非空，就继续执行
        while (!queue.isEmpty()) {
            //取出队列的头结点下标
            u = (Integer) queue.removeFirst();//自动拆箱

            //得到第一个邻接顶点的下标w
            w = getFirstNeighbor(u);
            while (w != -1) { //说明，的确有邻接顶点
                if (!isVisited[w]) {
                    //访问这个邻接顶点
                    System.out.print(getValueByIndex(w) + "=>");
                    //标记已经访问
                    isVisited[w] = true;
                    //入队列
                    queue.addLast(w);
                }
                //如果访问过了，以u为核心的w的下一个顶点
                // 以A为广度核心，B的下一个节点的就是C
                w = getNextNeighbor(u, w); //这里体现出了我们的广度优先
            }
        }
    }

    //遍历所有的节点，都进行广度优先搜索
    //原因：有些节点可能根本没有链接，属于独立节点
    public void bfs() {
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited, i);
            }
        }
    }


    //图中常用的方法
    //返回节点的个数
    public int getNumOfVertex() {
        return vertexList.size();
    }

    //返回边的个数
    public int getNumOfEdges() {
        return numOfEdges;
    }

    //返回节点i（下标）对应的节点名称
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    //返回v1和v2的权值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    //显示图对应的矩阵
    public void showGraph() {
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }
}
