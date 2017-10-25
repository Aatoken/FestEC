package com.mk.latte.ec.icon;


import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.IconFontDescriptor;

/**
 * Created by lenovo on 2017/10/15.
 */


public class FontModule implements IconFontDescriptor {


    @Override
    public String ttfFileName() {
         return "iconfont.ttf";
    }

    @Override
    public Icon[] characters() {
        return EcIcons.values();
    }
}
