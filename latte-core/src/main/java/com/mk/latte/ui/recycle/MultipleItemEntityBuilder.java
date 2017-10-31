package com.mk.latte.ui.recycle;

import java.util.LinkedHashMap;
import java.util.WeakHashMap;

/**
 * @author lenovo
 * @data 2017/10/31
 */

public class MultipleItemEntityBuilder {

    private static final LinkedHashMap<Object, Object> FIELDS = new LinkedHashMap<>();


      MultipleItemEntityBuilder() {
        //先清除之前的数据
        FIELDS.clear();
    }


    public final MultipleItemEntityBuilder setItemType(int itemType) {
        FIELDS.put(MultipleFields.ITEM_TYPE.name(), itemType);
        return this;
    }


    public final MultipleItemEntityBuilder setField(Object key, Object value) {
        FIELDS.put(key, value);
        return this;
    }

    public final MultipleItemEntityBuilder setFields(WeakHashMap<?, ?> map) {
        FIELDS.putAll(map);
        return this;
    }


    public final MultipleItemEntity build() {
        return new MultipleItemEntity(FIELDS);
    }


}












