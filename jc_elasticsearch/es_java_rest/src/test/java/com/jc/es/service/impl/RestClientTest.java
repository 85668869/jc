/**
 * Created by jingchun.zhang on 2018/12/28.
 */
package com.jc.es.service.impl;

import com.alibaba.fastjson.JSON;
import com.jc.es.BaseTest;
import com.jc.es.config.EsRestClient;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.annotation.Resource;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author jingchun.zhang
 * @version 1.0.0
 * @date 2018/12/28
 */
public class RestClientTest extends BaseTest{

    private Object ret;

    private static String type[] = {"文学", "历史", "计算机", "政治"};
    private static Random random = new Random();

    @Resource
    RestHighLevelClient restHighLevelClient;
    @Resource
    RestClient restClient;

    @After
    public void tearDown() throws Exception {
        System.out.println(JSON.toJSONString(ret));
    }

    @Test
    public void simple() throws Exception {
        ret = restClient.performRequest(new Request("GET", "/"));
        Map<String, Object> map = Collections.singletonMap("type","文学");
        Request request = new Request("GET","/") ;
        request.addParameter("perry","true");
        ret = restClient.performRequest(request);
    }

    @Test
    public void test1() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("id","J"+System.currentTimeMillis());
        map.put("name","书" + System.currentTimeMillis());
        map.put("message","这是一本书");
        map.put("type",type[random.nextInt(3)]);
//        restClient.p
    }


}
