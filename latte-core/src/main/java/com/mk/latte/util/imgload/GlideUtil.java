package com.mk.latte.util.imgload;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

/**
 * @author lenovo
 * @data 2017/11/9
 */

public class GlideUtil {

    /**
     * 设置图片加载策略
     */
    public static final RequestOptions RECYCLER_OPTIONS =
            new RequestOptions()
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .dontAnimate();



}
