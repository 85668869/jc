/**
 * Created by jingchun.zhang on 2018/7/24.
 */
package com.jc.lamba;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author jingchun.zhang
 * @version 1.0.0
 * @date 2018/7/24
 */
public class ConvertListAndMap {

    public static void main(String[] args) {
        ConvertListAndMap convertListAndMap = new ConvertListAndMap();
//        convertListAndMap.listToMap2();
//        convertListAndMap.listToList1();
        convertListAndMap.test1();
    }

    /**
     *
     * List<People> ->  Map<name,People>
     *输出：
     *{
     *    张四={
     *   "name": "张四",
     *   "age": 10,
     *   "phone": "13675860001"
     *   },
     *   张三={
     *   "name": "张三",
     *   "age": 10,
     *   "phone": "13675860000"
     *   },
     *   张五={
     *   "name": "张五",
     *   "age": 15,
     *   "phone": "13675860002"
     *   }
     *}
     */
    private void listToMap1(){
        List<People> peopleList = buildList();

        Map<String, People> peopleMap = peopleList.stream().collect(Collectors.toMap(People::getName, Function.identity()));
        System.out.println(peopleMap);
    }

    /**
     * 按年龄分组后，转map
     * List<People> -> Map<age, List<People>>
     *
     *     {
     *        10=[{
     *           "name": "张三",
     *           "age": 10,
     *           "phone": "13675860000"
     *          },
     *          {
     *           "name": "张四",
     *           "age": 10,
     *           "phone": "13675860001"
     *       }],
     *       15=[{
     *           "name": "张五",
     *           "age": 15,
     *           "phone": "13675860002"
     *       }]
     *      }
     */
    private void listToMap2(){
        List<People> peopleList = buildList();

        Map<Integer, List<People>> peopleMap = peopleList.stream().collect(Collectors.groupingBy(People::getAge));
        System.out.println(peopleMap);
    }


    /**
     * 收集list中某一变量，抽取成list
     *
     * List<People> -> List<String>
     * 收集name:
     *    输出：[张三, 张四, 张五]
     *收集age：
     *    输出: [10, 10, 15]
     */
    private void listToList1(){
        List<People> peopleList = buildList();

        List<Integer> nameList = peopleList.stream().map(People::getAge).collect(Collectors.toList());
        System.out.println(nameList);
    }

    /**
     * 获取最小年龄，且不为0
     */
    private void test1(){
        List<People> specList = new ArrayList<>();
        People people = new People();
        people.setAge(5);
        specList.add(people);

        People people1 = new People();
        people1.setAge(0);
        specList.add(people1);

        People people2 = new People();
        people2.setAge(15);
        specList.add(people2);

        Optional<Integer> price = specList.stream()
                .filter(p -> p.getAge() > 0)
                .map(People::getAge)
                .min(Comparator.comparing(Function.identity()));
        System.out.println(price.orElse(0));
    }


    private List<People> buildList(){
        List<People> peopleList = new ArrayList<>();
        People people = new People();
        people.setName("张三");
        people.setAge(10);
        people.setPhone("13675860000");
        peopleList.add(people);

        People people1 = new People();
        people1.setName("张四");
        people1.setAge(10);
        people1.setPhone("13675860001");
        peopleList.add(people1);

        People people2 = new People();
        people2.setName("张五");
        people2.setAge(15);
        people2.setPhone("13675860002");
        peopleList.add(people2);

        return peopleList;
    }






}
