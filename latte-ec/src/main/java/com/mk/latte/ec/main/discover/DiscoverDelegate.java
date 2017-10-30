package com.mk.latte.ec.main.discover;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.mk.latte.delegates.bottom.BottomItemDelegate;
import com.mk.latte.ec.R;

/**
 * @author lenovo
 * @data 2017/10/30
 */

public class DiscoverDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_discover;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
