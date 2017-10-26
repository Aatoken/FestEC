package com.mk.latte.net;

import android.content.Context;

import com.mk.latte.net.callback.IError;
import com.mk.latte.net.callback.IFailure;
import com.mk.latte.net.callback.IRequest;
import com.mk.latte.net.callback.ISuccess;
import com.mk.latte.ui.LoaderStyle;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @author lenovo
 * @date 2017/10/17
 */

public class RestClientBuilder {
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
    private File mFile = null;
    private String mDownloadDir = null;
    private String mExtension = null;
    private String mName = null;
    private LoaderStyle mLoaderStyle = null;
    private Context mContext = null;


    RestClientBuilder() {

    }

    /**
     * 添加 url
     *
     * @param url url
     * @return
     */
    public final RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    /**
     * 参数集合 WeakHashMap
     *
     * @param params 参数集合
     * @return
     */
    public final RestClientBuilder params(WeakHashMap<String, Object> params) {
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
    public final RestClientBuilder Params(String key, Object value) {
        PARAMS.put(key, value);
        return this;
    }


    public final RestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }


    /**
     * 请求
     *
     * @param request
     * @return
     */
    public final RestClientBuilder request(IRequest request) {
        this.mIRequest = request;
        return this;
    }

    /**
     * 请求成功
     *
     * @param success 实现成功的回调方法
     * @return
     */
    public final RestClientBuilder success(ISuccess success) {
        this.mISuccess = success;
        return this;
    }

    /**
     * 请求失败
     *
     * @param failure 实现失败的回调方法
     * @return
     */
    public final RestClientBuilder failure(IFailure failure) {
        this.mIFailure = failure;
        return this;
    }

    /**
     * 请求错误
     *
     * @param error 实现错误的回调方法
     * @return
     */
    public final RestClientBuilder error(IError error) {
        this.mIError = error;
        return this;
    }

    /**
     * 主体
     *
     * @param body 主体
     * @return
     */
    public final RestClientBuilder body(RequestBody body) {
        this.mBody = body;
        return this;
    }

    /**
     * 显示加载的 loader
     *
     * @param context 上下文
     * @param style   dialog的样式
     * @return
     */
    public final RestClientBuilder loader(Context context, LoaderStyle style) {
        this.mLoaderStyle = style;
        this.mContext = context;
        return this;
    }

    /**
     * 显示加载的 loader 使用默认的样式
     *
     * @param context 上下文
     * @return
     */
    public final RestClientBuilder loader(Context context) {
        this.mLoaderStyle = LoaderStyle.BallTrianglePathIndicator;
        this.mContext = context;
        return this;
    }

    /**
     * 下载文件
     * @param file 文件
     * @return
     */
    public final RestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    /**
     * 下载文件
     * @param filePath 文件的路径
     * @return
     */
    public final RestClientBuilder file(String filePath) {
        this.mFile = new File(filePath);
        return this;
    }

    /**
     * 文件目录
     * @param dir 目录
     * @return
     */
    public final RestClientBuilder dir(String dir) {
        this.mDownloadDir = dir;
        return this;
    }

    /**
     * 文件后缀名
     * @param extension 文件后缀名
     * @return
     */
    public final RestClientBuilder extension(String extension) {
        this.mExtension = extension;
        return this;
    }

    /**
     * 文件名称
     * @param name 文件名称
     * @return
     */
    public final RestClientBuilder name(String name) {
        this.mName = name;
        return this;
    }


    public final RestClient build() {
        return new RestClient(mUrl, PARAMS,
                mIRequest, mISuccess, mIFailure, mIError,
                mBody, mFile,
                mDownloadDir,mExtension,mName,
                mLoaderStyle, mContext);
    }


}























