package com.mk.latte.ui.recycle;

import android.support.annotation.ColorInt;

import com.choices.divider.DividerItemDecoration;

/**
 * @author lenovo
 * @data 2017/11/2
 */

public class BaseDecoration  extends DividerItemDecoration{


    private BaseDecoration(@ColorInt int color, int size) {
        setDividerLookup(new DividerLookupImpl(color,size));
    }

     public static BaseDecoration create(@ColorInt int color, int size)
     {
         return new BaseDecoration(color,size);
     }



}
