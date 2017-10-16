package com.mk.latte.app;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by lenovo on 2017/10/15.
 */

public class Configurator {

    //配置集合
    private static final HashMap<Object, Object> LATTE_CONFIGS = new HashMap<>();
    //Icon集合
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();

    private Configurator() {

        LATTE_CONFIGS.put(ConfigType.CONFIG_READY, false);

    }

    /**
     * 单例--获取 实例
     *
     * @return
     */
    static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * 内部类
     */
    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    /**
     * 配置准备完成
     */
    public final void configure() {
        initIcons();
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), true);
    }

    /**
     * 获取配置集合
     *
     * @return
     */
    final HashMap<Object, Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }


    /**
     * 配置 host 属性
     *
     * @param host
     * @return
     */
    public final Configurator withApiHost(String host) {
        LATTE_CONFIGS.put(ConfigType.API_HOST.name(), host);
        return this;
    }

    /**
     *
     * @param descriptor
     * @return
     */
    public final Configurator withIcon(IconFontDescriptor descriptor)
    {
        ICONS.add(descriptor);
        return this;
    }


    /**
     * 检测配置是否完成
     */
    private void checkConfiguration() {
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigType.CONFIG_READY.name());
        if (isReady) {
            throw new RuntimeException("Configuration is not ready,call configure");
        }
    }


    final <T> T getConfiguration(Enum<ConfigType> key) {
        checkConfiguration();
        return (T) LATTE_CONFIGS.get(key.name());
    }



    /**
     * 初始化 Icons
     */
    private void initIcons() {
        if (ICONS.size() > 0) {
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }


}














