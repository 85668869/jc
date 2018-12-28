//package com.jc.es.config;
//
//import java.util.ArrayList;
//import java.util.List;
//import org.apache.http.HttpHost;
//import org.elasticsearch.client.RestClient;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Scope;
//import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
//
///**
// * @author : kuo.sun
// * @version : searchcenter
// *          Created in 13:06 on 2018/5/22.
// */
////@Configuration
//public class ESConfig {
//
//
//    @Bean(initMethod = "init" , destroyMethod = "close")
//    public EsClientFactory getFactory() throws Throwable{
//        return EsClientFactory.build(listHttpHost());
//    }
//
//    @Bean
//    @Scope("singleton")
//    public RestClient getRestClient() throws Throwable{
//        return getFactory().getRestClient();
//    }
//
//    /**
//     * 将配置的host集群转化为HttpHost集合信息
//     * */
//    private List<HttpHost> listHttpHost() throws Exception{
//        String host = "localhost:9200";
//        String schema = "http";
//        if(host == null){
//            throw new Exception();
//        }
//        List<HttpHost> httpHosts = new ArrayList<>();
//        String[] split = host.split(",");
//        for(String s : split){
//            String[] hs = s.split(":");
//            httpHosts.add(new HttpHost(hs[0].trim(), Integer.valueOf(hs[1].trim()), schema));
//        }
//        if(httpHosts.isEmpty()){
//            throw new Exception();
//        }
//        return httpHosts;
//    }
//}
