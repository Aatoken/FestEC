package com.mk.festec.example;

import com.mk.latte.activities.ProxyActivity;
import com.mk.latte.delegates.LatteDelegate;

/**
 * 单activity 的跟Activity
 */
public class ExampleActivity extends ProxyActivity {

    @Override
    public LatteDelegate setRootDelegate() {
        return new ExampleDelegate();
    }




}
