/**
 * Created by jingchun.zhang on 2018/12/27.
 */
package com.jc.es.controller;

import com.jc.es.dao.Book;
import com.jc.es.model.BookDao;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jingchun.zhang
 * @version 1.0.0
 * @date 2018/12/27
 */
@RestController
@RequestMapping("/book")
public class BookController {

    @Resource
    BookDao bookDao;

    @PostMapping("/insert")
    public Book save(Book book){
        bookDao.save(book);
        return book;
    }

    @GetMapping("/get/{id}")
    public Book getById(String id){
        return bookDao.findById(id).get();
    }
}
