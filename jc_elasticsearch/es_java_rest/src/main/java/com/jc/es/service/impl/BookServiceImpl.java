package com.jc.es.service.impl;

import com.jc.es.service.BookService;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    RestHighLevelClient restHighLevelClient;

    @Override
    public boolean testEsRestClient(){
        SearchRequest searchRequest = new SearchRequest("gdp_tops*");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.termQuery("city","北京"));
        searchRequest.source(sourceBuilder);

        SearchResponse response = null;
        try {
            response = restHighLevelClient.search(searchRequest);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        Arrays.stream(response.getHits().getHits())
                .forEach(i->{
                    System.out.println(i.getIndex());
                    System.out.println(i.getType());
                    System.out.println(i.getSourceAsString());
                });
        System.out.println(response.getHits().totalHits);
        return true;
    }
}
