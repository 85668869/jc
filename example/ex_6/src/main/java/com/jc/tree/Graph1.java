/**
 * Created by jingchun.zhang on 2018/6/20.
 */
package com.jc.tree;

/**
 * @author jingchun.zhang
 * @version 1.0.0
 * @date 2018/6/20
 */
public class Graph1 {

    public static void main(String[] args) {
        test2();
    }
    private static void test2(){
        G theGraph = new G();
        theGraph.addVertex('A');   //0
        theGraph.addVertex('B');   //1
        theGraph.addVertex('C');   //2
        theGraph.addVertex('D');   //3
        theGraph.addEdge(0, 1);    //AB
        theGraph.addEdge(0, 2);    //AC
        theGraph.addEdge(0, 3);    //AD
        theGraph.addEdge(1, 3);    //BD
        System.out.print("Minimum spanning tree: ");
        theGraph.mst();
        System.out.println();
    }

    private static void test1(){
        G theGraph = new G();
        theGraph.addVertex('A');   //0
        theGraph.addVertex('B');   //1
        theGraph.addVertex('C');   //2
        theGraph.addVertex('D');   //3
        theGraph.addVertex('E');   //4
        theGraph.addEdge(0, 1);    //AB
        theGraph.addEdge(0, 2);    //AC
        theGraph.addEdge(0, 3);    //AD
        theGraph.addEdge(0, 4);    //AE
        theGraph.addEdge(1, 2);    //BC
        theGraph.addEdge(1, 3);    //BD
        theGraph.addEdge(1, 4);    //BE
        theGraph.addEdge(2, 3);    //CD
        theGraph.addEdge(2, 4);    //CE
        theGraph.addEdge(3, 4);    //DE
        System.out.print("Minimum spanning tree: ");
        theGraph.mst();
        System.out.println();
    }

}
