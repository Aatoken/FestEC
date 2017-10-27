package com.mk.latte.ec.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.mk.latte.delegates.LatteDelegate;
import com.mk.latte.ec.R;
import com.mk.latte.ui.launcher.LauncherHolderCreator;
import com.mk.latte.ui.launcher.ScrollLauncherTag;
import com.mk.latte.util.storage.LattePreference;
import com.mk.latte.util.toast.ToastUtils;

import java.util.ArrayList;

/**
 * @author lenovo
 * @data 2017/10/27
 */

public class LauncherScrollDelegate extends LatteDelegate implements OnItemClickListener {

    private ConvenientBanner<Integer> mConvenientBanner = null;
    private static final ArrayList<Integer> INTEGERS = new ArrayList<>();

    private void initBanner() {

        INTEGERS.add(R.mipmap.launcher_01);
        INTEGERS.add(R.mipmap.launcher_02);
        INTEGERS.add(R.mipmap.launcher_03);
        INTEGERS.add(R.mipmap.launcher_04);
        INTEGERS.add(R.mipmap.launcher_05);
        mConvenientBanner
                .setPages(new LauncherHolderCreator(), INTEGERS)
                //设置导航界面的 小圆点
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                //设置它的位置
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this)
                //设置可以循环
                .setCanLoop(false);


    }

    @Override
    public Object setLayout() {
        mConvenientBanner = new ConvenientBanner<>(getContext());
        return mConvenientBanner;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initBanner();
    }

    @Override
    public void onItemClick(int position) {
        //如果是点击的最后一个
        if (position == INTEGERS.size() - 1) {
            ToastUtils.showToast(getContext(), "点击了滑动启动页面");
            //true 表示已经进入过滑动启动页面
            LattePreference.setAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name(), true);
            //检测 用户是否已经登录
        }

    }


}
