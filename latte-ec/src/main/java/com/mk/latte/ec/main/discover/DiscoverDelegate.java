package com.mk.latte.ec.main.discover;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.mk.latte.delegates.bottom.BottomItemDelegate;
import com.mk.latte.delegates.web.WebDelegateImpl;
import com.mk.latte.ec.R;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

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


    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        final WebDelegateImpl delegate=WebDelegateImpl.create("index.html");
        delegate.setTopDelegate(this.getParentDelegate());
        getSupportDelegate().loadRootFragment(R.id.web_discovery_container,delegate);
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }



}


















