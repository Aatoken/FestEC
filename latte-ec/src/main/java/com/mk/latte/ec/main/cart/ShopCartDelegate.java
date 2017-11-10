package com.mk.latte.ec.main.cart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.joanzapata.iconify.widget.IconTextView;
import com.mk.latte.delegates.bottom.BottomItemDelegate;
import com.mk.latte.ec.R;
import com.mk.latte.ec.R2;
import com.mk.latte.net.RestClient;
import com.mk.latte.net.callback.ISuccess;
import com.mk.latte.ui.recycle.MultipleItemEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author lenovo
 * @data 2017/10/30
 */

public class ShopCartDelegate extends BottomItemDelegate implements ISuccess {


    private ShopCartAdapter mAdapter = null;
    /**
     * 购物车数量标记
     */
    private int mCurrentCount = 0;
    private int mTotalCount = 0;

    @BindView(R2.id.rv_shop_cart)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.icon_shop_cart_select_all)
    IconTextView mIconSelectAll = null;

    @OnClick(R2.id.icon_shop_cart_select_all)
    void onClickSelectAll() {
        //isSlectedAll 与 mAdapter中的 mIsSelectedAll 要一致
        final boolean isSlectedAll = (boolean) mIconSelectAll.getTag();
        //更新点击图标的颜色
        mAdapter.showIconSelected(mIconSelectAll, !isSlectedAll);
        //更新 adapter 的 item 被选中
        mAdapter.setIsSelectedAll(!isSlectedAll);
        //更新 recycleView的数据
        mAdapter.notifyItemRangeChanged(0, mAdapter.getItemCount());
        //保存到Tag 上
        mIconSelectAll.setTag(!isSlectedAll);
    }

    @OnClick(R2.id.tv_top_shop_cart_remove_selected)
    void onClickRemoveSelectedItem() {
        //获取所有的数据
        final List<MultipleItemEntity> data = mAdapter.getData();
        //要删除的数据
        final List<MultipleItemEntity> deleteEntities = new ArrayList<>();
        for (MultipleItemEntity entity : data) {
            final boolean isSelected = entity.getField(ShopCartItemFields.IS_SELECTED);
            if (isSelected) {
                deleteEntities.add(entity);
            }
        }
        for (MultipleItemEntity entity : deleteEntities) {
            int removePosition;
            final int entityPosition = entity.getField(ShopCartItemFields.POSITION);
            if (entityPosition > mCurrentCount - 1) {
                removePosition = entityPosition - (mTotalCount - mCurrentCount);
            } else {
                removePosition = entityPosition;
            }
            if (removePosition <= mAdapter.getItemCount()) {
                mAdapter.remove(removePosition);
                mCurrentCount = mAdapter.getItemCount();
                //更新数据
                mAdapter.notifyItemRangeChanged(removePosition, mCurrentCount);
            }
        }
    }


    @OnClick(R2.id.tv_top_shop_cart_clear)
    void onClickClear() {
        mAdapter.getData().clear();
        mAdapter.notifyDataSetChanged();
    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_shop_cart;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {


    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

        RestClient.builder()
                .url("shop_cart.php")
                .loader(getContext())
                .success(this)
                .build()
                .get();
    }


    @Override
    public void onSuccess(String response) {

        final ArrayList<MultipleItemEntity> data = new ShopCartDataConvert().setJsonData
                (response).convert();

        mAdapter = new ShopCartAdapter(data);

        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);

        //初始化 是否为全选
        mIconSelectAll.setTag(true);
        mAdapter.showIconSelected(mIconSelectAll, true);
        mCurrentCount = mAdapter.getItemCount();
        mTotalCount = mAdapter.getItemCount();

    }


}



























