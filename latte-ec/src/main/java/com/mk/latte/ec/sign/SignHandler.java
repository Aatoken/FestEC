package com.mk.latte.ec.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mk.latte.app.AccountManager;
import com.mk.latte.ec.database.DatabaseManager;
import com.mk.latte.ec.database.UserProfile;

/**
 * @author lenovo
 * @data 2017/10/27
 */

public class SignHandler {

    /**
     * 注册
     */
    public static void onSingUp(String response,ISignListener listener) {
        //获取Json对象
        final JSONObject profileJson = JSON.parseObject(response)
                .getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile profile=new UserProfile(null,name,avatar,gender,address);

        DatabaseManager.getInstance().getDao().insert(profile);

        //已经注册并且登录成功
        AccountManager.setSignState(true);

        //设置回调事件
        listener.onSignUpSuccess();
    }


    /**
     *
     * @param response
     * @param listener
     */
    public static void onSingIn(String response,ISignListener listener) {
        //获取Json对象
        final JSONObject profileJson = JSON.parseObject(response)
                .getJSONObject("data");


        //已经注册并且登录成功
        AccountManager.setSignState(true);

        //设置回调事件
        listener.onSignInSuccess();
    }




}














