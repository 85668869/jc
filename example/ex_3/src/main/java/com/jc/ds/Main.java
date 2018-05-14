/**
 * Created by jingchun.zhang on 2018/4/26.
 */
package com.jc.ds;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author jingchun.zhang
 * @version 1.0.0
 * @date 2018/4/26
 */
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        BlockingQueue blockingQueue = new ArrayBlockingQueue(3);
        blockingQueue.add(1);
        blockingQueue.add(2);
        blockingQueue.add(3);
//        blockingQueue.remove(4);
//        Object o = blockingQueue.element(3);
//        System.out.println(o);
//        blockingQueue.offer(4);
//        blockingQueue.poll();
        blockingQueue.take();
        blockingQueue.add(4);
        System.out.println(blockingQueue);
    }
}
