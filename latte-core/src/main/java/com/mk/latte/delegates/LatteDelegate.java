package com.mk.latte.delegates;

/**
 *
 *
 * @author lenovo
 * @date 2017/10/16
 */

public abstract class LatteDelegate extends PermissionCheckerDelegate {

    @SuppressWarnings("unchecked")
    public <T extends LatteDelegate> T getParentDelegate()
    {
        return (T) getParentFragment();
    }

}
