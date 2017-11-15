package com.mk.latte.ec.main.cart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ViewStubCompat;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.joanzapata.iconify.widget.IconTextView;
import com.mk.latte.delegates.bottom.BottomItemDelegate;
import com.mk.latte.ec.R;
import com.mk.latte.ec.R2;
import com.mk.latte.ec.pay.FastPay;
import com.mk.latte.ec.pay.IAlPayResultListener;
import com.mk.latte.net.RestClient;
import com.mk.latte.net.callback.ISuccess;
import com.mk.latte.ui.recycle.MultipleItemEntity;
import com.mk.latte.util.toast.ToastUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author lenovo
 * @data 2017/10/30
 */

public class ShopCartDelegate extends BottomItemDelegate implements
        ISuccess, ICartItemListener,IAlPayResultListener {


    private ShopCartAdapter mAdapter = null;
    /**
     * 购物车数量标记
     */
    private int mCurrentCount = 0;
    private int mTotalCount = 0;
    private double mTotalPrice = 0.00;

    @BindView(R2.id.rv_shop_cart)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.icon_shop_cart_select_all)
    IconTextView mIconSelectAll = null;
    @BindView(R2.id.stub_no_item)
    ViewStubCompat mStubNoItem = null;
    @BindView(R2.id.tv_shop_cart_total_price)
    AppCompatTextView mTvTotalPrice = null;

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

        checkItemCount();
    }


    @OnClick(R2.id.tv_top_shop_cart_clear)
    void onClickClear() {
        mAdapter.getData().clear();
        mAdapter.notifyDataSetChanged();

        checkItemCount();
    }


    @OnClick(R2.id.tv_shop_cart_pay)
    void onClickPay() {
        FastPay.create(this).beginPayDialog();
    }


    /**
     * 创建订单，注意，和支付是没有关系的
     */
    private void createOrder() {
        final String orderUrl = "你的生成订单的API";
        final WeakHashMap<String, Object> orderParams = new WeakHashMap<>();
        orderParams.put("userid", "");
        orderParams.put("amount", 0.01);
        orderParams.put("comment", "测试支付");
        orderParams.put("type", 1);
        orderParams.put("ordertype", 0);
        orderParams.put("isanonymous", true);
        orderParams.put("followeduser", 0);


        RestClient.builder()
                .url(orderUrl)
                .loader(getContext())
                .params(orderParams)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final int orderId= JSON.parseObject(response).getInteger("result");
                        //进行具体的支付
                        FastPay.create(ShopCartDelegate.this)
                                .setOrderId(orderId)
                                .setPayResultListener(ShopCartDelegate.this)
                                //弹出支付框选择支付
                                .beginPayDialog();


                    }
                })
                .build()
                .post();

    }


    /**
     * 检测购物车现实的个数
     */
    private void checkItemCount() {
        final int count = mAdapter.getItemCount();
        if (count == 0) {
            final AppCompatTextView tvToBuy = (AppCompatTextView) mStubNoItem.inflate()
                    .findViewById(R.id
                    .tv_stub_to_buy);

            tvToBuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtils.showToast(getContext(), "你该购物了");
                }
            });

            mRecyclerView.setVisibility(View.GONE);
        } else {
            mRecyclerView.setVisibility(View.VISIBLE);
        }
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
        //初始化 是否为全选
        mIconSelectAll.setTag(false);
        mAdapter.showIconSelected(mIconSelectAll, false);
        mAdapter.setICartItemListener(this);
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);

        mTotalPrice = mAdapter.getTotalPrice();
        mTvTotalPrice.setText(String.valueOf(mTotalPrice));

        checkItemCount();
    }


    @Override
    public void onItemClick(double itemTotalPrice) {
        final double price = mAdapter.getTotalPrice();
        mTvTotalPrice.setText(String.valueOf(price));
    }


    @Override
    public void onPaySuccess() {

    }

    @Override
    public void onPaying() {

    }

    @Override
    public void onPayFail() {

    }

    @Override
    public void onPayCancel() {

    }

    @Override
    public void onPayConnectError() {

    }
}



























