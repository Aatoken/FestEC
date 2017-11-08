package com.mk.latte.ec.main.index;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;

import com.mk.latte.ec.R;

/**
 * 沉浸式状态栏
 *
 * @author lenovo
 * @data 2017/10/31
 */

public class TranslucentBehavior extends CoordinatorLayout.Behavior<Toolbar> {

    /**
     *  顶部距离
     */
    private int mDistanceY=0;
    /**
     * 颜色变化速度
     */
    private static final int SHOW_SPEED=3;


    /**
     * 必须要有的
     * @param context
     * @param attrs
     */
    public TranslucentBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, Toolbar child, View dependency) {
        return  dependency.getId()== R.id.rv_index;
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, Toolbar child, View
            directTargetChild, View target, int nestedScrollAxes) {
        return  true;
    }


    /**
     * 处理具体的逻辑
     * @param coordinatorLayout
     * @param child
     * @param target
     */
    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, Toolbar child, View
            target) {
        super.onStopNestedScroll(coordinatorLayout, child, target);
    }





}
















