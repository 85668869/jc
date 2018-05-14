/**
 * Created by jingchun.zhang on 2018/4/26.
 */
package com.jc.concurrent;

import sun.dc.pr.PRError;

import java.util.concurrent.Exchanger;

/**
 * @author jingchun.zhang
 * @version 1.0.0
 * @date 2018/4/26
 */
public class ExchangerEx {

    public static void main(String[] args) {
        ExchangerEx exchangerEx = new ExchangerEx();
        exchangerEx.test();
    }

    private void test(){
        Exchanger exchanger = new Exchanger();
        this.create(exchanger, "A");
        this.create(exchanger, "B");
//        this.create(exchanger, "C");
//        this.create(exchanger, "D");
//        this.create(exchanger, "E");
//        this.create(exchanger, "F");
    }

    private void create(Exchanger exchanger, Object o){
        new Thread(new ExchangerRunnable(exchanger, o)).start();
    }

    class ExchangerRunnable implements Runnable{

        private Exchanger exchanger = null;
        private Object o;

        public ExchangerRunnable(Exchanger exchanger, Object o) {
            this.exchanger = exchanger;
            this.o = o;
        }

        @Override
        public void run() {
            Object preO = this.o;
            try {
                this.o = exchanger.exchange(this.o);
                System.out.println(Thread.currentThread().getId() + "  exchanged  " + preO + " for " + this.o);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
