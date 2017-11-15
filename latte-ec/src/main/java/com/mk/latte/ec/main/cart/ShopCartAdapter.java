package com.mk.latte.ec.main.cart;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.bumptech.glide.Glide;
import com.joanzapata.iconify.widget.IconTextView;
import com.mk.latte.app.Latte;
import com.mk.latte.ec.R;
import com.mk.latte.net.RestClient;
import com.mk.latte.net.callback.ISuccess;
import com.mk.latte.ui.recycle.MultipleFields;
import com.mk.latte.ui.recycle.MultipleItemEntity;
import com.mk.latte.ui.recycle.MultipleRecyclerAdapter;
import com.mk.latte.ui.recycle.MultipleViewHolder;
import com.mk.latte.util.imgload.GlideUtil;

import java.util.List;

/**
 * @author lenovo
 * @data 2017/11/10
 */

public class ShopCartAdapter extends MultipleRecyclerAdapter {

    /**
     * 是否为全部选中 默认全选， 存入到 ShopCartItemFields.IS_SELECTED
     */
    private boolean mIsSelectedAll = false;

    private ICartItemListener mICartItemListener=null;

    private double mTotalPrice=0.00;

    protected ShopCartAdapter(List<MultipleItemEntity> data) {
        super(data);
        //初始化价格
        for (MultipleItemEntity entity :data)
        {
            final double price = entity.getField(ShopCartItemFields.PRICE);
            final int count = entity.getField(ShopCartItemFields.COUNT);
            final double total = price * count;
            mTotalPrice = mTotalPrice + total;
        }
        //添加 购物车 item 布局
        addItemType(ShopCartItemType.SHOP_CART_ITEM, R.layout.item_shop_cart);
    }


    public void setIsSelectedAll(boolean isSelectedAll) {
        this.mIsSelectedAll = isSelectedAll;
    }

    public void setICartItemListener (ICartItemListener listener)
    {
        this.mICartItemListener=listener;
    }

    public double getTotalPrice() {
        return mTotalPrice;
    }

    @Override
    protected void convert(MultipleViewHolder holder, final MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()) {
            case ShopCartItemType.SHOP_CART_ITEM:

                //先取出所有值
                final int id = entity.getField(MultipleFields.ID);
                final String thumb = entity.getField(MultipleFields.IMAGE_URL);
                final String title = entity.getField(ShopCartItemFields.TITLE);
                final String desc = entity.getField(ShopCartItemFields.DESC);
                final int count = entity.getField(ShopCartItemFields.COUNT);
                final double price = entity.getField(ShopCartItemFields.PRICE);
                //取出所以控件
                final AppCompatImageView imgThumb = holder.getView(R.id.image_item_shop_cart);
                final AppCompatTextView tvTitle = holder.getView(R.id.tv_item_shop_cart_title);
                final AppCompatTextView tvDesc = holder.getView(R.id.tv_item_shop_cart_desc);
                final AppCompatTextView tvPrice = holder.getView(R.id.tv_item_shop_cart_price);
                final IconTextView iconMinus = holder.getView(R.id.icon_item_minus);
                final IconTextView iconPlus = holder.getView(R.id.icon_item_plus);
                final AppCompatTextView tvCount = holder.getView(R.id.tv_item_shop_cart_count);
                final IconTextView iconIsSelected = holder.getView(R.id.icon_item_shop_cart);

                //赋值
                tvTitle.setText(title);
                tvDesc.setText(desc);
                tvPrice.setText(String.valueOf(price));
                tvCount.setText(String.valueOf(count));
                Glide.with(mContext)
                        .load(thumb)
                        .apply(GlideUtil.RECYCLER_OPTIONS)
                        .into(imgThumb);

                //是否为全选 存入到 IS_SELECTED
                entity.setField(ShopCartItemFields.IS_SELECTED, mIsSelectedAll);

                //根据数据状态显示左侧小对勾
                final boolean isSelected = entity.getField(ShopCartItemFields.IS_SELECTED);
                showIconSelected(iconIsSelected, isSelected);


                //左侧小对勾点击事件
                iconIsSelected.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final boolean currentSelected = entity.getField(ShopCartItemFields
                                .IS_SELECTED);
                        //刷新 状态
                        showIconSelected(iconIsSelected, !currentSelected);
                        //保存 item
                        entity.setField(ShopCartItemFields.IS_SELECTED, !currentSelected);


                    }
                });

                //添加加减事件
                iconMinus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final int currentCount = entity.getField(ShopCartItemFields.COUNT);
                        if(Integer.parseInt(tvCount.getText().toString())>1)
                        {
                            RestClient.builder()
                                    .url("shop_cart_count.php")
                                    .loader(mContext)
                                    .params("count", currentCount)
                                    .success(new ISuccess() {
                                        @Override
                                        public void onSuccess(String response) {
                                           int count=Integer.parseInt(tvCount.getText().toString());
                                            count--;
                                            tvCount.setText(String.valueOf(count));

                                            if (mICartItemListener != null) {
                                                mTotalPrice = mTotalPrice - price;
                                                final double itemTotal = count * price;
                                                mICartItemListener.onItemClick(itemTotal);
                                            }


                                        }
                                    })
                                    .build()
                                    .post();
                        }
                    }
                });

                iconPlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final int currentCount = entity.getField(ShopCartItemFields.COUNT);

                            RestClient.builder()
                                    .url("shop_cart_count.php")
                                    .loader(mContext)
                                    .params("count", currentCount)
                                    .success(new ISuccess() {
                                        @Override
                                        public void onSuccess(String response) {
                                            int count = Integer.parseInt(tvCount.getText().toString());
                                            count++;
                                            tvCount.setText(String.valueOf(count));

                                            if (mICartItemListener != null) {
                                                mTotalPrice = mTotalPrice + price;
                                                final double itemTotal = count * price;
                                                mICartItemListener.onItemClick(itemTotal);
                                            }

                                        }
                                    })
                                    .build()
                                    .post();

                    }
                });


                break;
            default:
                break;
        }
    }

    /**
     * 选中的 小对勾显示
     *
     * @param iconView
     * @param isSelected
     */
    public void showIconSelected(IconTextView iconView, boolean isSelected) {
        if (isSelected) {
            iconView.setTextColor(ContextCompat.getColor(Latte
                    .getApplicationContext(), R.color.app_main));
        } else {
            iconView.setTextColor(Color.GRAY);
        }
    }


}


















