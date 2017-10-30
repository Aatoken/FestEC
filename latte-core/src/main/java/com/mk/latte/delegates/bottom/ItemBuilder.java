package com.mk.latte.delegates.bottom;

import java.util.LinkedHashMap;

/**
 * @author lenovo
 * @data 2017/10/30
 */

public final class ItemBuilder {

    /**
     * 有序的存储 BottomTabBean与 delegate
     */
    private final LinkedHashMap<BottomTabBean, BottomItemDelegate> ITEMS = new LinkedHashMap<>();

    /**
     * 简单工厂
     *
     * @return
     */
    static ItemBuilder builder() {
        return new ItemBuilder();
    }


    public final ItemBuilder addItem(BottomTabBean bean, BottomItemDelegate delegate) {
        ITEMS.put(bean,delegate);
        return this;
    }

    public final ItemBuilder addItems(LinkedHashMap<BottomTabBean, BottomItemDelegate> items) {
        ITEMS.putAll(items);
        return this;
    }

    public  final LinkedHashMap<BottomTabBean, BottomItemDelegate> build()
    {
        return ITEMS;
    }



}





















