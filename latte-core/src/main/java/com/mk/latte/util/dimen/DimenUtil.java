package com.mk.latte.util.dimen;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.mk.latte.app.Latte;

/**
 * 测量的工具方法
 * @author lenovo
 * @data 2017/10/25
 */

public class DimenUtil {

    public static int getScreenWidth() {
        final Resources resources = Latte.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight() {
        final Resources resources = Latte.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }

}
