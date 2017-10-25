package com.mk.festec.example;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.mk.latte.app.ConfigType;
import com.mk.latte.app.Latte;

/**
 * 测试网络
 * Created by lenovo on 2017/10/19.
 */

public class ExampleTest extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String apiHost = Latte.getConfigurationByKey(ConfigType.API_HOST.name());
        Toast.makeText(this,apiHost , Toast.LENGTH_SHORT).show();

    }



}
