/**
 * Created by jingchun.zhang on 2018/11/2.
 */
package com.jc.mybatis.service;

import com.jc.mybatis.dao.mapper.ProductMapper;
import com.jc.mybatis.dao.model.Product;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * @author jingchun.zhang
 * @version 1.0.0
 * @date 2018/11/2
 */
public class BatchTest {

    SqlSessionFactory sqlSessionFactory = null;

    public BatchTest(){
        init();
    }

    private void init(){
        if (sqlSessionFactory != null){
            return;
        }
        String resource = "mybatis.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    public void testSimple(){
        long start = System.currentTimeMillis();
        Product product;
        SqlSession sqlSession = sqlSessionFactory.openSession(false);//批处理方式 手动提交事务
        ProductMapper mapper = sqlSession.getMapper(ProductMapper.class);
        for (int i = 0; i < 5000; i++) {
            product = new Product();
            product.setId(i);
            product.setProductName("name"+i);
            product.setProductContent("content"+i);
            product.setPrice("15"+i);
            product.setSort(i);
            product.setFalseSales(i);
            product.setCategory_id(i);
            mapper.insert(product);
        }
        sqlSession.commit();
        long end = System.currentTimeMillis();
        System.out.println("---------------" + (start - end) + "---------------");
    }

    public void testBatch(){
        long start = System.currentTimeMillis();
        Product product;
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH,false);//批处理方式 手动提交事务
        ProductMapper mapper = sqlSession.getMapper(ProductMapper.class);
        for (int i = 0; i < 5000; i++) {
            product = new Product();
            product.setId(i);
            product.setProductName("name"+i);
            product.setProductContent("content"+i);
            product.setPrice("15"+i);
            product.setSort(i);
            product.setFalseSales(i);
            product.setCategory_id(i);
            mapper.insert(product);
        }
        sqlSession.commit();
        long end = System.currentTimeMillis();
        System.out.println("---------------" + (start - end) + "---------------");
    }

    public void testSqlBatch(){
        long start = System.currentTimeMillis();
        List<Product> products = new ArrayList<>();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);//批处理方式 手动提交事务
        ProductMapper mapper = sqlSession.getMapper(ProductMapper.class);
        for (int i = 0; i < 5000; i++) {
            Product product = new Product();
            product.setId(i);
            product.setProductName("name"+i);
            product.setProductContent("content"+i);
            product.setPrice("15"+i);
            product.setSort(i);
            product.setFalseSales(i);
            product.setCategory_id(i);
            products.add(product);
        }
        mapper.batchInsert(products);
        long end = System.currentTimeMillis();
        System.out.println("---------------" + (start - end) + "---------------");
    }

    public static void main(String[] args) {
        BatchTest batchTest = new BatchTest();
//        batchTest.testSimple();
//        10764
//        batchTest.testBatch();
        //5951
        batchTest.testSqlBatch();
        //1211
    }

}
