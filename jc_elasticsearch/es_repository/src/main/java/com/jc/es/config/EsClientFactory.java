package com.jc.es.config;

import static org.elasticsearch.client.RestClient.builder;

import java.util.List;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;

/**
 * @author : kuo.sun
 * @version : xdw-usercenter
 *          Created in 9:49 on 2018/5/18.
 */
public class EsClientFactory {
    /**
     *ES客户端的配置，可以在属性文件中更换
     * */
    private static Integer CONNECT_TIMEOUT_MILLIS = 1000;
    private static Integer SOCKET_TIMEOUT_MILLIS = 30000;
    private static Integer CONNECTION_REQUEST_TIMEOUT_MILLIS = 500;
    private static Integer MAX_CONN_TOTAL = 30;
    private static Integer MAX_CONN_PER_ROUTE = 10;
    private static List<HttpHost> HTTP_HOST_LIST;

    private RestClient restClient;
//    private RestHighLevelClient restHighLevelClient;

    private  static EsClientFactory singleton = new EsClientFactory();

    private EsClientFactory() {
    }

    /**
     * 获取低级java客户端
     * */
    public RestClient getRestClient(){
        return restClient;
    }

    /**
     * 获取高级客户端
     * */
//    public RestHighLevelClient getRestHighLevelClient(){
//        return restHighLevelClient;
//    }

    /**
     * 构建es的客户端工厂
     * */
    public static EsClientFactory build(List<HttpHost> httpHosts, Integer connectTimeoutMillis, Integer socketTimeoutMillis,
                                        Integer connectionRequestTimeoutMillis, Integer maxConnectNum, Integer maxConnectPerRoute){
        HTTP_HOST_LIST = httpHosts;
        CONNECT_TIMEOUT_MILLIS = initProperty(connectTimeoutMillis, CONNECT_TIMEOUT_MILLIS);
        SOCKET_TIMEOUT_MILLIS = initProperty(socketTimeoutMillis, SOCKET_TIMEOUT_MILLIS);
        CONNECTION_REQUEST_TIMEOUT_MILLIS = initProperty(connectionRequestTimeoutMillis, CONNECTION_REQUEST_TIMEOUT_MILLIS);
        MAX_CONN_TOTAL = initProperty(maxConnectNum, MAX_CONN_TOTAL);
        MAX_CONN_PER_ROUTE = initProperty(maxConnectPerRoute, MAX_CONN_PER_ROUTE);
        return singleton;
    }

    public static EsClientFactory build(List<HttpHost> httpHosts){
        HTTP_HOST_LIST = httpHosts;
        return singleton;
    }



    /**
     * 工厂初始化
     * */
    public void init(){
        RestClientBuilder restClientBuilder = builder(HTTP_HOST_LIST.toArray(new HttpHost[HTTP_HOST_LIST.size()]));
        //配置链接时间延迟
        restClientBuilder.setRequestConfigCallback(builder->{
            builder.setConnectTimeout(CONNECT_TIMEOUT_MILLIS);
            builder.setSocketTimeout(SOCKET_TIMEOUT_MILLIS);
            builder.setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT_MILLIS);
            return builder;
        });
        // 设置异步httpclient并发连接数
        restClientBuilder.setHttpClientConfigCallback(httpAsyncClientBuilder->{
            httpAsyncClientBuilder.setMaxConnTotal(MAX_CONN_TOTAL);
            httpAsyncClientBuilder.setMaxConnPerRoute(MAX_CONN_PER_ROUTE);
            return httpAsyncClientBuilder;
        });
        restClient = restClientBuilder.build();
//        restHighLevelClient = new RestHighLevelClient(restClientBuilder);
    }

    /**
     *  工厂关闭
     * */
    public void close(){
        try{
            if(restClient != null){
                restClient.close();
            }
//            if(restHighLevelClient != null){
//                restHighLevelClient.close();
//            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 属性拷贝
     * */
    private static Integer initProperty(Integer source, Integer target){
        if(source == null){
            return target;
        }
        return source;
    }

}
