/**
 * Created by jingchun.zhang on 2018/4/24.
 */
package com.jc.spring.bean;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author jingchun.zhang
 * @version 1.0.0
 * @date 2018/4/24
 */
@Component
public class Hello {

    public String say(String name){
        return "hello " + name;
    }
}
