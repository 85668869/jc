/**
 * Created by jingchun.zhang on 2018/4/24.
 */
package com.jc.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author jingchun.zhang
 * @version 1.0.0
 * @date 2018/4/24
 */
public class CyclicBarrierEx_2 {
    /**
     *  CyclicBarrier重用的例子：
     *
     *  在初次的4个线程越过barrier状态后，又可以用来进行新一轮的使用。而CountDownLatch无法进行重复使用.
     *
     * 执行结果：
     *<pre>
         线程Thread-0  开始写入数据...
         线程Thread-1  开始写入数据...
         线程Thread-2  开始写入数据...
         线程Thread-3  开始写入数据...
         线程Thread-2写入数据完毕，等待其他线程写入完毕
         线程Thread-1写入数据完毕，等待其他线程写入完毕
         线程Thread-0写入数据完毕，等待其他线程写入完毕
         线程Thread-3写入数据完毕，等待其他线程写入完毕
         Thread-3所有线程写入完毕，继续处理其他任务...
         Thread-2所有线程写入完毕，继续处理其他任务...
         Thread-1所有线程写入完毕，继续处理其他任务...
         Thread-0所有线程写入完毕，继续处理其他任务...
         cyclicbarrier 重用
         线程Thread-4  开始写入数据...
         线程Thread-5  开始写入数据...
         线程Thread-6  开始写入数据...
         线程Thread-7  开始写入数据...
         线程Thread-6写入数据完毕，等待其他线程写入完毕
         线程Thread-5写入数据完毕，等待其他线程写入完毕
         线程Thread-4写入数据完毕，等待其他线程写入完毕
         线程Thread-7写入数据完毕，等待其他线程写入完毕
         Thread-7所有线程写入完毕，继续处理其他任务...
         Thread-5所有线程写入完毕，继续处理其他任务...
         Thread-6所有线程写入完毕，继续处理其他任务...
         Thread-4所有线程写入完毕，继续处理其他任务...
     *</pre>
     *
     */
    public static void main(String[] args) {
        CyclicBarrierEx_2 cyclicBarrierEx = new CyclicBarrierEx_2();
        cyclicBarrierEx.test();
    }

    public void test() {
        int N = 4;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(N);
        for (int i=0; i<N; i++){
            new CyclicBarrierEx_2.Writer_2(cyclicBarrier).start();
        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("cyclicbarrier 重用");

        for (int i=0; i<N; i++){
            new CyclicBarrierEx_2.Writer_2(cyclicBarrier).start();
        }
    }


    class Writer_2 extends Thread{
        private CyclicBarrier cyclicBarrier;
        public Writer_2(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            System.out.println("线程" + threadName + "  开始写入数据...");
            try {
                Thread.sleep(5000);
                System.out.println("线程" + threadName + "写入数据完毕，等待其他线程写入完毕");
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"所有线程写入完毕，继续处理其他任务...");
        }
    }
}
