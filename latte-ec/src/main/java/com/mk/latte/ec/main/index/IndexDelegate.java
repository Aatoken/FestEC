package com.mk.latte.ec.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
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
import com.mk.latte.ec.main.EcBottomDelegate;
import com.mk.latte.ec.main.index.search.SearchDelegate;
import com.mk.latte.ui.recycle.BaseDecoration;
import com.mk.latte.ui.refresh.RefreshHandler;
import com.mk.latte.util.callback.CallBackManager;
import com.mk.latte.util.callback.CallBackType;
import com.mk.latte.util.callback.IGlobalCallBack;
import com.mk.latte.util.toast.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @author lenovo
 * @data 2017/10/30
 */

public class IndexDelegate extends BottomItemDelegate  implements View.OnFocusChangeListener{


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

    //二维码扫描
    @OnClick(R2.id.icon_index_scan)
    void  onClickScanOrCode()
    {
        startScanWithCheck(this.getParentDelegate());
    }

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
     *
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
    private void initRecycleView() {

        final GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
        mRecyclerView.setLayoutManager(manager);
        //添加边框
        mRecyclerView.addItemDecoration(BaseDecoration.create(ContextCompat.getColor(getContext()
                , R.color.app_background), 5));
        //点击事件
        //获得父容器
        final EcBottomDelegate ecBottomDelegate=getParentDelegate();
        mRecyclerView.addOnItemTouchListener(IndexItemClickListener.create(ecBottomDelegate));

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

        CallBackManager.getInstance()
                .addCallBack(CallBackType.ON_SCAN, new IGlobalCallBack<String>() {
                    @Override
                    public void executeCallBack(@Nullable String args) {
                        //二维码的回调

                        ToastUtils.showToast("得到的二维码:"+args);
                    }
                });

        mSearchView.setOnFocusChangeListener(this);
    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if(hasFocus)
        {
            getParentDelegate().getSupportDelegate().start(new SearchDelegate());
        }
    }


}
