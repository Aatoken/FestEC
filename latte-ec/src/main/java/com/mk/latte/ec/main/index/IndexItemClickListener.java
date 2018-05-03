package com.mk.latte.ec.main.index;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.mk.latte.delegates.LatteDelegate;
import com.mk.latte.ec.detail.GoodsDetailDelegate;
import com.mk.latte.ui.recycle.MultipleFields;
import com.mk.latte.ui.recycle.MultipleItemEntity;

/**
 * @author lenovo
 * @data 2017/11/8
 */

public class IndexItemClickListener extends SimpleClickListener {

    private final LatteDelegate DELEGATE;

    private IndexItemClickListener(LatteDelegate delegate) {
        this.DELEGATE = delegate;
    }

    /**
     * 简单工厂
     *
     * @param delegate delegate
     * @return
     */
    public static SimpleClickListener create(LatteDelegate delegate) {
        return new IndexItemClickListener(delegate);
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final MultipleItemEntity entity = (MultipleItemEntity) baseQuickAdapter
                .getData().get(position);
        final int goodsId = entity.getField(MultipleFields.ID);
        final GoodsDetailDelegate detailDelegate = GoodsDetailDelegate.create(goodsId);
        DELEGATE.getSupportDelegate().start(detailDelegate);
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
