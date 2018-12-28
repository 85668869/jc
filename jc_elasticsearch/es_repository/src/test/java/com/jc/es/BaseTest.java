/**
 * Created by jingchun.zhang on 2018/12/28.
 */
package com.jc.es;

import com.alibaba.fastjson.JSON;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author jingchun.zhang
 * @version 1.0.0
 * @date 2018/12/28
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseTest {
    protected Object ret;

    @After
    public void tearDown() throws Exception {
        System.out.println(JSON.toJSONString(ret));
    }
}
