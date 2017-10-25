package com.mk.festec.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.mk.latte.delegates.LatteDelegate;
import com.mk.latte.net.RestClient;
import com.mk.latte.net.callback.IError;
import com.mk.latte.net.callback.IFailure;
import com.mk.latte.net.callback.ISuccess;

/**
 *
 * @author lenovo
 * @date 2017/10/16
 */

public class ExampleDelegate extends LatteDelegate {


    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
         testRestClient();
    }


    /**
     * 测试网络
     */
    private void testRestClient() {

        RestClient.builder()
                .url("ReadMe.txt")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Log.d("note",response);
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Toast.makeText(getContext(), "onFailure", Toast.LENGTH_SHORT).show();
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Toast.makeText(getContext(), "onError", Toast.LENGTH_SHORT).show();
                    }
                })
                .build()
                .get();
    }


}





















