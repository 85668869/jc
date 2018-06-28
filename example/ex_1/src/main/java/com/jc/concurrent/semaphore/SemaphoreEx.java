/**
 * Created by jingchun.zhang on 2018/4/24.
 */
package com.jc.concurrent.semaphore;

import java.util.concurrent.Semaphore;

/**
 * @author jingchun.zhang
 * @version 1.0.0
 * @date 2018/4/24
 */
public class SemaphoreEx {

    /**
     *  Semaphore使用例子：可以控制同时访问的线程个数，通过 acquire() 获取一个许可，如果没有就等待，而 release() 释放一个许可
     *
     * 执行结果：
     *<pre>
         1占用一个线程...
         2占用一个线程...
         0占用一个线程...
         1释放一个线程
         4占用一个线程...
         2释放一个线程
         0释放一个线程
         3占用一个线程...
         4释放一个线程
         3释放一个线程
     *</pre>
     *
     */
    public static void main(String[] args) {
        SemaphoreEx semaphoreEx = new SemaphoreEx();
        semaphoreEx.test();
    }

    public void test(){
        int resourceNum = 3;
        int threadNum = 5;
        //假设3个资源，且被5个线程公平获取
        Semaphore semaphore = new Semaphore(resourceNum, true);
        for (int i=0; i<threadNum; i++){
            new Work(semaphore, i).start();
        }
    }

    class Work extends Thread{
        private Semaphore semaphore;
        private int num;

        public Work(Semaphore semaphore, int num) {
            this.semaphore = semaphore;
            this.num = num;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println(this.num + "占用一个线程...");
                Thread.sleep(2000);
                System.out.println(this.num + "释放一个线程");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}
