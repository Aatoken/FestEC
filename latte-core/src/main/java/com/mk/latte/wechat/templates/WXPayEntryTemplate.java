package com.mk.latte.wechat.templates;

import android.widget.Toast;

import com.mk.latte.wechat.BaseWXPayEntryActivity;
import com.tencent.mm.opensdk.modelbase.BaseReq;

/**
 * @author lenovo
 * @data 2017/11/12
 */

public class WXPayEntryTemplate extends BaseWXPayEntryActivity {

    @Override
    protected void onPaySuccess() {
        Toast.makeText(this, "支付成功", Toast.LENGTH_SHORT).show();
        finish();
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onPayFail() {
        Toast.makeText(this, "支付失败", Toast.LENGTH_SHORT).show();
        finish();
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onPayCancel() {
        Toast.makeText(this, "支付取消", Toast.LENGTH_SHORT).show();
        finish();
        overridePendingTransition(0, 0);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }


}
