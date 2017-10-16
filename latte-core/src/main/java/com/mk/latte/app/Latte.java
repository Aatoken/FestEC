package com.mk.latte.app;

import android.content.Context;

import java.util.HashMap;

/**
 * Created by lenovo on 2017/10/15.
 */

public class Latte {

    /**
     * 初始化
     * @param context 建议使用
     * @return
     */
    public static Configurator init(Context context) {
        getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(), context.getApplicationContext());
        return Configurator.getInstance();
    }

    /**
     * 获取 Configura
     * @return
     */
    private static HashMap<Object, Object> getConfigurations() {
        return Configurator.getInstance().getLatteConfigs();
    }

    /**
     * 获取 application Context
     * @return
     */
    public static Context getApplication()
    {
        return (Context) getConfigurations().get(ConfigType.APPLICATION_CONTEXT.name());
    }


}
