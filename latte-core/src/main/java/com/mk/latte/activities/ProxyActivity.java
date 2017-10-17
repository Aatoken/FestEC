package com.mk.latte.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import com.mk.latte.R;
import com.mk.latte.delegates.LatteDelegate;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by lenovo on 2017/10/16.
 */

/**
 * 引入的fragmentation 依赖包下的
 */
public abstract class ProxyActivity extends SupportActivity {

    public abstract LatteDelegate setRootDelegate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }

    private void initContainer(@Nullable Bundle savedInstanceState)
    {
        final ContentFrameLayout contatiner=new ContentFrameLayout(this);
        contatiner.setId(R.id.delegate_container);
        setContentView(contatiner);

        if(savedInstanceState==null)
        {
            //SupportActivity 中的方法
            loadRootFragment(R.id.delegate_container,setRootDelegate());
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }
}










