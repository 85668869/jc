/**
 * Created by jingchun.zhang on 2018/9/12.
 */
package com.jc.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author jingchun.zhang
 * @version 1.0.0
 * @date 2018/9/12
 */
public class TimeClient {

    public static void main(String[] args) throws IOException {
        int port = 8080;

        if (args!=null && args.length > 0){
            try {
                port = Integer.valueOf(args[0]);
            }catch (Exception e){

            }
        }

        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;

        try {
            socket = new Socket("127.0.0.1", port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(),true);

            out.println("QUERY TIME ORDER");
            System.out.println("send order to server succeed.");
            String resp = in.readLine();
            System.out.println("Now is :" + resp);

        }catch (IOException e){

        }finally {
            if (out!= null){
                out.close();
                out = null;
            }

            if (in != null){
                in.close();
                in = null;
            }

            if (socket!=null){
                socket.close();
                socket = null;
            }
        }
    }

}
