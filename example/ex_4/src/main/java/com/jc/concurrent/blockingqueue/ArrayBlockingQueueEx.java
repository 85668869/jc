/**
 * Created by jingchun.zhang on 2018/4/26.
 */
package com.jc.concurrent.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author jingchun.zhang
 * @version 1.0.0
 * @date 2018/4/26
 */
public class ArrayBlockingQueueEx {

    /***
     *
     * 输出：
     *  <pre>
         product put: 0
         product put: 1
         product put: 2
         product put: 3
         consumer take: 0
         consumer take: 1
         consumer take: 2
         consumer take: 3
         consumer take: 4
         product put: 4
         product put: 5
         consumer take: 5
         product put: 6
         consumer take: 6
         product put: 7
         consumer take: 7
         product put: 8
         consumer take: 8
         product put: 9
         consumer take: 9
     * </pre>
     */
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueueEx queueEx = new ArrayBlockingQueueEx();
        queueEx.test();
    }

    private void test() throws InterruptedException {
        BlockingQueue queue = new ArrayBlockingQueue(1024);
        new Thread(new Product(queue)).start();
        Thread.sleep(5000);
        new Thread(new Consumer(queue)).start();
    }
}
