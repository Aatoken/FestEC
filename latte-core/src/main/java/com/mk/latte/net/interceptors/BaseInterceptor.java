package com.mk.latte.net.interceptors;

import java.util.LinkedHashMap;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;

/**
 * @author lenovo
 * @data 2017/10/26
 */

public abstract class BaseInterceptor implements Interceptor {



    /**
     * 获取URL 的参数对
     *
     * @param chain
     * @return 有序的参数对集合
     */
    protected LinkedHashMap<String, String> getUrlParameters(Chain chain) {
        final HttpUrl url = chain.request().url();
        final int size = url.querySize();
        final LinkedHashMap<String, String> params = new LinkedHashMap<>();
        for (int i = 0; i < size; i++) {
            params.put(url.queryParameterName(i), url.queryParameterValue(i));
        }
        return params;
    }


    /**
     * 重载
     *
     * @param chain
     * @param key   key
     * @return 获取Value
     */
    protected String getUrlParameters(Chain chain, String key) {
        final Request request = chain.request();
        return request.url().queryParameter(key);
    }


    /**
     * Body 中获取 参数对
     * @param chain
     * @return
     */
    protected LinkedHashMap<String, String> getBodyParameters(Chain chain) {
        final FormBody formBody = (FormBody) chain.request().body();
        final LinkedHashMap<String, String> params = new LinkedHashMap<>();
        final int size = formBody.size();
        for (int i = 0; i < size; i++) {
            params.put(formBody.name(i),formBody.value(i));
        }
        return params;
    }


    /**
     * 重载
     * @param chain
     * @param key key
     * @return
     */
    protected String getBodyParameters(Chain chain, String key) {
        return getBodyParameters(chain).get(key);
    }




}
