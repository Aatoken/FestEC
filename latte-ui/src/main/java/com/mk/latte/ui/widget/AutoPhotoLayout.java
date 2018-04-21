package com.mk.latte.ui.widget;


import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;

/**
 * Created by Aatoken on 2018/4/21.
 */

public class AutoPhotoLayout extends LinearLayoutManager {
    public AutoPhotoLayout(Context context) {
        super(context);
    }

    public AutoPhotoLayout(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public AutoPhotoLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
