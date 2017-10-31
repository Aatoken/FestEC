package com.mk.latte.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mk.latte.app.Latte;
import com.mk.latte.net.RestClient;
import com.mk.latte.net.callback.ISuccess;
import com.mk.latte.ui.recycle.DataConverter;
import com.mk.latte.ui.recycle.MultipleRecyclerAdapter;

/**
 * @author lenovo
 * @data 2017/10/31
 */

public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener
     ,BaseQuickAdapter.RequestLoadMoreListener{

    /**
     * 刷新的 layout
     */
    private final SwipeRefreshLayout REFRESH_LAYOUT;
    /**
     * 显示的 控件 RecyclerView
     */
    private final RecyclerView RECYCLERVIEW;
    /**
     * 适配器
     */
    private MultipleRecyclerAdapter mAdapter = null;
    /**
     * 显示的数据
     */
    private final DataConverter CONVERTER;
    /**
     * 分页显示 bean
     */
    private final PagingBean BEAN;

    private RefreshHandler(SwipeRefreshLayout refreshlayout,
                           RecyclerView recyclerview,
                           DataConverter converter,
                           PagingBean bean) {

        this.REFRESH_LAYOUT = refreshlayout;
        this.RECYCLERVIEW = recyclerview;
        this.CONVERTER = converter;
        this.BEAN = bean;
        //设置刷新事件
        REFRESH_LAYOUT.setOnRefreshListener(this);
    }

    /**
     * 创建 RefreshHandler 的实例
     *
     * @param refreshlayout layout
     * @param recyclerview  recycleview
     * @param converter     数据
     * @return
     */
    public static RefreshHandler create(SwipeRefreshLayout refreshlayout,
                                        RecyclerView recyclerview,
                                        DataConverter converter) {
        return new RefreshHandler(refreshlayout, recyclerview, converter,
                new PagingBean());
    }


    /**
     * 下拉刷新
     */
    private void refresh() {
        REFRESH_LAYOUT.setRefreshing(true);

        Latte.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //进行网络的数据加载
                REFRESH_LAYOUT.setRefreshing(false);
            }
        }, 2000);
    }

    public void firstPage(String url) {
        BEAN.setDelayed(1000);

        RestClient.builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final JSONObject object = JSON.parseObject(response);
                        //填充 PagingBean
                        BEAN.setTotal(object.getInteger("total"))
                                .setPageSize(object.getInteger("page_size"));
                        //设置 adapter
                        mAdapter = MultipleRecyclerAdapter.create(CONVERTER.setJsonData
                                (response));
                        mAdapter.setOnLoadMoreListener(RefreshHandler.this,RECYCLERVIEW);
                        RECYCLERVIEW.setAdapter(mAdapter);
                        //下一页
                        BEAN.addIndex();


                    }
                })
                .build()
                .get();
    }


    @Override
    public void onRefresh() {
        refresh();
    }


    @Override
    public void onLoadMoreRequested() {

    }
}
