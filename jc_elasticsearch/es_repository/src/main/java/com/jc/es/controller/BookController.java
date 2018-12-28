/**
 * Created by jingchun.zhang on 2018/12/27.
 */
package com.jc.es.controller;

import com.jc.es.dao.BookRepository;
import com.jc.es.model.Book;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.queryparser.xml.builders.BooleanQueryBuilder;
import org.apache.lucene.search.BooleanQuery;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author jingchun.zhang
 * @version 1.0.0
 * @date 2018/12/27
 */
@RestController
@RequestMapping("/book")
@Slf4j
public class BookController {

    private static String type[] = {"文学", "历史", "计算机", "政治"};
    private static Random random = new Random();

    @Resource
    BookRepository bookRepository;

    @GetMapping("/test")
    public String test(){
        return "This is test request!";
    }

    @GetMapping("/save")
    public Book save(){
        Book book = new Book();
        book.setId("B" + System.currentTimeMillis());
        book.setName("书" + System.currentTimeMillis());
        book.setMessage("这是一本书");
        book.setType(type[random.nextInt(3)]);
        bookRepository.save(book);

        log.debug("save book:{}", book);
        return book;
    }

    @GetMapping("/get")
    public Book getById(String id){
        log.debug("get book id:{}", id);
        return bookRepository.findById(id).get();
    }

    public Book getByIdWithPage(){
        QueryBuilder queryBuilder = QueryBuilders.boolQuery();
        return null;
    }
}
