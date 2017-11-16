package com.mk.latte.ec.main.person;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mk.latte.delegates.bottom.BottomItemDelegate;
import com.mk.latte.ec.R;
import com.mk.latte.ec.R2;
import com.mk.latte.ec.main.person.list.ListEntity;
import com.mk.latte.ec.main.person.list.ListEntityAdapter;
import com.mk.latte.ec.main.person.list.ListItemType;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author lenovo
 * @data 2017/10/30
 */

public class PersonalDelegate extends BottomItemDelegate {

    @BindView(R2.id.rv_personal_setting)
    RecyclerView mRvSettings = null;


    @Override
    public Object setLayout() {
        return R.layout.delegate_personal;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        final ListEntity address = new ListEntity.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(1)
                .setText("收货地址")
                .build();

        final ListEntity system = new ListEntity.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(2)
                .setText("系统设置")
                .build();

        final List<ListEntity> data = new ArrayList<>();
        data.add(address);
        data.add(system);


        //设置RecyclerView
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRvSettings.setLayoutManager(manager);
        final ListEntityAdapter adapter = new ListEntityAdapter(data);
        mRvSettings.setAdapter(adapter);

    }




}
