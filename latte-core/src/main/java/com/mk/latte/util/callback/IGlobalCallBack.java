package com.mk.latte.util.callback;

import android.support.annotation.Nullable;

/**
 * 全局回调
 * Created by Aatoken on 2018/4/18.
 */

public interface IGlobalCallBack<T> {

    void executeCallBack(@Nullable T args);
}
