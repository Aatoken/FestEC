package com.mk.latte.ec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.mk.latte.app.AccountManager;
import com.mk.latte.app.IUserChecker;
import com.mk.latte.delegates.LatteDelegate;
import com.mk.latte.ec.R;
import com.mk.latte.ec.R2;
import com.mk.latte.ui.launcher.ILauncherListener;
import com.mk.latte.ui.launcher.OnLauncherFinishTag;
import com.mk.latte.ui.launcher.ScrollLauncherTag;
import com.mk.latte.util.storage.LattePreference;
import com.mk.latte.util.timer.BaseTimerTask;
import com.mk.latte.util.timer.ITimerListener;
import com.mk.latte.util.toast.ToastUtils;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @author lenovo
 * @data 2017/10/27
 */

public class LauncherDelegate extends LatteDelegate implements ITimerListener {

    /**
     * 广告倒计时
     */
    private int mCount = 5;
    private Timer mTimer = null;
    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView mTvTimer = null;

    private ILauncherListener mILauncherListener;

    /**
     * 用于接口回调
     *
     * @param activity
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener) {
            mILauncherListener = (ILauncherListener) activity;
        }
    }

    /**
     * 手动点击取消倒计时
     */
    @OnClick(R2.id.tv_launcher_timer)
    void onClickTimerView() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
            //倒计时结束
            ToastUtils.showToast(getContext(), "取消倒计时");
            checkIsShowScroll();
        }
    }

    private void initTimer() {
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        mTimer.schedule(task, 0, 1000);
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initTimer();
    }


    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mTvTimer != null) {
                    mTvTimer.setText(MessageFormat.format("跳过\n{0}s", mCount));
                    mCount--;
                    if (mCount < 0) {
                        if (mTimer != null) {
                            mTimer.cancel();
                            mTimer = null;
                            //倒计时结束
                            ToastUtils.showToast(getContext(), "倒计时完成！");
                            checkIsShowScroll();
                        }
                    }
                }
            }
        });

    }


    /**
     * 检测是否显示滑动启动页面
     */
    private void checkIsShowScroll() {
        //默认为 false：未启动
        if (!LattePreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name())) {
            ToastUtils.showToast(getContext(), "第一次启动！");
            //显示 滑动启动页面
            getSupportDelegate().start(new LauncherScrollDelegate(), SINGLETASK);

        } else {
            //用户是否登录了 APP
            AccountManager.checkAccount(new IUserChecker() {
                @Override
                public void onSignIn() {
                    if (mILauncherListener != null) {
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.SIGNED);
                    }
                }

                @Override
                public void onNotSignIn() {
                    if (mILauncherListener != null) {
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.NOT_SIGNED);
                    }
                }
            });

        }


    }


}


















