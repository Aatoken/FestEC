package com.mk.latte.delegates.bottom;

import android.view.KeyEvent;
import android.view.View;

import com.mk.latte.delegates.LatteDelegate;
import com.mk.latte.util.toast.ToastUtils;

/**
 * @author lenovo
 * @data 2017/10/30
 */

public abstract class BottomItemDelegate extends LatteDelegate
        implements View.OnKeyListener {

    /**
     * 记录上一次 back的时间
     */
    private long mExitTime = 0;
    /**
     * 按键的间隔时间
     */
    private static final int EXIT_TIME = 2000;

    @Override
    public void onResume() {
        super.onResume();
        final View rootView=getView();
        if(rootView!=null)
        {
            rootView.setFocusableInTouchMode(true);
            rootView.requestFocus();
            rootView.setOnKeyListener(this);
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - mExitTime) > EXIT_TIME) {
                ToastUtils.showToast(getContext(), "双击退出");
                mExitTime = System.currentTimeMillis();
            } else {
                //单activity finsh相当于退出 APP
                _mActivity.finish();
                if (mExitTime != 0) {
                    mExitTime = 0;
                }
            }
        }
        //消费该事件
        return true;
    }


}


















