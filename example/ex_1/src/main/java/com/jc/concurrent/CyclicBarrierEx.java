/**
 * Created by jingchun.zhang on 2018/4/23.
 */
package com.jc.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author jingchun.zhang
 * @version 1.0.0
 * @date 2018/4/23
 */
public class CyclicBarrierEx {

    /**
     *  在所有线程写入操作完之后，为CyclicBarrier提供Runnable参数进行额外的其他操作:
     *
     *  从结果可以看出，当四个线程都到达barrier状态后，会从四个线程中选择一个线程去执行Runnable
     * 执行结果：
     *<pre>
         线程Thread-1  开始写入数据
         线程Thread-2  开始写入数据
         线程Thread-3  开始写入数据
         线程Thread-0  开始写入数据
         线程Thread-1写入数据完毕，等待其他线程写入完毕
         线程Thread-2写入数据完毕，等待其他线程写入完毕
         线程Thread-3写入数据完毕，等待其他线程写入完毕
         线程Thread-0写入数据完毕，等待其他线程写入完毕
         当前线程Thread-0
         所有线程写入完毕，继续处理其他任务...
         所有线程写入完毕，继续处理其他任务...
         所有线程写入完毕，继续处理其他任务...
         所有线程写入完毕，继续处理其他任务...
     *</pre>
     *
     */
    public static void main(String[] args) {
        CyclicBarrierEx cyclicBarrierEx = new CyclicBarrierEx();
        cyclicBarrierEx.test();
    }

    public void test() {
        int N = 4;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(N, ()->{
            System.out.println("当前线程" + Thread.currentThread().getName());
        });

        for (int i=0; i<N; i++){
            new Writer(cyclicBarrier).start();
        }
    }


    class Writer extends Thread{
        private CyclicBarrier cyclicBarrier;
        public Writer(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            System.out.println("线程" + threadName + "  开始写入数据");
            try {
                Thread.sleep(5000);
                System.out.println("线程" + threadName + "写入数据完毕，等待其他线程写入完毕");
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("所有线程写入完毕，继续处理其他任务...");
        }
    }
}
