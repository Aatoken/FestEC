package com.mk.latte.net;

import com.mk.latte.app.ConfigKeys;
import com.mk.latte.app.Latte;

import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by lenovo on 2017/10/17.
 */

/**
 * 1. 构建  Retrofit 实例
 * 2. 获取网络请求的接口实例
 */
public final class RestCreator {


    /**
     * 静态内部类
     */
    private static final class ParamsHolder {
        public static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    }

    /**
     * 获取 PARAMS
     *
     * @return
     */
    public static WeakHashMap<String, Object> getParams() {
        return ParamsHolder.PARAMS;
    }


    /**
     * 1. 构建  Retrofit 实例
     */
    private static final class RetrofitHolder {

        private static final String BASE_URI = Latte.getConfigurationByKey(ConfigKeys.API_HOST
                .name());
        /**
         * Retrofit的创建
         */
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URI)
                .client(OKHttpHolder.OK_HTTP_CLIENT)
                //String解析器
                .addConverterFactory(ScalarsConverterFactory.create())
                //Json解析器
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    /**
     * OkHttp
     */
    private static final class OKHttpHolder {
        private static final int TIME_OUT = 60;
        private static final OkHttpClient.Builder BUILDER = new OkHttpClient.Builder();
        private static final ArrayList<Interceptor> INTERCEPTORS = Latte.getConfigurationByKey
                (ConfigKeys.INTERCEPTOR.name());

        private static OkHttpClient.Builder addInterceptor() {
            if (INTERCEPTORS != null && INTERCEPTORS.isEmpty()) {
                for (Interceptor interceptor : INTERCEPTORS) {
                    BUILDER.addInterceptor(interceptor);
                }
            }
            return BUILDER;
        }

        private static final OkHttpClient OK_HTTP_CLIENT = addInterceptor()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();

    }


    /**
     * 2. 获取网络请求的接口实例
     *
     * @return
     */
    public static RestService getRestService() {
        return RestServiceHolder.REST_SERVICE;
    }

    /**
     * 内部类 创建 网络请求接口 的实例
     */
    private static final class RestServiceHolder {
        private static final RestService REST_SERVICE = RetrofitHolder.RETROFIT_CLIENT
                .create(RestService.class);
    }


}





















