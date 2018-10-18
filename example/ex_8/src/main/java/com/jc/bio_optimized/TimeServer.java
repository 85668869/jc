/**
 * Created by jingchun.zhang on 2018/9/12.
 */
package com.jc.bio_optimized;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author jingchun.zhang
 * @version 1.0.0
 * @date 2018/9/12
 *
 * BIO:同步阻塞式I/O 优化版
 *
 * 伪异步：服务端采用线程池实现，针对客户端请求，从线程池中取空闲线程处理
 */
public class TimeServer {
    public static void main(String[] args) throws IOException {
        int port = 8080;

        if (args!=null && args.length > 0){
            try {
                port = Integer.valueOf(args[0]);
            }catch (Exception e){

            }
        }

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("The time server start in port:" + port);
            Socket socket = null;

            //创建任务线程池
            TimeServerHandlerExecutePool singleExecutor = new TimeServerHandlerExecutePool(50, 10000);

            while (true){
                socket = serverSocket.accept();
                System.out.println("accept msg and new thread handle ...");
                singleExecutor.execute(new TimeServerHandler(socket));
            }
        }finally {
            if (serverSocket != null){
                serverSocket.close();
                serverSocket = null;
                System.out.println("The time server close!");
            }
            System.out.println("end");
        }

    }
}
