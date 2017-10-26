package com.mk.latte.net.download;

import android.os.AsyncTask;

import com.mk.latte.net.RestCreator;
import com.mk.latte.net.callback.IError;
import com.mk.latte.net.callback.IFailure;
import com.mk.latte.net.callback.IRequest;
import com.mk.latte.net.callback.ISuccess;

import java.util.WeakHashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * @author lenovo
 * @data 2017/10/25
 */

public class DownLoadHandler {

    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;

    public DownLoadHandler(String Url,
                           WeakHashMap<String, Object> Params,
                           IRequest request,
                           ISuccess success,
                           IFailure failure,
                           IError error,
                           String downloadDir,
                           String extension,
                           String name) {
        this.URL = Url;
        PARAMS.putAll(Params);
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.DOWNLOAD_DIR = downloadDir;
        this.EXTENSION = extension;
        this.NAME = name;

    }

    public final void handlerDownLoad() {
        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }

        RestCreator.getRestService().download(URL, PARAMS)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody>
                            response) {

                        if(response.isSuccessful())
                        {
                            final ResponseBody responseBody=response.body();
                            final SaveFileTask task=new SaveFileTask(REQUEST,SUCCESS);
                            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,
                                    DOWNLOAD_DIR,EXTENSION,NAME,responseBody);

                            //这里一定要注意判断，否则文件下载不全
                            if(task.isCancelled())
                            {
                                if(REQUEST!=null)
                                {
                                    REQUEST.onRequestEnd();
                                }
                            }

                        }else {

                            if(ERROR!=null)
                            {
                                ERROR.onError(response.code(),response.message());
                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        if(FAILURE!=null)
                        {
                            FAILURE.onFailure();
                        }
                    }
                });


    }




}