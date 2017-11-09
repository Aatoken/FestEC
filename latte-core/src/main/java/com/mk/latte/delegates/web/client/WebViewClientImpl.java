package com.mk.latte.delegates.web.client;

import android.graphics.Bitmap;
import android.os.Handler;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mk.latte.app.ConfigKeys;
import com.mk.latte.app.Latte;
import com.mk.latte.delegates.IPageLoadListener;
import com.mk.latte.delegates.web.WebDelegate;
import com.mk.latte.delegates.web.route.Router;
import com.mk.latte.ui.loader.LatteLoader;
import com.mk.latte.util.storage.LattePreference;
import com.mk.latte.util.str.StrUtils;

/**
 * @author lenovo
 * @data 2017/11/9
 */

public class WebViewClientImpl extends WebViewClient {

    private final WebDelegate DELEGATE;
    private IPageLoadListener mIPageLoadListener = null;
    private static final Handler HANDLER = Latte.getHandler();

    public void setPageLoadListener(IPageLoadListener listener) {
        this.mIPageLoadListener = listener;
    }

    public WebViewClientImpl(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }


    /**
     * 过时的方法 由于适配器的问题
     *
     * @param view
     * @param url
     * @return
     */
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        return Router.getInstance().handleWebUrl(DELEGATE, url);
    }


    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);

        if (mIPageLoadListener != null) {
            mIPageLoadListener.onLoadStart();
        }
        LatteLoader.showLoading(view.getContext());
    }

    /**
     * 获取浏览器 cookie
     */
    private void syncCookie() {
        final CookieManager manager = CookieManager.getInstance();
        //注意，这里的Cookie和API请求的Cookie是不一样的，这个在网页不可见
        final String webHost = Latte.getConfigurationByKey(ConfigKeys.WEB_HOST.name());
        if (webHost != null) {
            if (manager.hasCookies()) {
                final String cookieStr = manager.getCookie(webHost);
                if (StrUtils.isEmpty(cookieStr)) {
                    LattePreference.addCustomAppProfile("cookie", cookieStr);
                }
            }

        }

    }


    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);

        syncCookie();

        if (mIPageLoadListener != null) {
            mIPageLoadListener.onLoadEnd();
        }

        HANDLER.postDelayed(new Runnable() {
            @Override
            public void run() {
                LatteLoader.stopLoading();
            }
        }, 1000);

    }


}
