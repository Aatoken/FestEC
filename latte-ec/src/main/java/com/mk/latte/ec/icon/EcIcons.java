package com.mk.latte.ec.icon;

import com.joanzapata.iconify.Icon;

/**
 *
 * @author lenovo
 * @date 2017/10/15
 */

public enum EcIcons implements Icon {
    icon_scan('\ue602'),
    icon_ali_pay('\ue606'),
    icon_wx_pay('\ue615'),
    icon_qq_pay('\ue60d');


    private char character;

    EcIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}
