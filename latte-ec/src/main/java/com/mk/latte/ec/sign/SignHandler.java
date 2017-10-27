package com.mk.latte.ec.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mk.latte.ec.database.DatabaseManager;
import com.mk.latte.ec.database.UserProfile;

import java.util.List;

/**
 * @author lenovo
 * @data 2017/10/27
 */

public class SignHandler {

    /**
     * 注册
     */
    public static void onSingUp(String response) {
        //获取Json对象
        final JSONObject profileJson = JSON.parseObject(response)
                .getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile profile=new UserProfile(userId,name,avatar,gender,address);

        DatabaseManager.getInstance().getDao().insert(profile);
    }


    public static String getUserProfile()
    {
        List<UserProfile> userProfiles = DatabaseManager.getInstance().getDao().loadAll();
        return userProfiles.toString();
    }


}














