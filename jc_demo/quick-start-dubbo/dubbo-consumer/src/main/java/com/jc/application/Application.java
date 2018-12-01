package com.jc.application;

import com.jc.service.DemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;


public class Application {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"spring-consumer.xml"});
        context.start();

        DemoService demoService = (DemoService)context.getBean("demoService");
        String hello = demoService.sayHello("world");

        System.out.println("result:" + hello);
    }
}
