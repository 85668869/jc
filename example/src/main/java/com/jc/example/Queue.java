/**
 * Created by jingchun.zhang on 2018/4/12.
 */
package com.jc.example;

import com.alibaba.fastjson.JSONObject;

import java.util.NoSuchElementException;

/**
 * @author jingchun.zhang
 * @version 1.0.0
 * @date 2018/4/12
 */
public class Queue {
    protected Object[] data;
    protected int size, head, tail;

    public Queue() {
        final int INITIAL_LENGTH = 3;
        data = new Object[INITIAL_LENGTH];
        size = 0;
        head = 0;
        tail = -1;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public Object front(){
        if (size==0)
            throw new NoSuchElementException();
        return data[head];
    }

    public void enqueue(Object element){
        if (size == data.length){
            Object[] oldData = data;
            data = new Object[data.length * 2];
            //copy old data
            System.arraycopy(oldData, head, data, 0, oldData.length - head);
            if (head > 0){
                System.arraycopy(oldData, 0, data, head+1, tail+1);
            }
            head = 0;
            tail = oldData.length -1;
        }
        tail = (tail + 1)% data.length;
        size++;
        data[tail] = element;
    }

    public Object dequeue(){
        if (size==0)
            throw new NoSuchElementException();
        Object element = data[head];
        head = (head+1)%data.length;
        return element;
    }

    public void print(){
        System.out.println("head:" + head + "  tail:"+tail+"  size:"+size);
        System.out.println(JSONObject.toJSONString(data));
        System.out.println("");
    }

    public static void main(String[] args) {
        Queue queue = new Queue();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.print();
        queue.dequeue();
        queue.dequeue();
        queue.print();
//        queue.enqueue(4);
        queue.dequeue();
        queue.dequeue();
        queue.print();
    }

    private void test1(){
        Queue queue = new Queue();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.print();
        queue.dequeue();
        queue.print();
        queue.enqueue(4);
        queue.print();
        queue.dequeue();
        queue.print();
    }
}
