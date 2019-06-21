/**
 * Created by jingchun.zhang on 2019/4/16.
 */
package com.jc.lock;

/**
 * @author jingchun.zhang
 * @version 1.0.0
 * @date 2019/4/16
 */
public class A {

        public static void main(String[] args) {
            Object lock1 = new Object();
            Object lock2 = new Object();

           Thread t1 =  new Thread(new Runnable() {

                @Override
                public void run() {

                    while (true) {
                        synchronized (lock1) {
                            System.out.println("线程t1获取了 lock1锁");
                            try {
                                Thread.sleep(1000L);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            synchronized (lock2) {
                                System.out.println("线程t1获取了 lock2锁");
                            }
                        }
                    }
                }
            }, "t1");


            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {

                    while (true) {
                        synchronized (lock2) {
                            System.out.println("线程t2获取了 lock2锁");
                            try {
                                Thread.sleep(1000L);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            synchronized (lock1) {
                                System.out.println("线程t2获取了 lock1锁");
                            }
                        }
                    }
                }
            }, "t2");

            t1.start();
            t2.start();
            Thread.interrupted();
        }
}
