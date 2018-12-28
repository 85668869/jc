package com.jc.es.service.impl;

import com.jc.es.BaseTest;
import com.jc.es.config.EsRestClient;
import com.jc.es.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

public class BookServiceImplTest extends BaseTest{
    @Resource
    BookService bookService;

    @org.junit.Test
    public void testEsRestClient() {
        bookService.testEsRestClient();
    }

}
