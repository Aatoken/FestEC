package com.mk.latte.net;

import com.mk.latte.net.callback.IError;
import com.mk.latte.net.callback.IFailure;
import com.mk.latte.net.callback.IRequest;
import com.mk.latte.net.callback.ISuccess;
import com.mk.latte.net.callback.RequestCallBacks;

import java.util.WeakHashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * 建造者模式
 * Created by lenovo on 2017/10/17.
 */

public class RestClient {

    //使用final 必须给赋值
    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final RequestBody BODY;


    /**
     * 初始化赋值
     * @param Url 路径
     * @param Params 参数集合
     * @param request 请求
     * @param success 请求成功
     * @param failure 请求失败
     * @param error  请求错误
     * @param body
     */
    public RestClient(String Url,
                      WeakHashMap<String, Object> Params,
                      IRequest request,
                      ISuccess success,
                      IFailure failure,
                      IError error,
                      RequestBody body) {
        this.URL = Url;
        PARAMS.putAll(Params);
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.BODY = body;
    }


    public static RestClientBuild builder() {
        return new RestClientBuild();
    }


    /**
     * 请求
     * @param method
     */
    public void request(HttpMethod method) {
        //1.获取服务
        final RestService service = RestCreator.getRestService();
        //2.Call介绍返回
        Call<String> call = null;
        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }

        switch (method) {
            case TEST:
                call=service.test();
                break;
            case GET:
                call=service.get(URL,PARAMS);
                break;
            case POST:
                call=service.post(URL,PARAMS);
                break;
            case PUT:
                call=service.put(URL,PARAMS);
                break;
            case DELETE:
                call=service.delete(URL,PARAMS);
                break;
            default:
                break;
        }

        if(call!=null)
        {
            call.enqueue(getRequestCallback());
        }

    }


    private Callback<String> getRequestCallback()
    {
        return new RequestCallBacks(REQUEST,SUCCESS,FAILURE,ERROR);
    }

    public final void test()
    {
        request(HttpMethod.TEST);
    }

    public final void get()
    {
       request(HttpMethod.GET);
    }

    public final void post()
    {
        request(HttpMethod.POST);
    }


    public final void put()
    {
        request(HttpMethod.PUT);
    }


    public final void delete()
    {
        request(HttpMethod.DELETE);
    }


}























