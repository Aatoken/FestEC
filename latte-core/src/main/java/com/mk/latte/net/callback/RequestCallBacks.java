package com.mk.latte.net.callback;


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

    public RequestCallBacks(IRequest request,
                            ISuccess success,
                            IFailure failure,
                            IError error) {
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
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
    }




}

















