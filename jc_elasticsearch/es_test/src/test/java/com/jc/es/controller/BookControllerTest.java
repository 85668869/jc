package com.jc.es.controller;

import com.jc.es.Application;
import com.jc.es.dao.Book;
import com.jc.es.model.BookDao;
import javax.annotation.Resource;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by jingchun.zhang on 2018/12/27.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class BookControllerTest {

    @Resource
    BookDao bookDao;

    @org.junit.Test
    public void save() throws Exception {
        Book book = new Book();
        book.setId("book_1");
        book.setName("java编程实战");
        book.setMessage("我是第一本书");
        book.setType("文学");
        bookDao.save(book);
    }

    @org.junit.Test
    public void getById() throws Exception {
    }


}
