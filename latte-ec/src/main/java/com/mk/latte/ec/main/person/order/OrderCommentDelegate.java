package com.mk.latte.ec.main.person.order;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.mk.latte.delegates.LatteDelegate;
import com.mk.latte.ec.R;
import com.mk.latte.ec.R2;
import com.mk.latte.ui.widget.AutoPhotoLayout;
import com.mk.latte.ui.widget.StarLayout;
import com.mk.latte.util.callback.CallBackManager;
import com.mk.latte.util.callback.CallBackType;
import com.mk.latte.util.callback.IGlobalCallBack;
import com.mk.latte.util.toast.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Aatoken on 2018/4/21.
 */

public class OrderCommentDelegate extends LatteDelegate {

    @BindView(R2.id.custom_star_layout)
    StarLayout mStarLayout = null;

    @BindView(R2.id.custom_auto_photo_layout)
    AutoPhotoLayout mAutoPhotoLayout = null;


    @OnClick(R2.id.top_tv_comment_commit)
    void onClickSubmit() {
        ToastUtils.showToast( "评分： " + mStarLayout.getStarCount());
    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_order_comment;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        mAutoPhotoLayout.setDelegate(this);
        CallBackManager.getInstance()
                .addCallBack(CallBackType.ON_CROP, new IGlobalCallBack<Uri>() {
                    @Override
                    public void executeCallBack(@Nullable Uri args) {
                        mAutoPhotoLayout.onCropTarget(args);
                    }
                });
    }
}
