package com.mk.latte.ec.main.person.settings;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.mk.latte.delegates.LatteDelegate;
import com.mk.latte.ec.main.person.list.ListBean;

/**
 * Created by Aatoken on 2018/4/19.
 */

public class SettingsClickListener extends SimpleClickListener {

    private final LatteDelegate DELEGATE;

    public SettingsClickListener(LatteDelegate delegate) {
        DELEGATE = delegate;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        ListBean entity = (ListBean) baseQuickAdapter.getData().get(position);
        final int id = entity.getId();
        switch (id)
        {
            case 1:
                //极光推送无界面
                break;
            case 2:
                //关于界面
                DELEGATE.getSupportDelegate().start(entity.getDelegate());
                break;
            default:
                break;
        }

    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
