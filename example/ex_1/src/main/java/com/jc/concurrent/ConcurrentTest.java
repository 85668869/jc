/**
 * Created by jingchun.zhang on 2018/6/28.
 */
package com.jc.concurrent;

import java.security.PrivateKey;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author jingchun.zhang
 * @version 1.0.0
 * @date 2018/6/28
 */
public class ConcurrentTest {

    private static Semaphore semaphore = new Semaphore(100);
    private static int count=0;
    private static int threadCount = 10000;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i=0; i<=threadCount; i++){
            semaphore.acquire();
            executorService.execute(()->{
                count++;
                semaphore.release();
            });
        }

        System.out.println(count);
    }

}
