package com.mk.festec.example.event;

import android.widget.Toast;

import com.mk.latte.delegates.web.event.Event;

/**
 * @author lenovo
 * @data 2017/11/9
 */

public class TestEvent extends Event {
    @Override
    public String excute(String params) {
        Toast.makeText(getContext(), getAction(), Toast.LENGTH_LONG).show();
        return null;
    }
}
