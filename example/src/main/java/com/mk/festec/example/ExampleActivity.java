package com.mk.festec.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import com.mk.latte.activities.ProxyActivity;
import com.mk.latte.app.Latte;
import com.mk.latte.delegates.LatteDelegate;
import com.mk.latte.ec.main.EcBottomDelegate;
import com.mk.latte.ec.sign.ISignListener;
import com.mk.latte.ec.sign.SignInDelegate;
import com.mk.latte.ui.launcher.ILauncherListener;
import com.mk.latte.ui.launcher.OnLauncherFinishTag;

import qiu.niorgai.StatusBarCompat;

/**
 * @author lenovo
 * @data 2017/11/15
 */

public class ExampleActivity  extends ProxyActivity implements
        ISignListener,
        ILauncherListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        Latte.getConfigurator().withActivity(this);
        StatusBarCompat.translucentStatusBar(this, true);

    }

    @Override
    protected void onPause() {
        super.onPause();
        //JPushInterface.onPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //JPushInterface.onResume(this);
    }

    @Override
    public LatteDelegate setRootDelegate() {
        return new EcBottomDelegate();
    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag) {
            case SIGNED:
                //                Toast.makeText(this, "启动结束，用户登录了", Toast.LENGTH_LONG).show();
                getSupportDelegate().startWithPop(new EcBottomDelegate());
                break;
            case NOT_SIGNED:
                //                Toast.makeText(this, "启动结束，用户没登录", Toast.LENGTH_LONG).show();
                getSupportDelegate().startWithPop(new SignInDelegate());
                break;
            default:
                break;
        }
    }


}
