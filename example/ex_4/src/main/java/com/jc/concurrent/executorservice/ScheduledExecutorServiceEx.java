/**
 * Created by jingchun.zhang on 2018/4/28.
 */
package com.jc.concurrent.executorservice;

import java.util.concurrent.*;

/**
 * @author jingchun.zhang
 * @version 1.0.0
 * @date 2018/4/28
 */
public class ScheduledExecutorServiceEx {

    public static void main(String[] args) throws Exception{
        ScheduledExecutorServiceEx scheduledExecutorServiceEx = new ScheduledExecutorServiceEx();
//        scheduledExecutorServiceEx.test1();
//        scheduledExecutorServiceEx.test2();
        scheduledExecutorServiceEx.test3();

    }

    private volatile int time = 0;

    //延迟3秒，然后每2秒执行一次
    private void test3() throws Exception{
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(5);
        time = getNowByS();
        System.out.println("init time:" + time);
        ses.scheduleWithFixedDelay(()->{
            System.out.println("执行开始  time:" + (getNowByS() - time));
            System.out.println("执行一次。。");
//            int count = 0;
//            int s = (Integer.MAX_VALUE - 8)/3;
//            while (++count < Integer.MAX_VALUE - 8){
//                if (count%s == 0){
//                    System.out.println("aa:" + count);
//                }
//            }
//            System.out.println("count:" + count);
            System.out.println("执行结束  time:" + (getNowByS() - time));
            System.out.println("执行结束一次...");
            time = getNowByS();
        }, 3, 2, TimeUnit.SECONDS);
    }

    //延迟3秒，然后每2秒执行一次
    private void test2() throws Exception{
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(5);
        time = getNowByS();
        System.out.println("init time:" + time);
        ses.scheduleAtFixedRate(()->{
            System.out.println("time:" + (getNowByS() - time));
            System.out.println("执行一次。。");
            int count = 0;
            int s = (Integer.MAX_VALUE - 8)/3;
            while (++count < Integer.MAX_VALUE - 8){
                if (count%s == 0){
                    System.out.println("aa:" + count);
                }
            }
            System.out.println("count:" + count);
            System.out.println("执行结束一次...");
            time = getNowByS();
        }, 3, 2, TimeUnit.SECONDS);


        ses.scheduleAtFixedRate(()->{
        }, 3, 2, TimeUnit.SECONDS);
    }



    private void test1() throws ExecutionException, InterruptedException {
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(5);
        ScheduledFuture sf = ses.schedule(()->{
            System.out.println("runable runing...");
        }, 5, TimeUnit.SECONDS);


        ScheduledFuture sf1 = ses.schedule(()->{
            System.out.println("callable runing...");
            return "bb";
        }, 3, TimeUnit.SECONDS);
        System.out.println(sf1.get());
        System.out.println(sf.get());

    }

    public static int getNowByS(){
        return (int) (System.currentTimeMillis()/1000);
    }

}
