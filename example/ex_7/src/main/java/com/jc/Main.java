package com.jc;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.function.IntUnaryOperator;
import java.util.function.LongFunction;
import java.util.function.UnaryOperator;


public class Main {

    public static void main(String[] args) {
        Main main = new Main();
//        main.test1();
        main.test2();
    }

    private void test3(){
//        UnaryOperator
        List lis = new ArrayList();


        Callable<Runnable> c = () -> () -> System.out.println("call");
    }



    Runnable a(){
        return ()-> System.out.println("aa");
    }

    Runnable b(){
        return new Runnable() {
            @Override
            public void run() {

            }
        };
    }

    private void test2(){
        Comparator<Point> comparator = Comparator.comparing(point -> point.x);

        intList.stream()
                .map(i -> new Point(i%3, i *3))
                .mapToDouble(p -> p.distance(0, 0))
                .forEach(d -> System.out.println(d));

        intList.stream()
                .map(i -> new Point(i%3, i *3))
                .sorted(Comparator.comparing(point -> point.distance(0, 0)))
                .forEach(point -> System.out.println("("+point.x+","+point.y+") "));


    }


    private List<Integer> intList = Arrays.asList(1,3,2,4,5);

    private void test1(){
        OptionalDouble optionalDouble = intList.stream()
                .map(i -> new Point(i%3, i*3))
                .mapToDouble(p -> p.distance(0,0))
                .max();

        System.out.println(optionalDouble);
        System.out.println(intList);

        optionalDouble = intList.parallelStream()
                .map(i -> new Point(i%3, i*3))
                .mapToDouble(p -> p.distance(0,0))
                .max();

        System.out.println(optionalDouble);
        System.out.println(intList);
    }

    class Point{
        private Integer x,y;

        public Point(Integer x, Integer y) {
            this.x = x;
            this.y = y;
        }

        public double distance(Integer x, Integer y){
            double _x = Math.abs(this.x - x);
            double _y = Math.abs(this.y - y);
            return Math.sqrt(_x*_x+_y*_y);
        }
    }
}
