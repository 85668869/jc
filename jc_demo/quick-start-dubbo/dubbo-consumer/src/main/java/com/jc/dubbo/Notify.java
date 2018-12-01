package com.jc.dubbo;

/**
 * Created by jingchun.zhang on 2018/10/18.
 */
public interface Notify {

    void oninvoke(String name);

    void onreturn(String result, String name);

    void onthrow(Throwable ex, String name);
}
