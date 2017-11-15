package com.mk.latte.ec.main.cart;

/**
 * @author lenovo
 * @data 2017/11/11
 */

public interface ICartItemListener {
    /**
     * 点击加减 商品 数量，计算总价格
     * @param itemTotalPrice
     */
    void onItemClick(double itemTotalPrice);
}
