package com.mk.festec.example;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.mk.latte.app.Latte;
import com.mk.latte.ec.database.DatabaseManager;
import com.mk.latte.ec.icon.FontModule;
import com.mk.latte.net.interceptors.DebugInterceptor;

/**
 * Created by lenovo on 2017/10/15.
 */

public class ExampleApp extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        //初始化 配置参数
        Latte.init()
                .withContext(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontModule())
                .withLoaderDelayed(1000)
                //电脑服务地址：http://192.168.33.2:1210/RestServer/api/index.php
                //新闻测试 http://news.baidu.com/
                .withApiHost("http://192.168.33.2:1210/RestServer/api/")
                .withInterceptor(new DebugInterceptor("test", R.raw.test))
                .configure();

        //初始化数据库
        DatabaseManager.getInstance().init(this);


    }


}
