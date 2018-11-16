package com.jc.application;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Created by jingchun.zhang on 2017/6/9.
 */
public class Application {
    public static void main(String[] args) throws IOException {
        System.out.println("===========start===============");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"spring-provider.xml"});
        context.start();
        System.out.println("=============end==============");
        System.out.println("按任意键退出……");
        System.in.read();

    }
}
