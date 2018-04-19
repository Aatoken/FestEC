package com.mk.festec.example;

import android.support.multidex.MultiDexApplication;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.mk.festec.example.event.TestEvent;
import com.mk.latte.app.Latte;
import com.mk.latte.ec.database.DatabaseManager;
import com.mk.latte.ec.icon.FontModule;
import com.mk.latte.net.interceptors.DebugInterceptor;
import com.mk.latte.net.rx.AddCookieInterceptor;

import cn.jpush.android.api.JPushInterface;

/**
 * @author lenovo
 * @date 2017/10/15
 */

public class ExampleApp extends MultiDexApplication {


    @Override
    public void onCreate() {
        super.onCreate();
        //初始化 配置参数
        Latte.init()
                .withContext(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontModule())
                .withLoaderDelayed(1000)
                //本地/模拟器
                //http://127.0.0.1:1314/RestServer/api/index.php
                //http://192.168.75.2:1314/RestServer/api/index.php
                //真机 http://192.168.1.105:1314/RestServer/api/index.php
                .withApiHost("http://192.168.1.105:1314/RestServer/api/")
                .withInterceptor(new DebugInterceptor("test", R.raw.test))
                .withJavaScriptInterface("latte")
                .withWebEevent("test", new TestEvent())
                //添加Cookie同步拦截器
                .withInterceptor(new AddCookieInterceptor())
                .withWebHost("https://www.baidu.com/")
                .configure();

        //初始化数据库
        DatabaseManager.getInstance().init(this);

        //开启极光推送
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

    }


}
