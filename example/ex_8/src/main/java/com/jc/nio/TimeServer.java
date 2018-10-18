/**
 * Created by jingchun.zhang on 2018/9/12.
 */
package com.jc.nio;

import com.jc.bio.TimeServerHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author jingchun.zhang
 * @version 1.0.0
 * @date 2018/9/12
 *
 * NIO:
 *
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



    }
}
