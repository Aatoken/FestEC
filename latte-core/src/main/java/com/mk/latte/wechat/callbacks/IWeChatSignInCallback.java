package com.mk.latte.wechat.callbacks;

/**
 * @author lenovo
 * @data 2017/11/12
 */

public interface IWeChatSignInCallback {

    /**
     * 注册成功
     * @param userInfo
     */
    void onSignInSuccess(String userInfo);

}
