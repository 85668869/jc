package com.jc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jingchun.zhang on 2017/6/12.
 */
@EnableAutoConfiguration
@RestController
@Scope("prototype")
public class Demo{


    @RequestMapping("/{name}")
    Object home(@PathVariable String name) {
        Map map = new HashMap<String, String>();
        map.put("name",name);
        map.put("tell","Hello World!"+name);
//        return "Hello World!"+name;
        return  map;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Demo.class, args);
    }

    @Bean
    public EmbeddedServletContainerCustomizer embeddedServletContainerCustomizer(){
        System.out.println("doing。。。");
        return (configurableEmbeddedServletContainer)->{
            configurableEmbeddedServletContainer.setPort(9090);
        };
    }
}
