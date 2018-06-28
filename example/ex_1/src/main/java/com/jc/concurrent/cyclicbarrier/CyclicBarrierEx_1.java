/**
 * Created by jingchun.zhang on 2018/4/24.
 */
package com.jc.concurrent.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author jingchun.zhang
 * @version 1.0.0
 * @date 2018/4/24
 */
public class CyclicBarrierEx_1 {
    /**
     *  为await指定时间
     *
     *  代码在main方法的for循环中，故意让最后一个线程启动延迟，因为在前面三个线程都达到barrier之后，等待了指定的时间发现第四个线程还没有达到barrier，
     *  就抛出异常并继续执行后面的任务。
     * 执行结果：
     *<pre>
         线程Thread-0  开始写入数据...
         线程Thread-1  开始写入数据...
         线程Thread-2  开始写入数据...
         线程Thread-1写入数据完毕，等待其他线程写入完毕
         线程Thread-0写入数据完毕，等待其他线程写入完毕
         线程Thread-2写入数据完毕，等待其他线程写入完毕
         线程Thread-3  开始写入数据...
         Thread-2所有线程写入完毕，继续处理其他任务...
         Thread-0所有线程写入完毕，继续处理其他任务...
         Thread-1所有线程写入完毕，继续处理其他任务...
         java.util.concurrent.BrokenBarrierException
         at java.util.concurrent.CyclicBarrier.dowait(CyclicBarrier.java:250)
         at java.util.concurrent.CyclicBarrier.await(CyclicBarrier.java:435)
         at com.jc.concurrent.cyclicbarrier.CyclicBarrierEx_1$Writer_1.run(CyclicBarrierEx_1.java:91)
         java.util.concurrent.BrokenBarrierException
         at java.util.concurrent.CyclicBarrier.dowait(CyclicBarrier.java:250)
         at java.util.concurrent.CyclicBarrier.await(CyclicBarrier.java:435)
         at com.jc.concurrent.cyclicbarrier.CyclicBarrierEx_1$Writer_1.run(CyclicBarrierEx_1.java:91)
         java.util.concurrent.TimeoutException
         at java.util.concurrent.CyclicBarrier.dowait(CyclicBarrier.java:257)
         at java.util.concurrent.CyclicBarrier.await(CyclicBarrier.java:435)
         at com.jc.concurrent.cyclicbarrier.CyclicBarrierEx_1$Writer_1.run(CyclicBarrierEx_1.java:91)
         线程Thread-3写入数据完毕，等待其他线程写入完毕
         Thread-3所有线程写入完毕，继续处理其他任务...
         java.util.concurrent.BrokenBarrierException
         at java.util.concurrent.CyclicBarrier.dowait(CyclicBarrier.java:207)
         at java.util.concurrent.CyclicBarrier.await(CyclicBarrier.java:435)
         at com.jc.concurrent.cyclicbarrier.CyclicBarrierEx_1$Writer_1.run(CyclicBarrierEx_1.java:91)
     *</pre>
     *
     */
    public static void main(String[] args) {
        CyclicBarrierEx_1 cyclicBarrierEx = new CyclicBarrierEx_1();
        cyclicBarrierEx.test();
    }

    public void test() {
        int N = 4;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(N);

        for (int i=0; i<N; i++){
            if (i<N-1){
                new CyclicBarrierEx_1.Writer_1(cyclicBarrier).start();
            }else{
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                new CyclicBarrierEx_1.Writer_1(cyclicBarrier).start();
            }
        }
    }


    class Writer_1 extends Thread{
        private CyclicBarrier cyclicBarrier;
        public Writer_1(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            System.out.println("线程" + threadName + "  开始写入数据...");
            try {
                Thread.sleep(5000);
                System.out.println("线程" + threadName + "写入数据完毕，等待其他线程写入完毕");
                cyclicBarrier.await(2000, TimeUnit.MICROSECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"所有线程写入完毕，继续处理其他任务...");
        }
    }
}
