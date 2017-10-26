package com.mk.latte.net;

import android.content.Context;

import com.mk.latte.net.callback.IError;
import com.mk.latte.net.callback.IFailure;
import com.mk.latte.net.callback.IRequest;
import com.mk.latte.net.callback.ISuccess;
import com.mk.latte.net.callback.RequestCallBacks;
import com.mk.latte.net.download.DownLoadHandler;
import com.mk.latte.ui.LatteLoader;
import com.mk.latte.ui.LoaderStyle;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * 建造者模式
 * Created by lenovo on 2017/10/17.
 */

public class RestClient {

    /**
     * 使用final 必须给赋值
     */
    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final RequestBody BODY;
    private final File FILE;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;
    private final LoaderStyle LOADER_STYLE;
    private final Context CONTEXT;


    /**
     * 初始化赋值
     *
     * @param Url         路径
     * @param Params      参数集合
     * @param request     请求
     * @param success     请求成功
     * @param failure     请求失败
     * @param error       请求错误
     * @param body        主体
     * @param file        文件
     * @param downloadDir 下载目录
     * @param extension   后缀名
     * @param name        下载的文件名
     * @param loaderStyle 加载控件样式
     * @param context     上下文
     */
    public RestClient(String Url,
                      WeakHashMap<String, Object> Params,
                      IRequest request,
                      ISuccess success,
                      IFailure failure,
                      IError error,
                      RequestBody body,
                      File file,
                      String downloadDir,
                      String extension,
                      String name,
                      LoaderStyle loaderStyle,
                      Context context) {
        this.URL = Url;
        PARAMS.putAll(Params);
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.BODY = body;
        this.FILE = file;
        this.DOWNLOAD_DIR = downloadDir;
        this.EXTENSION = extension;
        this.NAME = name;
        this.LOADER_STYLE = loaderStyle;
        this.CONTEXT = context;
    }


    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }


    /**
     * 请求
     *
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

        //LoaderStyle
        if (LOADER_STYLE != null) {
            LatteLoader.showLoading(CONTEXT, LOADER_STYLE);
        }

        switch (method) {
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case POST_RAW:
                call = service.postRaw(URL, BODY);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case PUT_RAW:
                call = service.putRaw(URL, BODY);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody = RequestBody.create(MediaType.parse(MultipartBody
                        .FORM.toString()), FILE);
                final MultipartBody.Part body = MultipartBody.Part.createFormData("file", FILE
                        .getName(), requestBody);
                call = RestCreator.getRestService().upload(URL, body);
                break;
            default:
                break;
        }

        if (call != null) {
            call.enqueue(getRequestCallback());
        }

    }


    private Callback<String> getRequestCallback() {
        return new RequestCallBacks(
                REQUEST,
                SUCCESS,
                FAILURE,
                ERROR,
                LOADER_STYLE);
    }


    public final void get() {
        request(HttpMethod.GET);
    }

    public final void post() {
        if (BODY == null) {
            request(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.POST_RAW);
        }

    }


    public final void put() {
        if (BODY == null) {
            request(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.PUT_RAW);
        }
    }


    public final void delete() {
        request(HttpMethod.DELETE);
    }

    public final void upload() {
        request(HttpMethod.UPLOAD);
    }


    public final void download() {
        new DownLoadHandler(URL, PARAMS,
                REQUEST, SUCCESS, FAILURE, ERROR,
                DOWNLOAD_DIR, EXTENSION, NAME).handlerDownLoad();

    }


}























