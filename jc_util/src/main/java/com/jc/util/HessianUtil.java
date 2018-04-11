/**
 * Created by jingchun.zhang on 2018/4/11.
 */
package com.jc.util;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.caucho.hessian.client.HessianProxyFactory;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

/**
 * @author jc
 * @version 1.0.0
 * @date 2018/4/11
 */
public class HessianUtil {

    public static void main(String[] args){

    }

    /**
     *  客户端通过hessian协议访问dubbo接口
     * @param url
     * @param clazz
     * @param methodName 方法名称
     * @param parms  参数列表
     * @param <T>
     * @throws Exception
     */
    public static <T> void request(String url, Class<T> clazz, String methodName, Object ...parms){
        System.out.println("服务："+ url);
        System.out.println("方法：" + methodName);
        System.out.println("参数：" + Arrays.toString(parms));
        HessianProxyFactory factory = new HessianProxyFactory();
        //支持方法重载
        factory.setOverloadEnabled(true);
        try {
            T basic = (T) factory.create(clazz, url);
            //获取Method并调用
            Method[] methods = clazz.getMethods();
            Method method = null;
            for (Method m: methods) {
                if (m.getName().equals(methodName)){
                    method = m;
                    break;
                }
            }
            if (Objects.isNull(method)){
                throw new IllegalArgumentException("class中找不到" + methodName + "方法，请检查方法名称是否正确！");
            }
            Optional ret = Optional.ofNullable(method.invoke(basic, parms));
            System.out.println("返回数据：" + JSONObject.toJSONString(ret.orElse(""), SerializerFeature.WriteMapNullValue));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }





}
