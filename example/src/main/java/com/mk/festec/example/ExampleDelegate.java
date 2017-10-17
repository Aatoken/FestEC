package com.mk.festec.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.mk.latte.delegates.LatteDelegate;

/**
 * Created by lenovo on 2017/10/16.
 */

public class ExampleDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
