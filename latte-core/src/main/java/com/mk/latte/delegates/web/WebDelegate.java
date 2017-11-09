package com.mk.latte.delegates.web;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import com.mk.latte.app.ConfigKeys;
import com.mk.latte.app.Latte;
import com.mk.latte.delegates.LatteDelegate;
import com.mk.latte.delegates.web.route.RouteKeys;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * @author lenovo
 * @data 2017/11/9
 */

public abstract class WebDelegate extends LatteDelegate implements IWebViewInitializer {

    private WebView mWebView = null;
    private final ReferenceQueue<WebView> WEB_VIEW_QUENE = new ReferenceQueue<>();
    private String mUrl = null;
    private boolean mIsWebViewAbailable = false;
    private LatteDelegate mTopDelegate = null;


    public WebDelegate() {
    }

    /**
     * 初始化
     *
     * @return
     */
    public abstract IWebViewInitializer setInitializer();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        mUrl = args.getString(RouteKeys.URL.name());

        initWebView();
    }

    private void initWebView() {
        if (mWebView != null) {
            mWebView.removeAllViews();
            mWebView.destroy();
        } else {
            final IWebViewInitializer initializer = setInitializer();
            if (initializer != null) {
                final WeakReference<WebView> webViewWeakReference =
                        new WeakReference<>(new WebView(getContext()), WEB_VIEW_QUENE);
                mWebView = webViewWeakReference.get();
                mWebView = initializer.initWebView(mWebView);
                mWebView.setWebViewClient(initializer.initWebViewClient());
                mWebView.setWebChromeClient(initializer.initWebChromeClient());
                final String name = Latte.getConfigurationByKey(
                        ConfigKeys.JAVASCRIPT_INTERFACE.name());
                mWebView.addJavascriptInterface(LatteWebInterface.create(this), "latte");
                mIsWebViewAbailable = true;
            } else {
                throw new NullPointerException("initializer IS NULL");
            }
        }
    }


    public void setTopDelegate(LatteDelegate delegate) {
        mTopDelegate = delegate;
    }

    public LatteDelegate getTopDelegate() {
        if (mTopDelegate == null) {
            mTopDelegate = this;
        }
        return mTopDelegate;
    }

    public WebView getWebView() {
        if (mWebView == null) {
            throw new NullPointerException("webView is null");
        } else {
            return mIsWebViewAbailable ? mWebView : null;
        }
    }

    public String getUrl() {
        if (mUrl == null) {
            throw new NullPointerException("webView is null");
        } else {
            return mUrl;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mWebView != null) {
            mWebView.onPause();
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        if (mWebView != null) {
            mWebView.onResume();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mIsWebViewAbailable = false;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            mWebView.removeAllViews();
            mWebView.destroy();
            mWebView = null;
        }
    }


}

















