package com.mk.latte.net;

import android.content.Context;

import com.mk.latte.net.callback.IError;
import com.mk.latte.net.callback.IFailure;
import com.mk.latte.net.callback.IRequest;
import com.mk.latte.net.callback.ISuccess;
import com.mk.latte.ui.LoaderStyle;

import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @author lenovo
 * @date 2017/10/17
 */

public class RestClientBuild {
    /**
     * 使用final 必须给赋值
     */
    private String mUrl = null;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private IRequest mIRequest = null;
    private ISuccess mISuccess = null;
    private IFailure mIFailure = null;
    private IError mIError = null;
    private RequestBody mBody = null;
    private LoaderStyle mLoaderStyle = null;
    private Context mContext = null;


    RestClientBuild() {

    }

    /**
     * 添加 url
     * @param url url
     * @return
     */
    public final RestClientBuild url(String url) {
        this.mUrl = url;
        return this;
    }

    /**
     * 参数集合 WeakHashMap
     * @param params 参数集合
     * @return
     */
    public final RestClientBuild params(WeakHashMap<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }

    /**
     * 重载
     * @param key
     * @param value
     * @return
     */
    public final RestClientBuild Params(String key, Object value) {
        PARAMS.put(key, value);
        return this;
    }


    public final RestClientBuild raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }


    /**
     * 请求
     * @param request
     * @return
     */
    public final RestClientBuild request(IRequest request) {
        this.mIRequest = request;
        return this;
    }

    /**
     * 请求成功
     * @param success 实现成功的回调方法
     * @return
     */
    public final RestClientBuild success(ISuccess success) {
        this.mISuccess = success;
        return this;
    }

    /**
     * 请求失败
     * @param failure 实现失败的回调方法
     * @return
     */
    public final RestClientBuild failure(IFailure failure) {
        this.mIFailure = failure;
        return this;
    }

    /**
     * 请求错误
     * @param error 实现错误的回调方法
     * @return
     */
    public final RestClientBuild error(IError error) {
        this.mIError = error;
        return this;
    }

    /**
     * 主体
     * @param body 主体
     * @return
     */
    public final RestClientBuild body(RequestBody body) {
        this.mBody = body;
        return this;
    }

    /**
     * 显示加载的 loader
     * @param context 上下文
     * @param style dialog的样式
     * @return
     */
    public final RestClientBuild loader(Context context,LoaderStyle style) {
        this.mLoaderStyle = style;
        this.mContext = context;
        return this;
    }

    /**
     * 显示加载的 loader 使用默认的样式
     * @param context 上下文
     * @return
     */
    public final RestClientBuild loader(Context context) {
        this.mLoaderStyle =LoaderStyle.BallClipRotatePulseIndicator;
        this.mContext = context;
        return this;
    }

    public final RestClient build() {
        return new RestClient(mUrl, PARAMS, mIRequest, mISuccess, mIFailure, mIError, mBody,
                mLoaderStyle, mContext);
    }


}























