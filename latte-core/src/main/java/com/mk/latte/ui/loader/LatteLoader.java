package com.mk.latte.ui.loader;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.mk.latte.R;
import com.mk.latte.util.dimen.DimenUtil;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

/**
 * @author lenovo
 * @data 2017/10/25
 */

public class LatteLoader {

    /**
     * 宽高比
     */
    private static final int LOAD_SIZE_SCALE = 8;
    /**
     * 屏幕的上下偏移量
     */
    private static final int LOAD_OFFSET_SCALE = 10;
    /**
     * dialog 集合
     */
    private static final ArrayList<AppCompatDialog> LOADERS = new ArrayList<>();
    /**
     * 设置默认dialog的样式
     */
    private static final String DEFAULT_LOADER = LoaderStyle.BallClipRotatePulseIndicator.name();


    public static void showLoading(Context context, Enum<LoaderStyle> type) {
        LatteLoader.showLoading(context, type.name());
    }

    /**
     * 初始化 一个 dialog
     *
     * @param context 上下文
     * @param type    dialog的样式
     */
    public static void showLoading(Context context, String type) {

        final AppCompatDialog dialog = new AppCompatDialog(context, R.style.dialog);

        final AVLoadingIndicatorView avLoadingIndicatorView = LoaderCreator.create(context, type);

        dialog.setContentView(avLoadingIndicatorView);

        int deviceWidth = DimenUtil.getScreenWidth();
        int deviceHeight = DimenUtil.getScreenHeight();

        final Window dialogWindow = dialog.getWindow();

        if (dialogWindow != null) {
            final WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.width = deviceWidth / LOAD_SIZE_SCALE;
            lp.height = deviceHeight / LOAD_SIZE_SCALE;
            lp.height = lp.height + deviceHeight / LOAD_OFFSET_SCALE;
            lp.gravity = Gravity.CENTER;
        }
        //添加到集合中去统一进行处理
        LOADERS.add(dialog);
        dialog.show();
    }

    /**
     * 重载 调用
     *
     * @param context 上下文
     */
    public static void showLoading(Context context) {
        showLoading(context, DEFAULT_LOADER);
    }

    /**
     * 关闭所有的 dialog
     */
    public static void stopLoading() {
        for (AppCompatDialog dialog : LOADERS) {
            if (dialog != null) {
                if (dialog.isShowing()) {
                    //会执行一些回调
                    dialog.cancel();
                    //不会执行回调
                    //dialog.dismiss();
                }
            }
        }
    }


}
