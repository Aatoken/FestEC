package com.mk.latte.ec.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.joanzapata.iconify.widget.IconTextView;
import com.mk.latte.delegates.bottom.BottomItemDelegate;
import com.mk.latte.ec.R;
import com.mk.latte.ec.R2;
import com.mk.latte.ui.refresh.RefreshHandler;

import butterknife.BindView;


/**
 * @author lenovo
 * @data 2017/10/30
 */

public class IndexDelegate extends BottomItemDelegate {


    @BindView(R2.id.rv_index)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.srl_index)
    SwipeRefreshLayout mRefreshLayout = null;
    @BindView(R2.id.tb_index)
    Toolbar mToolbar = null;
    @BindView(R2.id.icon_index_scan)
    IconTextView mIconScan = null;
    @BindView(R2.id.et_search_view)
    AppCompatEditText mSearchView = null;

    private RefreshHandler mRefreshHandler = null;



    /**
     * 初始化加载 layout
     */
    private void initRefreshLayout() {
        mRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
        mRefreshLayout.setProgressViewOffset(true, 120, 300);
    }

    /**
     * 懒加载
     * @param savedInstanceState
     */
    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        //初始化 refresh
        initRefreshLayout();
        //填充数据
        initRecycleView();
        //下拉刷新 获取数据
        mRefreshHandler.firstPage("index.php");

    }

    /**
     * 初始化 RecycleView
     * manager 的设置
     */
    private void initRecycleView()
    {
        //将 宽分为4 份
        final  int spanSize=4;
        final GridLayoutManager manager=new GridLayoutManager(getContext(),spanSize);
        mRecyclerView.setLayoutManager(manager);

    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        //使用handler控制 mRefreshLayout
        mRefreshHandler = RefreshHandler.create(mRefreshLayout, mRecyclerView, new
                IndexDataConverter());
    }




}
