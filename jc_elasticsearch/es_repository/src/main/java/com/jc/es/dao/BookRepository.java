package com.jc.es.dao;

import com.jc.es.model.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

@Component
public interface BookRepository extends ElasticsearchRepository<Book, String>{
}
