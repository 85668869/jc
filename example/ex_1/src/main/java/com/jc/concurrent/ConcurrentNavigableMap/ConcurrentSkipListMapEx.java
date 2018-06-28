/**
 * Created by jingchun.zhang on 2018/4/26.
 */
package com.jc.concurrent.ConcurrentNavigableMap;

import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * @author jingchun.zhang
 * @version 1.0.0
 * @date 2018/4/26
 */
public class ConcurrentSkipListMapEx {
    public static void main(String[] args) {
        ConcurrentSkipListMapEx mapEx = new ConcurrentSkipListMapEx();
        mapEx.test();
    }

    private void test(){
        ConcurrentNavigableMap map = new ConcurrentSkipListMap();
        map.put("1", "one");
        map.put("2", "two");
        map.put("3", "three");

        ConcurrentNavigableMap headMap = map.headMap("2");
        System.out.println(headMap);    //{1=one}
        ConcurrentNavigableMap tailMap = map.tailMap("2");
        System.out.println(tailMap);    //{2=two, 3=three}
    }
}
