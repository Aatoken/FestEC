package com.mk.latte.ui.recycle;

import com.google.auto.value.AutoValue;

/**
 * @author lenovo
 * @data 2017/11/15
 */
@AutoValue
public abstract class RgbValue {


    public abstract int red();

    public abstract int green();

    public abstract int blue();

    public static RgbValue create(int red, int green, int blue) {
        return new AutoValue_RgbValue(red, green, blue);
    }




}
