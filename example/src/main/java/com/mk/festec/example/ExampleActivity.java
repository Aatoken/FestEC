package com.mk.festec.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;

import com.mk.latte.activities.ProxyActivity;
import com.mk.latte.delegates.LatteDelegate;
import com.mk.latte.ec.sign.SignUpDelegate;

/**
 * 单activity 的跟Activity
 */
public class ExampleActivity extends ProxyActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //取消掉 actionBar
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null)
        {
            actionBar.hide();
        }
    }

    @Override
    public LatteDelegate setRootDelegate() {
        return new SignUpDelegate();
    }




}
