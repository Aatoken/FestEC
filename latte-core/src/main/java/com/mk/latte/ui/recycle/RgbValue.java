package com.mk.latte.ui.recycle;

import com.google.auto.value.AutoValue;

/**
 * 存储色体
 *
 * @author lenovo
 * @data 2017/11/2
 */
@AutoValue
public abstract class RgbValue {

    /**
     * 红色
     * @return
     */
    public abstract int red();

    /**
     *绿色
     * @return
     */
    public abstract int green();

    /**
     * 蓝色
     * @return
     */
    public abstract int blue();

    public static RgbValue create(int red, int green, int blue) {
        return new AutoValue_RgbValue(red, green, blue);
    }


}
