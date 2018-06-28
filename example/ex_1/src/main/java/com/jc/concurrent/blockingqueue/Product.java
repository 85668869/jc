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
public class Product implements Runnable{
    protected BlockingQueue blockingQueue;
    protected int N = 100;

    public Product(BlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < N; i++) {
                blockingQueue.put(i);
                System.out.println("product put: " + i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
