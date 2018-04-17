package com.mk.latte.ec.main.person.order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mk.latte.ui.recycle.DataConverter;
import com.mk.latte.ui.recycle.MultipleFields;
import com.mk.latte.ui.recycle.MultipleItemEntity;

import java.util.ArrayList;

/**
 * @author lenovo
 * @data 2017/11/16
 */

public class OrderListDataConverter extends DataConverter {


    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final JSONArray array = JSON.parseObject(getJsonData()).getJSONArray("data");
        final int size = array.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = array.getJSONObject(i);
            final String thumb = data.getString("thumb");
            final String title = data.getString("title");
            final int id = data.getInteger("id");
            final double price = data.getDouble("price");
            final String time = data.getString("time");


            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setItemType(OrderListItemType.ITEM_ORDER_LIST)
                    .setField(MultipleFields.ID, id)
                    .setField(MultipleFields.IMAGE_URL, thumb)
                    .setField(MultipleFields.TITLE, title)
                    .setField(OrderItemFields.PRICE, price)
                    .setField(OrderItemFields.TIME,time)
                    .build();

            ENTITIES.add(entity);
        }

        return ENTITIES;
    }




}
