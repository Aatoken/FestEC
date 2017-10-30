package com.mk.latte.delegates.bottom;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.joanzapata.iconify.widget.IconTextView;
import com.mk.latte.R;
import com.mk.latte.R2;
import com.mk.latte.delegates.LatteDelegate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * @author lenovo
 * @data 2017/10/30
 */

public abstract class BaseBottomDelegate extends LatteDelegate
        implements View.OnClickListener {

    private final ArrayList<BottomTabBean> TAB_BEANS = new ArrayList<>();
    private final ArrayList<BottomItemDelegate> ITEM_DELEGATES = new ArrayList<>();
    private final LinkedHashMap<BottomTabBean, BottomItemDelegate> ITEMS = new LinkedHashMap<>();
    private int mCurrentDelegate = 0;
    /**
     * 设置点击 delegate 的索引
     */
    private int mIndexDelegate = 0;
    /**
     * 设置被点击后的颜色
     */
    private int mClickedColor = Color.RED;
    /**
     * 设置初始化的颜色
     */
    private int mInitColor = Color.GRAY;

    /**
     * 抽象方法
     *
     * @param builder
     * @return
     */
    public abstract LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder);

    /**
     * 设置初始界面索引
     *
     * @return
     */
    public abstract int setIndexDelegate();

    /**
     * 设置被点击后的颜色
     *
     * @return
     */
    @ColorInt
    public abstract int setClickedColor();

    /**
     * 设置初始化的颜色
     *
     * @return
     */
    public abstract int setInitColor();

    @BindView(R2.id.bottom_bar)
    LinearLayoutCompat mBottomBar = null;



    /**
     * 初始化数据
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mIndexDelegate = setIndexDelegate();
        if (setClickedColor() != 0) {
            mClickedColor = setClickedColor();
        }
        if (setIndexDelegate() != 0) {
            mInitColor = setIndexDelegate();
        }

        final ItemBuilder builder = ItemBuilder.builder();
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = setItems(builder);
        ITEMS.putAll(items);

        for (Map.Entry<BottomTabBean, BottomItemDelegate> item : ITEMS.entrySet()) {
            final BottomTabBean key = item.getKey();
            final BottomItemDelegate value = item.getValue();
            TAB_BEANS.add(key);
            ITEM_DELEGATES.add(value);
        }

    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_bottom;
    }

    /**
     * 绑定数据
     *
     * @param savedInstanceState
     * @param rootView
     */
    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        final int size = ITEMS.size();
        for (int i = 0; i < size; i++) {
            LayoutInflater.from(getContext()).inflate(R.layout.bottom_item_icon_text_layout,
                    mBottomBar);
            //获取第i个 mBottomBar 的 view
            final RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);
            //设置每个item的点击事件
            item.setTag(i);
            item.setOnClickListener(this);
            //获取 mBottomBar 内部控件
            final IconTextView itemIcon = (IconTextView) item.findViewById(R.id.icon_bottom_item);
            final AppCompatTextView itemTitle = (AppCompatTextView) item.findViewById(R.id
                    .tv_bottom_item);
            final BottomTabBean bean = TAB_BEANS.get(i);
            //绑定数据
            itemIcon.setText(bean.getIcon());
            itemTitle.setText(bean.getTitle());
            if (i == mIndexDelegate) {
                //设置点击后的颜色
                itemIcon.setTextColor(mClickedColor);
                itemTitle.setTextColor(mClickedColor);
            }
        }

        //获取索引的delegate
        final SupportFragment[] delegateArray = ITEM_DELEGATES.toArray(new SupportFragment[size]);

        loadMultipleRootFragment(R.id.bottom_bar_delegate_container, mIndexDelegate, delegateArray);


    }

    /**
     * 重置 点击的bar 内部控件的颜色
     */
    private void resetColor() {
        final int count = mBottomBar.getChildCount();
        for (int i = 0; i < count; i++) {
            final RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);
            final IconTextView itemIcon = (IconTextView) item.findViewById(R.id.icon_bottom_item);
            final AppCompatTextView itemTitle = (AppCompatTextView) item.findViewById(R.id
                    .tv_bottom_item);
            itemIcon.setTextColor(mInitColor);
            itemTitle.setTextColor(mInitColor);
        }

    }


    @Override
    public void onClick(View v) {
        final int tag = (int) v.getTag();
        //重置颜色
        resetColor();
        final RelativeLayout item = (RelativeLayout) v;
        final IconTextView itemIcon = (IconTextView) item.findViewById(R.id.icon_bottom_item);
        final AppCompatTextView itemTitle = (AppCompatTextView) item.findViewById(R.id
                .tv_bottom_item);
        itemIcon.setTextColor(mClickedColor);
        itemTitle.setTextColor(mClickedColor);
        showHideFragment(ITEM_DELEGATES.get(tag),ITEM_DELEGATES.get(mCurrentDelegate));
        mCurrentDelegate=tag;

    }


}



















