package com.mk.latte.ui.launcher;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

/**
 * @author lenovo
 * @data 2017/10/27
 */

public class LauncherHolderCreator implements CBViewHolderCreator<LauncherHolder>{
    @Override
    public LauncherHolder createHolder() {
        return new LauncherHolder();
    }
}
