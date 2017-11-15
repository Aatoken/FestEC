package com.mk.latte.wechat.templates;

import com.mk.latte.wechat.BaseWXEntryActivity;
import com.mk.latte.wechat.LatteWeChat;

/**
 * @author lenovo
 * @data 2017/11/12
 */

public class WXEntryTemplate extends BaseWXEntryActivity {

    @Override
    protected void onResume() {
        super.onResume();
        finish();
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onSignInSuccess(String userInfo) {
        LatteWeChat.getInstance().getSignInCallback().onSignInSuccess(userInfo);
    }
}