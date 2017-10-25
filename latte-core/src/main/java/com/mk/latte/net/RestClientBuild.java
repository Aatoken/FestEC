package com.mk.latte.net;

import com.mk.latte.net.callback.IError;
import com.mk.latte.net.callback.IFailure;
import com.mk.latte.net.callback.IRequest;
import com.mk.latte.net.callback.ISuccess;

import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by lenovo on 2017/10/17.
 */

public class RestClientBuild {
    //使用final 必须给赋值
    private String mUrl;
    private static final WeakHashMap<String, Object> PARAMS=RestCreator.getParams();
    private IRequest mIRequest;
    private ISuccess mISuccess;
    private IFailure mIFailure;
    private IError mIError;
    private RequestBody mBody;

    RestClientBuild() {

    }


    public RestClientBuild url(String url) {
        this.mUrl = url;
        return this;
    }

    public RestClientBuild params(WeakHashMap<String, Object> params) {
       PARAMS.putAll(params);
        return this;
    }

    /**
     * 重载
     *
     * @param key
     * @param value
     * @return
     */
    public RestClientBuild Params(String key, Object value) {
        PARAMS.put(key, value);
        return this;
    }


    public RestClientBuild raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }


    public RestClientBuild request(IRequest request) {
        this.mIRequest = request;
        return this;
    }

    public RestClientBuild success(ISuccess success) {
        this.mISuccess = success;
        return this;
    }

    public RestClientBuild failure(IFailure failure) {
        this.mIFailure = failure;
        return this;
    }

    public RestClientBuild error(IError error) {
        this.mIError = error;
        return this;
    }

    public RestClientBuild body(RequestBody body) {
        this.mBody = body;
        return this;
    }




    public final RestClient build()
    {
        return new RestClient(mUrl,PARAMS,mIRequest,mISuccess,mIFailure,mIError,mBody);
    }




}























