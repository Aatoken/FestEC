package com.mk.latte.net.rx;

import android.content.Context;

import com.mk.latte.net.HttpMethod;
import com.mk.latte.net.RestCreator;
import com.mk.latte.ui.loader.LatteLoader;
import com.mk.latte.ui.loader.LoaderStyle;

import java.io.File;
import java.util.WeakHashMap;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * 建造者模式
 * Created by lenovo on 2017/10/17.
 */

public class RxRestClient {

    /**
     * 使用final 必须给赋值
     */
    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final RequestBody BODY;
    private final File FILE;
    private final LoaderStyle LOADER_STYLE;
    private final Context CONTEXT;


    /**
     * 初始化赋值
     *
     * @param Url         路径
     * @param Params      参数集合
     * @param body        主体
     * @param file        文件
     * @param loaderStyle 加载控件样式
     * @param context     上下文
     */
    public RxRestClient(String Url,
                        WeakHashMap<String, Object> Params,
                        RequestBody body,
                        File file,
                        LoaderStyle loaderStyle,
                        Context context) {
        this.URL = Url;
        PARAMS.putAll(Params);
        this.BODY = body;
        this.FILE = file;
        this.LOADER_STYLE = loaderStyle;
        this.CONTEXT = context;
    }


    public static RxRestClientBuilder builder() {
        return new RxRestClientBuilder();
    }


    /**
     * 请求
     *
     * @param method
     */
    public Observable<String> request(HttpMethod method) {
        //1.获取服务
        final RxRestService rxService = RestCreator.getRxRestService();
        //2.Call介绍返回
        Observable<String> observable = null;

        //LoaderStyle
        if (LOADER_STYLE != null) {
            LatteLoader.showLoading(CONTEXT, LOADER_STYLE);
        }

        switch (method) {
            case GET:
                observable = rxService.get(URL, PARAMS);
                break;
            case POST:
                observable = rxService.post(URL, PARAMS);
                break;
            case POST_RAW:
                observable = rxService.postRaw(URL, BODY);
                break;
            case PUT:
                observable = rxService.put(URL, PARAMS);
                break;
            case PUT_RAW:
                observable = rxService.putRaw(URL, BODY);
                break;
            case DELETE:
                observable = rxService.delete(URL, PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody = RequestBody.create(MediaType.parse(MultipartBody
                        .FORM.toString()), FILE);
                final MultipartBody.Part body = MultipartBody.Part.createFormData("file", FILE
                        .getName(), requestBody);
                observable = rxService.upload(URL, body);
                break;
            default:
                break;
        }

        return observable;
    }



    public final Observable<String> get() {
        return request(HttpMethod.GET);
    }

    public final Observable<String> post() {
        if (BODY == null) {
            return request(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            return request(HttpMethod.POST_RAW);
        }

    }


    public final Observable<String> put() {
        if (BODY == null) {
            return request(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            return request(HttpMethod.PUT_RAW);
        }
    }


    public final Observable<String> delete() {
        return request(HttpMethod.DELETE);
    }

    public final Observable<String> upload() {
        return request(HttpMethod.UPLOAD);
    }


    public final Observable<ResponseBody> download() {
        return RestCreator.getRxRestService().download(URL, PARAMS);

    }


}























