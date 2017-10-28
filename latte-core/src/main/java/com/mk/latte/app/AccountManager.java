package com.mk.latte.app;

import com.mk.latte.util.storage.LattePreference;

/**
 * @author lenovo
 * @data 2017/10/28
 */

public class AccountManager {

    /**
     * 是否登录了
     */
    private enum SignTag{
        SIGN_TAG
    }

    /**
     * 保存用户登录状态，登录后调用
     * @param state
     */
    public static void setSignState(boolean state){
        LattePreference.setAppFlag(SignTag.SIGN_TAG.name(),state);
    }

    /**
     * 判断是否登录
     * @return
     */
    private static boolean isSignIn()
    {
        return LattePreference.getAppFlag(SignTag.SIGN_TAG.name());
    }

    /**
     * 判断是否登录APP
     * @param checker 回调事件
     */
    public static void checkAccount(IUserChecker checker)
    {
        if(isSignIn())
        {
            checker.onSignIn();
        }else {
            checker.onNotSignIn();
        }
    }


}
