/**
 * Created by jingchun.zhang on 2018/4/28.
 */
package com.jc.concurrent.executorservice;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author jingchun.zhang
 * @version 1.0.0
 * @date 2018/4/28
 */
public class ExecutorServiceEx {

    public static void main(String[] args) throws Exception {
        ExecutorServiceEx executorServiceEx = new ExecutorServiceEx();
//        executorServiceEx.test2();
//        executorServiceEx.test1();
//        executorServiceEx.test3();
        executorServiceEx.test4();
    }

    private void test4() throws Exception {
        ExecutorService es = Executors.newFixedThreadPool(10);
        List<Callable<String>> callables = new ArrayList<>();
        callables.add(()->{
            System.out.println("run task1...");
            int count = 0;
            do{
                count++;
            }while (count > 1000*1000*100);
            System.out.println("doing task1...");
            return "task1";
        });
        callables.add(()->{
            System.out.println("run task2...");
            int count = 0;
            do{
                count++;
            }while (count > 1000*1000*1000);
            System.out.println("doing task2...");
            return "task2";
        });
        callables.add(()->{
            System.out.println("doing task3...");
            Integer.parseInt("as");
            return "task3";
        });
//        String f = es.invokeAny(callables);
//        System.out.println(f);
        System.out.println("输出结果：");
        List<Future<String>> fl = es.invokeAll(callables);
        fl.stream().forEach(s->{
            try {
                System.out.println(s.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });

    }

    private void test3() throws Exception {
        ExecutorService es = Executors.newFixedThreadPool(10);
        People p = new People();
        p.age = 10;
        System.out.println("执行前:" + p);
        Future<People> f = es.submit(()->{
            p.name = "san";
            p.age = 15;
        }, p);
        System.out.println(f.get());
        System.out.println("执行后:" + p);
    }

    private void test2() throws Exception {
        ExecutorService es = Executors.newFixedThreadPool(10);
        es.execute(()->{
            Thread t = Thread.currentThread();
            System.out.println("async doing。。。" + t.getId());
            try {
                t.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("async finish...");
        });

        es.execute(()->{
            Thread t = Thread.currentThread();
            System.out.println("2  async doing。。。" + t.getId());
            try {
                t.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("2  async finish...");
        });

    }

    private void test1() throws Exception{
        ExecutorService es = Executors.newFixedThreadPool(10);
        Future f = es.submit(()->{
            Thread t = Thread.currentThread();
            System.out.println("async doing。。。" + t.getId());
            try {
                t.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("async finish...");
        });



        Future f1 = es.submit(()->{
            Thread t = Thread.currentThread();
            System.out.println("callback async doing..." + t.getId());
            try {
                t.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("callback async finish...");
            return 2;
        });
        System.out.println(f.isDone());
        System.out.println(f.get());
        System.out.println(f1.get());
    }

    class People {
        public  String name;
        public int age;

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
        }
    }

}
