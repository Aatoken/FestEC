package com.mk.festec.example;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.mk.latte.app.Latte;
import com.mk.latte.ec.icon.FontModule;

/**
 * Created by lenovo on 2017/10/15.
 */

public class ExampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init()
                .withContext(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontModule())
                .withApiHost("http://news.baidu.com")
                .configure();
    }



}
