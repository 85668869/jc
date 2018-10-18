/**
 * Created by jingchun.zhang on 2018/9/12.
 */
package com.jc.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * @author jingchun.zhang
 * @version 1.0.0
 * @date 2018/9/12
 *
 * BIO:同步阻塞式I/O
 * 服务端针对每一个客户端请求创建一个线程处理
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
            while (true){
                socket = serverSocket.accept();
                System.out.println("accept msg and new thread handle ...");
                new Thread(new TimeServerHandler(socket)).start();
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
