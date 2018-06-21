/**
 * Created by jingchun.zhang on 2018/6/20.
 */
package com.jc.tree;

/**
 * @author jingchun.zhang
 * @version 1.0.0
 * @date 2018/6/20
 *
 * 图的顶点
 *
 */
public class Vertex {

    public char lable;
    public boolean wasVisited;

    public Vertex(char lable) {
        this.lable = lable;
        this.wasVisited = false;
    }
}
