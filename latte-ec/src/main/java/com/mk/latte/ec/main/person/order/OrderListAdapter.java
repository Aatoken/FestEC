package com.mk.latte.ec.main.person.order;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;

import com.bumptech.glide.Glide;
import com.mk.latte.ec.R;
import com.mk.latte.ui.recycle.MultipleFields;
import com.mk.latte.ui.recycle.MultipleItemEntity;
import com.mk.latte.ui.recycle.MultipleRecyclerAdapter;
import com.mk.latte.ui.recycle.MultipleViewHolder;
import com.mk.latte.util.imgload.GlideUtil;

import java.util.List;

/**
 * @author lenovo
 * @data 2017/11/16
 */

public class OrderListAdapter extends MultipleRecyclerAdapter {


    protected OrderListAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(OrderListItemType.ITEM_ORDER_LIST, R.layout.item_order_list);
    }


    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);

        switch (holder.getItemViewType()) {
            case OrderListItemType.ITEM_ORDER_LIST:
                final AppCompatImageView imageView = holder.getView(R.id.image_order_list);
                final AppCompatTextView title = holder.getView(R.id.tv_order_list_title);
                final AppCompatTextView price = holder.getView(R.id.tv_order_list_price);
                final AppCompatTextView time = holder.getView(R.id.tv_order_list_time);

                final String titleVal = entity.getField(MultipleFields.TITLE);
                final String timeVal = entity.getField(OrderItemFields.TIME);
                final double priceVal = entity.getField(OrderItemFields.PRICE);
                final String imageUrl = entity.getField(MultipleFields.IMAGE_URL);

                Glide.with(mContext)
                        .load(imageUrl)
                        .apply(GlideUtil.RECYCLER_OPTIONS)
                        .into(imageView);

                title.setText(titleVal);
                price.setText("价格:" + String.valueOf(priceVal));
                time.setText("时间:" + timeVal);
                break;
            default:
                break;
        }
    }



}
