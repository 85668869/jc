/**
 * Created by jingchun.zhang on 2018/10/18.
 */
package com.jc.dubbo;

/**
 * @author jingchun.zhang
 * @version 1.0.0
 * @date 2018/10/18
 */
public class NotifyImpl implements Notify{

    @Override
    public void oninvoke(String name) {
        System.out.println("do method oninvoke, param : " + name);
    }

    @Override
    public void onreturn(String result, String name) {
        System.out.println("do method onreturn, result : " + result + ",  param : " + name);
    }

    @Override
    public void onthrow(Throwable ex, String name) {
        System.out.println("do method onthrow, ex : " + ex + ",  param : " + name);
    }
}
