/**
 * Created by jingchun.zhang on 2018/6/20.
 */
package com.jc.tree;

/**
 * @author jingchun.zhang
 * @version 1.0.0
 * @date 2018/6/20
 * 栈
 */
public class S {

    //size
    private final int SIZE = 20;
    //data
    private int[] st;
    //top point
    private int top;

    public S(){
        st = new int[SIZE];
        top = -1;
    }

    public void push(int j){
        st[++top] = j;
    }

    public int pop(){
        return st[top--];
    }

    public int peek(){
        return st[top];
    }

    public boolean isEmpty(){
        return top==-1;
    }


}
