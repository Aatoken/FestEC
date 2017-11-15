package com.mk.latte.ec.pay;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mk.latte.app.ConfigKeys;
import com.mk.latte.app.Latte;
import com.mk.latte.delegates.LatteDelegate;
import com.mk.latte.ec.R;
import com.mk.latte.net.RestClient;
import com.mk.latte.net.callback.ISuccess;
import com.mk.latte.ui.loader.LatteLoader;
import com.mk.latte.util.log.LatteLogger;
import com.mk.latte.wechat.LatteWeChat;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;

/**
 * @author lenovo
 * @data 2017/11/12
 */

public class FastPay implements View.OnClickListener {

    /**
     * 设置支付回调监听
     */
    private IAlPayResultListener mIAlPayResultListener = null;
    private Activity mActivity = null;
    private AlertDialog mDialog = null;
    private int mOrderID = -1;

    private FastPay(LatteDelegate delegate) {
        this.mActivity = delegate.getProxyActivity();
        this.mDialog = new AlertDialog.Builder(delegate.getContext()).create();
    }


    public static FastPay create(LatteDelegate delegate) {
        return new FastPay(delegate);
    }

    public FastPay setPayResultListener(IAlPayResultListener listener) {
        this.mIAlPayResultListener = listener;
        return this;
    }

    public FastPay setOrderId(int orderId) {
        this.mOrderID = orderId;
        return this;
    }

    /**
     * 开始支付,选择支付方式
     */
    public void beginPayDialog() {
        mDialog.show();
        final Window window = mDialog.getWindow();
        if (window != null) {
            window.setContentView(R.layout.dialog_pay_panel);
            window.setGravity(Gravity.BOTTOM);
            window.setWindowAnimations(R.style.anim_panel_up_from_bottom);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            //设置属性
            final WindowManager.LayoutParams params = window.getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            window.setAttributes(params);
            //点击事件
            window.findViewById(R.id.btn_dialog_pay_alpay).setOnClickListener(this);
            window.findViewById(R.id.btn_dialog_pay_wechat).setOnClickListener(this);
            window.findViewById(R.id.btn_dialog_pay_cancel).setOnClickListener(this);
        }
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_dialog_pay_alpay) {
            alPay(mOrderID);
            mDialog.cancel();
        } else if (id == R.id.btn_dialog_pay_wechat) {
            weChatPay(mOrderID);
            mDialog.cancel();
        } else if (id == R.id.btn_dialog_pay_cancel) {
            mDialog.cancel();
        }
    }

    /**
     * 支付宝支付
     *
     * @param mOrderID
     */
    private final void alPay(int mOrderID) {
        final String singUrl = "";
        //获取前面字符串
        RestClient.builder()
                .url(singUrl)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final String paySign = JSON.parseObject(response).getString("result");
                        //必须是异步的调用客户端支付接口
                        final PayAsyncTask payAsyncTask = new PayAsyncTask(mActivity,
                                mIAlPayResultListener);
                        payAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, paySign);
                    }
                })
                .build()
                .post();
    }

    /**
     * 微信支付
     *
     * @param mOrderID
     */
    private final void weChatPay(int mOrderID) {
        LatteLoader.stopLoading();

        final String weChatPrePayUrl = "";
        LatteLogger.d("WX_PAY", weChatPrePayUrl);

        final IWXAPI iwxapi = LatteWeChat.getInstance().getWXAPI();
        final String appId = Latte.getConfigurationByKey(ConfigKeys.WE_CHAT_APP_ID.name());
        iwxapi.registerApp(appId);

        RestClient.builder()
                .url(weChatPrePayUrl)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {

                        final JSONObject result = JSON.parseObject(response).getJSONObject("result");
                        final String prepayId = result.getString("prepayid");
                        final String partnerId = result.getString("partnerid");
                        final String packageValue = result.getString("package");
                        final String timestamp = result.getString("timestamp");
                        final String nonceStr = result.getString("noncestr");
                        final String paySign = result.getString("sign");

                        final PayReq payReq=new PayReq();
                        payReq.appId = appId;
                        payReq.prepayId = prepayId;
                        payReq.partnerId = partnerId;
                        payReq.packageValue = packageValue;
                        payReq.timeStamp = timestamp;
                        payReq.nonceStr = nonceStr;
                        payReq.sign = paySign;

                        iwxapi.sendReq(payReq);
                    }
                })
                .build()
                .post();
    }


}


















