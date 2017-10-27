package com.mk.latte.util.timer;

import java.util.TimerTask;

/**
 * @author lenovo
 * @data 2017/10/27
 */

public class BaseTimerTask extends TimerTask {

    private ITimerListener mITimerListener=null;

    public BaseTimerTask(ITimerListener iTimerListener) {
        this.mITimerListener = iTimerListener;
    }

    @Override
    public void run() {
        if(mITimerListener!=null)
        {
            mITimerListener.onTimer();
        }
    }




}
