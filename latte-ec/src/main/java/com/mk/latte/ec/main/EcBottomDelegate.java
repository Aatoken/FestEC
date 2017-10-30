package com.mk.latte.ec.main;

import android.graphics.Color;

import com.mk.latte.delegates.bottom.BaseBottomDelegate;
import com.mk.latte.delegates.bottom.BottomItemDelegate;
import com.mk.latte.delegates.bottom.BottomTabBean;
import com.mk.latte.delegates.bottom.ItemBuilder;
import com.mk.latte.ec.main.cart.ShopCartDelegate;
import com.mk.latte.ec.main.discover.DiscoverDelegate;
import com.mk.latte.ec.main.index.IndexDelegate;
import com.mk.latte.ec.main.person.PersonalDelegate;
import com.mk.latte.ec.main.sort.SortDelegate;

import java.util.LinkedHashMap;

/**
 * @author lenovo
 * @data 2017/10/30
 */

public class EcBottomDelegate extends BaseBottomDelegate {


    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}", "主页"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-sort}", "分类"), new SortDelegate());
        items.put(new BottomTabBean("{fa-compass}", "发现"), new DiscoverDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}", "购物车"), new ShopCartDelegate());
        items.put(new BottomTabBean("{fa-user}", "我的"), new PersonalDelegate());
        return builder.addItems(items).build();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return Color.parseColor("#ffff8800");
    }

    @Override
    public int setInitColor() {
        return Color.parseColor("#ffff8800");
    }
}
