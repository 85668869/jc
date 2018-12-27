/**
 * Created by jingchun.zhang on 2018/12/27.
 */
package com.jc.es.dao;


import com.jc.es.model.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author jingchun.zhang
 * @version 1.0.0
 * @date 2018/12/27
 */
public interface BookDao extends ElasticsearchRepository<Book, String> {

}
