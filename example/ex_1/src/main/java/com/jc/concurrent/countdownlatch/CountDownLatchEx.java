/**
 * Created by jingchun.zhang on 2018/4/23.
 */
package com.jc.concurrent.countdownlatch;

/**
 * @author jingchun.zhang
 * @version 1.0.0
 * @date 2018/4/23
 */
public class CountDownLatchEx {

    public static void main(String[] args) throws Exception {
        CountDownLatchEx test = new CountDownLatchEx();
        test.test1();
    }

    private void test1() throws InterruptedException {
        java.util.concurrent.CountDownLatch cdl = new java.util.concurrent.CountDownLatch(2);
        System.out.println(cdl.getCount());
        for (int i=0; i<2; i++){
            new Thread(()->{
                try {
                    System.out.println(" start  id:" + Thread.currentThread().getId());
                    Thread.sleep(1000);
                    cdl.countDown();
                    System.out.println("end id:"+ Thread.currentThread().getId());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
        cdl.await();

//        cdl.await(1000, TimeUnit.MICROSECONDS);
        System.out.println(cdl.getCount());
    }

}
