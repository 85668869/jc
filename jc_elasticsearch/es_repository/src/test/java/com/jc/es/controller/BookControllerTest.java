package com.jc.es.controller;

import com.jc.es.dao.BookRepository;
import com.jc.es.model.Book;
import java.util.Random;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by jingchun.zhang on 2018/12/28.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class BookControllerTest {

    private static String type[] = {"文学", "历史", "计算机", "政治"};
    private static Random random = new Random();

    @Resource
    BookRepository bookRepository;

    @Test
    public void save() throws Exception {

        Book book = new Book();
        book.setId("T" + System.currentTimeMillis());
        book.setName("书" + System.currentTimeMillis());
        book.setMessage("这是一本书");
        book.setType(type[random.nextInt(3)]);
        log.debug("save book:{}", book);
        bookRepository.save(book);
    }

    @Test
    public void getById() throws Exception {
    }

    @Test
    public void test() throws Exception {


    }
}
