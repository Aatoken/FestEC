package com.mk.latte.ec.main.sort.content;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.mk.latte.delegates.LatteDelegate;
import com.mk.latte.ec.R;
import com.mk.latte.ec.R2;
import com.mk.latte.net.RestClient;
import com.mk.latte.net.callback.ISuccess;

import java.util.List;

import butterknife.BindView;

/**
 * @author lenovo
 * @data 2017/11/8
 */

public class ContentDelegate extends LatteDelegate {

    private static final String ARG_CONTENT_ID = "CONTENT_ID";
    private int mContentId = -1;
    private List<SectionBean> mData = null;

    @BindView(R2.id.rv_list_content)
    RecyclerView mRecyclerView = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if (args != null) {
            mContentId = args.getInt(ARG_CONTENT_ID);
        }
    }

    /**
     * 创建实例
     *
     * @param contentId 分类的ID
     * @return
     */
    public static ContentDelegate newInstance(int contentId) {
        final Bundle args = new Bundle();
        args.putInt(ARG_CONTENT_ID, contentId);
        final ContentDelegate delegate = new ContentDelegate();
        delegate.setArguments(args);
        return delegate;
    }

    private void initRecyclerView() {
        RestClient.builder()
                .url("sort_content_list.php?contentId=" + mContentId)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        mData = new SectionDataConverter().convert(response);
                        final StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2,
                                StaggeredGridLayoutManager.VERTICAL);
                        mRecyclerView.setLayoutManager(manager);
                        final SectionAdapter sectionAdapter = new SectionAdapter(
                                R.layout.item_section_content,
                                R.layout.item_section_header,
                                mData);
                        mRecyclerView.setAdapter(sectionAdapter);

                    }
                })
                .build()
                .get();
    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_list_content;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initRecyclerView();
    }


}
