package com.mk.latte.ui.banner;

import com.ToxicBakery.viewpager.transforms.DefaultTransformer;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.mk.latte.R;

import java.util.ArrayList;

/**
 * @author lenovo
 * @data 2017/10/31
 */

public class BannerCreator {

    public static void setDefault(ConvenientBanner<String> convenientBanner
            , ArrayList<String> banners, OnItemClickListener listener) {

        convenientBanner
                .setPages(new HolderCreator(), banners)
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(listener)
                //
                .setPageTransformer(new DefaultTransformer())
                //延迟时间
                .startTurning(3000)
                //允许循环
                .setCanLoop(true);
    }

}
