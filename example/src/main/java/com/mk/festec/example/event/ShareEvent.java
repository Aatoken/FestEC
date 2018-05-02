package com.mk.festec.example.event;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mk.latte.delegates.web.event.Event;
import com.mob.MobSDK;

import cn.sharesdk.onekeyshare.OnekeyShare;


/**
 * Created by Aatoken on 2018/5/2.
 */

public class ShareEvent extends Event{

    @Override
    public String excute(String params) {

        final JSONObject object = JSON.parseObject(params).getJSONObject("params");
        final String title = object.getString("title");
        final String url = object.getString("url");
        final String imageUrl = object.getString("imageUrl");
        final String text = object.getString("text");

        //3.0之前的初始化
        //ShareSDK.initSDK(getContext());
        //3.1初始化
        MobSDK.init(getContext());
        final OnekeyShare oks = new OnekeyShare();
        oks.disableSSOWhenAuthorize();
        oks.setTitle(title);
        oks.setText(text);
        oks.setImageUrl(imageUrl);
        oks.setUrl(url);
        oks.show(getContext());

        return null;
    }


}
