package com.mk.latte.ec.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.mk.latte.delegates.LatteDelegate;
import com.mk.latte.ec.R;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * @author lenovo
 * @data 2017/11/8
 */

public class GoodsDetailDelegate extends LatteDelegate {


    public static GoodsDetailDelegate create() {
        return new GoodsDetailDelegate();
    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_goods_detail;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }


    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }
}
