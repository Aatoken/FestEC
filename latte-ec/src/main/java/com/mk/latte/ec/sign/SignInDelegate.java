package com.mk.latte.ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.mk.latte.delegates.LatteDelegate;
import com.mk.latte.ec.R;
import com.mk.latte.ec.R2;
import com.mk.latte.net.RestClient;
import com.mk.latte.net.callback.IFailure;
import com.mk.latte.net.callback.ISuccess;
import com.mk.latte.util.log.LatteLogger;
import com.mk.latte.util.toast.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author lenovo
 * @data 2017/10/27
 */

public class SignInDelegate extends LatteDelegate {


    @BindView(R2.id.edit_sign_in_email)
    TextInputEditText mEmail = null;
    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText mPassword = null;


    private ISignListener mISignListener = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof ISignListener)
        {
            mISignListener=(ISignListener)activity;
        }
    }

    /**
     * 登录点击事件
     */
    @OnClick(R2.id.btn_sign_in)
    void onClickSignIn() {
        if (checkForm()) {

            RestClient.builder()
                    .url("user_profile.php")
                    .params("email", mEmail.getText().toString())
                    .params("password", mPassword.getText().toString())
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            //打印日志
                            LatteLogger.json("USER_PROFILE", response);
                            //登录
                            SignHandler.onSingIn(response,mISignListener);

                        }
                    })
                    .failure(new IFailure() {
                        @Override
                        public void onFailure() {
                            ToastUtils.showToast(getContext(),"注册失败");
                        }
                    })
                    .build()
                    .post();
        }
    }


    @OnClick(R2.id.icon_sign_in_wechat)
    void onClickWeChat() {
        Toast.makeText(getContext(), "微信登录", Toast.LENGTH_SHORT).show();
    }


    @OnClick(R2.id.tv_link_sign_up)
    void onClickLink() {
        getSupportDelegate().start(new SignUpDelegate());
    }


    /**
     * 检测输入的数据格式是否正确
     *
     * @return
     */
    private boolean checkForm() {
        final String email = mEmail.getText().toString();
        final String password = mPassword.getText().toString();

        boolean isPass = true;

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            mEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("请填写至少6位数密码");
            isPass = false;
        } else {
            mPassword.setError(null);
        }

        return isPass;
    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
