/**
 * Created by jingchun.zhang on 2018/6/19.
 */
package com.jc;

import spark.Request;
import spark.Response;
import spark.Route;

import java.io.IOException;
import java.util.Scanner;

import static spark.Spark.*;
/**
 * @author jingchun.zhang
 * @version 1.0.0
 * @date 2018/6/19
 */
public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String s;
        while (true){
            s = scanner.next();
            System.out.println("out："+ s);
        }
    }

    private static void sparkTest(){
        System.out.println("测试");
        port(8080);
        get("/hello", (req, res) -> "Hello world!");

        get("/test", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                String name = request.params("name");
                return "hello " + name;
            }
        });
    }
}
