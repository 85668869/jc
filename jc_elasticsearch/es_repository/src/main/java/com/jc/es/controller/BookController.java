/**
 * Created by jingchun.zhang on 2018/12/27.
 */
package com.jc.es.controller;

import com.jc.es.dao.BookRepository;
import com.jc.es.model.Book;
import lombok.extern.slf4j.Slf4j;
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

    @Resource
    BookRepository bookRepository;

    @GetMapping("/save")
    public Book save(){
        Book book = new Book();
        book.setId("B" + System.currentTimeMillis());
        book.setName("书" + System.currentTimeMillis());
        book.setMessage("这是一本书");
        bookRepository.save(book);

        log.debug("save book:{}", book);
        return book;
    }

    @GetMapping("/get")
    public Book getById(String id){
        log.debug("get book id:{}", id);
        return bookRepository.findById(id).get();
    }
}
