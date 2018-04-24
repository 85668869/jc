/**
 * Created by jingchun.zhang on 2018/4/23.
 */
package com.jc.spring.config;

import com.jc.spring.bean.Hello;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author jingchun.zhang
 * @version 1.0.0
 * @date 2018/4/23
 */
@Configuration
@ComponentScan("com.jc.spring")
public class SpringConfigEx {

    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfigEx.class);
        Hello hello = ac.getBean(Hello.class);
        System.out.println(hello);
        System.out.println(hello.say("lilei"));
    }
}
