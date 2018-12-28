package com.jc.es.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Objects;

@Slf4j
@Configuration
@ComponentScan(basePackages = "com.jc.es")
public class EsRestClient {

    private static final int ADDRESS_LENGTH = 2;
    private static final String HTTP_SCHEME = "http";

//    @Value("${elasticsearch.ip}")
    @Value("127.0.0.1:9200")
    String[] ipAddress;

    @Bean
    public RestClientBuilder restClientBuilder(){
        HttpHost[] hosts = Arrays.stream(ipAddress)
                .map(this::makeHttpHost)
                .filter(Objects::nonNull)
                .toArray(HttpHost[]::new);
        log.debug("host:{}",  Arrays.toString(hosts));
        RestClientBuilder restClientBuilder = RestClient.builder(hosts);
        //配置链接时间延迟
        restClientBuilder.setRequestConfigCallback(builder->{
            builder.setConnectTimeout(1000);
            builder.setSocketTimeout(30000);
            builder.setConnectionRequestTimeout(500);
            return builder;
        });
        restClientBuilder.setMaxRetryTimeoutMillis(60000);
        return RestClient.builder(hosts);
    }

    @Bean
    public RestClient restClient(@Autowired RestClientBuilder restClientBuilder){
        return restClientBuilder.build();
    }

    @Bean
    public RestHighLevelClient restHighLevelClient(@Autowired RestClientBuilder restClientBuilder){
        return new RestHighLevelClient(restClientBuilder);
    }

    private HttpHost makeHttpHost(String s){
        log.debug("makeHttpHost:{}", s);
        assert StringUtils.isNotEmpty(s);
        String[] address = s.split(":");
        if (address.length == ADDRESS_LENGTH){
            String ip = address[0];
            int port = Integer.parseInt(address[1]);
            return new HttpHost(ip, port);
        }else {
            return null;
        }
    }
}
