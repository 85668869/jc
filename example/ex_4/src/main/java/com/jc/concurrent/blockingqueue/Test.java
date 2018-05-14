/**
 * Created by jingchun.zhang on 2018/4/26.
 */
package com.jc.concurrent.blockingqueue;

import java.util.concurrent.SynchronousQueue;

/**
 * @author jingchun.zhang
 * @version 1.0.0
 * @date 2018/4/26
 */
public class Test {

    public static void main(String[] args) {

    }

    private static void test1() throws InterruptedException {
        SynchronousQueue queue = new SynchronousQueue();
        queue.put(1);
        queue.put(2);
    }
}
