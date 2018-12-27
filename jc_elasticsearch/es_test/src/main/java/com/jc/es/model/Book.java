/**
 * Created by jingchun.zhang on 2018/12/27.
 */
package com.jc.es.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author jingchun.zhang
 * @version 1.0.0
 * @date 2018/12/27
 */
@Getter
@Setter
@Document(indexName = "zhjc", type = "test1")
public class Book {
    @Id
    String id;
    String name;
    String message;
    String type;
}
