package com.mk.latte.app;

/**
 * @author lenovo
 * @data 2017/10/28
 */

public interface IUserChecker {
    /**
     * 已登录
     */
    void onSignIn();

    /**
     * 未登录
     */
    void onNotSignIn();
}
