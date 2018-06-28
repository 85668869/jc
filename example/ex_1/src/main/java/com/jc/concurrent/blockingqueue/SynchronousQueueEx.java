/**
 * Created by jingchun.zhang on 2018/4/26.
 */
package com.jc.concurrent.blockingqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * @author jingchun.zhang
 * @version 1.0.0
 * @date 2018/4/26
 */
public class SynchronousQueueEx {
    public static void main(String[] args) throws InterruptedException {
        SynchronousQueueEx queueEx = new SynchronousQueueEx();
        queueEx.test();
    }

    private void test() throws InterruptedException {
        BlockingQueue queue = new SynchronousQueue();
        new Thread(new Product(queue)).start();
        Thread.sleep(500000);
        new Thread(new Consumer(queue)).start();
    }
}
