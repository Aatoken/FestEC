package com.mk.festec.example;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.mk.latte.app.Latte;
import com.mk.latte.ec.icon.FontModule;

/**
 * Created by lenovo on 2017/10/15.
 */

public class ExampleApp extends Application {


    //你好啊

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontModule())
                .withApiHost("http://127.0.0.1/")
                .configure();
    }



}
