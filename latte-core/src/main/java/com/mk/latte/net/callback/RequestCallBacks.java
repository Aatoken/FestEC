package com.mk.latte.net.callback;


import android.os.Handler;

import com.mk.latte.ui.LatteLoader;
import com.mk.latte.ui.LoaderStyle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * callback retrofit2
 * Created by lenovo on 2017/10/17.
 */

public class RequestCallBacks  implements Callback<String> {
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final LoaderStyle LOADER_STYLE;
    /**
     * Handler尽量声明成static，避免内存泄漏
     */
    private static final Handler HANDLER=new Handler();

    public RequestCallBacks(IRequest request,
                            ISuccess success,
                            IFailure failure,
                            IError error,
                            LoaderStyle loaderStyle) {
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.LOADER_STYLE=loaderStyle;
    }


    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if(response.isSuccessful())
        {
            if(call.isExecuted())
            {
                if(SUCCESS!=null)
                {
                    SUCCESS.onSuccess(response.body());
                }
            }
        }else {
            if(ERROR!=null)
            {
                ERROR.onError(response.code(),response.message());
            }
        }

        stopLoading();

    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if(FAILURE!=null)
        {
            FAILURE.onFailure();
        }

        if(REQUEST!=null)
        {
            REQUEST.onRequestEnd();
        }
        stopLoading();
    }


    /**
     * 加载Loader控件,显示时间为1S
     */
    private void stopLoading()
    {
        //LOADER_STYLE
        if(LOADER_STYLE!=null)
        {
            //给加载 设置1S的延迟
            HANDLER.postDelayed(new Runnable() {
                @Override
                public void run() {
                    LatteLoader.stopLoading();
                }
            },1000);
        }
    }


}

















