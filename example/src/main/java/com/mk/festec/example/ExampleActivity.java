package com.mk.festec.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;

import com.mk.latte.activities.ProxyActivity;
import com.mk.latte.delegates.LatteDelegate;
import com.mk.latte.ec.launcher.LauncherDelegate;
import com.mk.latte.ec.main.EcBottomDelegate;
import com.mk.latte.ec.sign.ISignListener;
import com.mk.latte.ec.sign.SignInDelegate;
import com.mk.latte.ui.launcher.ILauncherListener;
import com.mk.latte.ui.launcher.OnLauncherFinishTag;
import com.mk.latte.util.toast.ToastUtils;

/**
 * 单activity 的跟Activity
 * @author lenovo
 */
public class ExampleActivity extends ProxyActivity
        implements ISignListener,ILauncherListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //取消掉 actionBar
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null)
        {
            actionBar.hide();
        }
    }

    @Override
    public LatteDelegate setRootDelegate() {
        return new LauncherDelegate();
    }


    @Override
    public void onSignInSuccess() {
        ToastUtils.showToast(this,"登录成功");
    }

    @Override
    public void onSignUpSuccess() {
        ToastUtils.showToast(this,"注册成功");
    }

    /**
     * 界面启动回调
     * @param tag
     */
    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag) {
            case SIGNED:
                ToastUtils.showToast(this,"启动结束,用户登录了");
                //跳转到首页
                getSupportDelegate().startWithPop(new EcBottomDelegate());
                break;
            case NOT_SIGNED:
                ToastUtils.showToast(this,"启动结束,用户未登录");
                //跳转到登录界面
                getSupportDelegate().startWithPop(new SignInDelegate());
                break;
            default:
                break;
        }
    }


}
