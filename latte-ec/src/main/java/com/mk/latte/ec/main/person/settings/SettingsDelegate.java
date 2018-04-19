package com.mk.latte.ec.main.person.settings;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CompoundButton;

import com.mk.latte.delegates.LatteDelegate;
import com.mk.latte.ec.R;
import com.mk.latte.ec.R2;
import com.mk.latte.ec.main.person.list.ListAdapter;
import com.mk.latte.ec.main.person.list.ListBean;
import com.mk.latte.ec.main.person.list.ListItemType;
import com.mk.latte.util.callback.CallBackManager;
import com.mk.latte.util.callback.CallBackType;
import com.mk.latte.util.toast.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Aatoken on 2018/4/19.
 */

public class SettingsDelegate extends LatteDelegate {

    @BindView(R2.id.rv_settings)
    RecyclerView mRecyclerView = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_settings;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        final ListBean address = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_SWITCH)
                .setId(1)
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            CallBackManager.getInstance().getCallBack(CallBackType.TAG_OPEN_PUSH)
                                    .executeCallBack(null);
                            ToastUtils.showToast("打开推送");
                        } else {
                            CallBackManager.getInstance().getCallBack(CallBackType.TAG_STOP_PUSH)
                                    .executeCallBack(null);
                            ToastUtils.showToast("关闭推送");
                        }
                    }
                })
                .setText("技工推送")
                .build();

        final ListBean system = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(2)
                .setDelegate(new AboutDelegate())
                .setText("关于")
                .build();

        final List<ListBean> data = new ArrayList<>();
        data.add(address);
        data.add(system);

        //设置RecyclerView
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        final ListAdapter adapter = new ListAdapter(data);
        mRecyclerView.setAdapter(adapter);

        mRecyclerView.addOnItemTouchListener(new SettingsClickListener(this));
    }


}
