package com.mk.latte.ec.main.person.profile;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.mk.latte.delegates.LatteDelegate;
import com.mk.latte.ec.R;
import com.mk.latte.ec.main.person.list.ListBean;
import com.mk.latte.net.RestClient;
import com.mk.latte.net.callback.ISuccess;
import com.mk.latte.ui.data.DateDialogUtil;
import com.mk.latte.util.callback.CallBackManager;
import com.mk.latte.util.callback.CallBackType;
import com.mk.latte.util.callback.IGlobalCallBack;
import com.mk.latte.util.log.LatteLogger;

/**
 * @author lenovo
 * @data 2017/11/17
 */

public class UserProfileClickListener extends SimpleClickListener {

    private final LatteDelegate DELEGATE;
    private String[] mGenders = new String[]{"男", "女", "保密"};


    public UserProfileClickListener(LatteDelegate delegate) {
        this.DELEGATE = delegate;
    }


    @Override
    public void onItemClick(final BaseQuickAdapter adapter, final View view, int position) {
        final ListBean entity = (ListBean) baseQuickAdapter.getData().get(position);
        final int id = entity.getId();
        switch (id) {
            case 1:
                CallBackManager.getInstance()
                        .addCallBack(CallBackType.ON_CROP, new IGlobalCallBack<Uri>() {
                            @Override
                            public void executeCallBack(Uri args) {
                                final ImageView avatar = (ImageView) view.findViewById
                                        (R.id.img_arrow_avatar);
                                Glide.with(DELEGATE)
                                        .load(args)
                                        .into(avatar);
                                LatteLogger.d("ON_CROP", args);

                                //上传服务器
                                RestClient.builder()
                                        .url(UploadConfig.UPLOAD_IMG)
                                        .loader(DELEGATE.getContext())
                                        .file(args.getPath())
                                        .success(new ISuccess() {
                                            @Override
                                            public void onSuccess(String response) {
                                                LatteLogger.d("ON_CROP_UPLOAD", response);
                                                //通知服务器更新信息
                                            }
                                        }).build()
                                        .upload();

                            }
                        });
                //打开照相机或选择图片
                DELEGATE.startCameraWithCheck();
                break;
            case 2:
                //姓名
                final LatteDelegate nameDelegate = entity.getDelegate();
                DELEGATE.getSupportDelegate().start(nameDelegate);

                break;
            case 3:
                //性别
                getGenderDialog(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final TextView textView = (TextView) view.findViewById(R.id.tv_arrow_value);
                        textView.setText(mGenders[which]);
                        dialog.cancel();
                    }
                });
                break;
            case 4:
                //生日
                final DateDialogUtil dateDialogUtil = new DateDialogUtil();
                dateDialogUtil.setIDateListener(new DateDialogUtil.IDateListener() {
                    @Override
                    public void onDateChange(String date) {
                        final TextView textView = (TextView) view.findViewById(R.id.tv_arrow_value);
                        textView.setText(date);
                    }
                });
                dateDialogUtil.showDailog(DELEGATE.getContext());

                break;
            default:
                break;
        }
    }

    private void getGenderDialog(DialogInterface.OnClickListener listener) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(DELEGATE.getContext());
        builder.setSingleChoiceItems(mGenders, 0, listener);
        builder.show();
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }


}
