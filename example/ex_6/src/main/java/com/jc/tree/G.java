/**
 * Created by jingchun.zhang on 2018/6/20.
 */
package com.jc.tree;

/**
 * @author jingchun.zhang
 * @version 1.0.0
 * @date 2018/6/20
 * graph
 */
public class G {

    public final int max_verts = 20;
    private Vertex[] vertexList;
    //存储邻接矩阵
    private int adjmatrix[][];
    private int nVerts;
    //存储图的节点
    private S stack;

    public G() {
        vertexList = new Vertex[max_verts];
        adjmatrix = new int[max_verts][max_verts];
        nVerts=0;
        stack = new S();
        this.init();
    }

    private void init(){
        for (int i=0;i<max_verts;i++){
            for (int j=0;j<max_verts;j++){
                adjmatrix[i][j]=0;
            }
        }
    }

    //插入一个顶点
    public void addVertex(char lable){
        vertexList[nVerts++] = new Vertex(lable);
    }

    //向无向图插入一条边
    public void addEdge(int start, int end){
        adjmatrix[start][end] = 1;
        adjmatrix[end][start] = 1;
    }

    //显示
    public void displayVerts(int v){
        System.out.println(vertexList[v].lable);
    }

    public int getAdjUnvisitedVertex(int v){
        for (int i=0; i<nVerts;i++){
            //顶点有邻接节点，并且是未访问过
            if (adjmatrix[v][i] == 1 && !vertexList[i].wasVisited){
                return i;
            }
        }
        return -1;
    }

    //dfs模拟最小生成树
    public void mst(){
        vertexList[0].wasVisited = true;
        stack.push(0);

        while (!stack.isEmpty()){
            int curVertex = stack.peek();
            int v = getAdjUnvisitedVertex(curVertex);
            if (v == -1){
                stack.pop();
            }else {
                vertexList[v].wasVisited = true;
                stack.push(v);
                displayVerts(curVertex);
                displayVerts(v);
                System.out.print(" ");
            }
        }
        //当栈内的所有节点都为空时，则将节点矩阵置为已访问过
        for (int j=0;j<nVerts;j++){
            vertexList[j].wasVisited = false;
        }
    }
}
