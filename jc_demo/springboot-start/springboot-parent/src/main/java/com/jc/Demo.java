package com.jc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;

/**
 * Created by jingchun.zhang on 2017/6/12.
 */
@Controller
@EnableAutoConfiguration
public class Demo {
    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!  hahaha";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Demo.class, args);
//        System.out.println(isWindows());
    }
    private static boolean isWindows() {
        return File.separatorChar == '\\';
    }
}
