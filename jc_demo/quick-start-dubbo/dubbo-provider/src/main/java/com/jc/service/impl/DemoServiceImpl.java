package com.jc.service.impl;

import com.jc.service.DemoService;

/**
 * Created by jingchun.zhang on 2017/6/9.
 */
public class DemoServiceImpl implements DemoService{

    public String sayHello(String name) {
        return "Hello" + name;
    }
}
