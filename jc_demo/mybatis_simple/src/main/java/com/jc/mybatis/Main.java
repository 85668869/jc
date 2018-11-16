/**
 * Created by jingchun.zhang on 2018/6/27.
 */
package com.jc.mybatis;

import com.jc.mybatis.dao.mapper.ProductMapper;
import com.jc.mybatis.dao.model.Product;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author jingchun.zhang
 * @version 1.0.0
 * @date 2018/6/27
 */
public class Main {

    static SqlSession sqlSession = null;

    static {
        String resource = "mybatis.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSession = sqlSessionFactory.openSession();
    }

    public static void main(String[] args) throws IOException {
        try {
            ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);

            int i = 1;
            Product product1 = new Product();
            product1.setId(i);
            product1.setProductName("name"+i);
            product1.setProductContent("content"+i);
            product1.setPrice("15"+i);
            product1.setSort(i);
            product1.setFalseSales(i);
            product1.setCategory_id(i);
            int a = productMapper.insert(product1);
            System.out.println("..." + a);

//            if (true)return;
            int count = productMapper.count();
            System.out.println("count:"+count);
            List<Product> productList = productMapper.selectProductList();
            for (Product product : productList) {
                System.out.printf(product.toString());
            }
        } finally {
            sqlSession.close();
        }
    }

}
