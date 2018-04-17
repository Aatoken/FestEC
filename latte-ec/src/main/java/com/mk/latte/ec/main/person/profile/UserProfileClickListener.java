package com.mk.latte.ec.main.person.profile;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.mk.latte.delegates.LatteDelegate;
import com.mk.latte.ec.R;
import com.mk.latte.ec.main.person.list.ListEntity;
import com.mk.latte.ui.data.DateDialogUtil;

/**
 * @author lenovo
 * @data 2017/11/17
 */

public class UserProfileClickListener  extends SimpleClickListener {

    private final LatteDelegate DELEGATE;
    private String[] mGenders=new String[]{"男","女","保密"};


    public UserProfileClickListener(LatteDelegate delegate) {
        this.DELEGATE = delegate;
    }


    @Override
    public void onItemClick(final BaseQuickAdapter adapter, final View view, int position) {
        final ListEntity entity= (ListEntity) baseQuickAdapter.getData().get(position);
        final int id=entity.getId();
        switch (id) {
            case 1:
                //打开照相机或选择图片
                DELEGATE.startCameraWithCheck();
                break;
            case 2:
                //姓名
                final LatteDelegate nameDelegate=entity.getDelegate();
                DELEGATE.getSupportDelegate().start(nameDelegate);

                break;
            case 3:
                //性别
                getGenderDialog(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final TextView textView= (TextView) view.findViewById(R.id.tv_arrow_value);
                        textView.setText(mGenders[which]);

                        dialog.cancel();
                    }
                });
                break;
            case 4:
                //生日
                final DateDialogUtil dateDialogUtil=new DateDialogUtil();
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

    private  void getGenderDialog(DialogInterface.OnClickListener listener)
    {
        final AlertDialog.Builder builder=new AlertDialog.Builder(DELEGATE.getContext());
        builder.setSingleChoiceItems(mGenders,0,listener);
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
