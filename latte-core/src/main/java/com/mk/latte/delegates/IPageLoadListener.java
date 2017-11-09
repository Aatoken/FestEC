package com.mk.latte.delegates;

/**
 * @author lenovo
 * @data 2017/11/9
 */

public interface IPageLoadListener {

    /**
     * 开始加载
     */
    void onLoadStart();

    /**
     * 加载完毕
     */
    void onLoadEnd();
}
