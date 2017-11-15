package com.mk.latte.ec.pay;

/**
 * @author lenovo
 * @data 2017/11/12
 */

public interface IAlPayResultListener {

    /**
     * 支付成功
     */
    void onPaySuccess();

    /**
     * 支付中
     */
    void onPaying();

    /**
     * 支付失败
     */
    void onPayFail();

    /**
     * 支付取消
     */
    void onPayCancel();

    /**
     * 支付连接失败
     */
    void onPayConnectError();




}
