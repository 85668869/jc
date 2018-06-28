/**
 * Created by jingchun.zhang on 2018/4/26.
 */
package com.jc.concurrent.blockingqueue;

import java.util.concurrent.BlockingQueue;

/**
 * @author jingchun.zhang
 * @version 1.0.0
 * @date 2018/4/26
 */
public class Consumer implements Runnable{
    protected BlockingQueue blockingQueue;

    public Consumer(BlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        for (; ; ) {
            try {
                System.out.println("consumer take: " + blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
