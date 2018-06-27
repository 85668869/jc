package com.jc.mybatis.dao.mapper; /**
 * Created by jingchun.zhang on 2018/6/27.
 */


import com.jc.mybatis.dao.model.Product;

import java.util.List;

/**
 * @author jingchun.zhang
 * @version 1.0.0
 * @date 2018/6/27
 */
public interface ProductMapper {
    /**
     * 查询所有的产品
     * @return
     */
    List<Product> selectProductList();
}
