package com.mk.latte.app;

import android.content.Context;

/**
 * Created by lenovo on 2017/10/15.
 */

public class Latte {


    /**
     * 初始化 Configurator 获取它的实例
     * @return
     */
    private static Configurator getConfigurator() {
        return Configurator.getInstance();
    }


    public static Configurator init() {
      return getConfigurator();
    }



    /**
     * 获取 Configurator 中 LATTE_CONFIGS集合中 key队友的value
     * @param key key
     * @param <T> value
     * @return
     */
    public static <T> T getConfigurationByKey(Object key) {
        return getConfigurator().getConfiguration(key);
    }


    /**
     * 返回配置中的 Context
     * @return Context
     */
    public static Context getApplicationContext() {
        return (Context) getConfigurationByKey(ConfigType.APPLICATION_CONTEXT.name());
    }


}
