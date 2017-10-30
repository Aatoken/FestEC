package com.mk.latte.delegates.bottom;

/**
 * @author lenovo
 * @data 2017/10/30
 */

public final class BottomTabBean {

    private final CharSequence ICON;
    private final CharSequence TITLE;


    public BottomTabBean(CharSequence icon, CharSequence title) {
        this.ICON = icon;
        this.TITLE = title;
    }


    public CharSequence getIcon() {
        return ICON;
    }

    public CharSequence getTitle() {
        return TITLE;
    }
}












