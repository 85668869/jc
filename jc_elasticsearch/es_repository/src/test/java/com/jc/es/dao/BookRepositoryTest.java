package com.jc.es.dao;

import com.alibaba.fastjson.JSON;
import com.jc.es.BaseTest;
import com.jc.es.model.Book;
import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

/**
 * Created by jingchun.zhang on 2018/12/28.
 */
public class BookRepositoryTest extends BaseTest{

    @Resource
    BookRepository bookRepository;

    @Test
    public void page() throws Exception {
        ret = bookRepository.findAll(PageRequest.of(0, 2));
        System.out.println(JSON.toJSONString(ret));
        ret = bookRepository.findAll(PageRequest.of(0, 2, Sort.by(Order.asc("id"))));
    }
}
