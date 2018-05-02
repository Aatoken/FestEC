package com.mk.latte.ui.scanner;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.mk.latte.delegates.LatteDelegate;
import com.mk.latte.util.callback.CallBackManager;
import com.mk.latte.util.callback.CallBackType;
import com.mk.latte.util.callback.IGlobalCallBack;

import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

/**
 * Created by Aatoken on 2018/5/2.
 */

public class ScannerDelegate  extends LatteDelegate implements ZBarScannerView.ResultHandler{

    private ScanView mScanView=null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(mScanView==null)
        {
            mScanView=new ScanView(getContext());
        }
        //设置对焦
        mScanView.setAutoFocus(true);
        //设置回调监听
        mScanView.setResultHandler(this);
    }


    @Override
    public Object setLayout() {
        return mScanView;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }

    @Override
    public void onResume() {
        super.onResume();
        if(mScanView!=null)
        {
            mScanView.startCamera();
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        if(mScanView!=null)
        {
            mScanView.stopCameraPreview();
            mScanView.stopCamera();
        }
    }

    @Override
    public void handleResult(Result result) {
        final IGlobalCallBack<String> callBack= CallBackManager
                .getInstance()
                .getCallBack(CallBackType.ON_SCAN);
        if(callBack!=null)
        {
            callBack.executeCallBack(result.getContents());
        }
        getSupportDelegate().pop();
    }



}
