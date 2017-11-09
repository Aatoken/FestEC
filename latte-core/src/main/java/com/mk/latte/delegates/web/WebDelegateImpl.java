package com.mk.latte.delegates.web;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mk.latte.delegates.IPageLoadListener;
import com.mk.latte.delegates.web.chromeclient.WebChromeClientImpl;
import com.mk.latte.delegates.web.client.WebViewClientImpl;
import com.mk.latte.delegates.web.route.RouteKeys;
import com.mk.latte.delegates.web.route.Router;

/**
 * @author lenovo
 * @data 2017/11/9
 */

public class WebDelegateImpl extends WebDelegate {

    private IPageLoadListener mIPageLoadListener = null;

    public void setPageLoadListener(IPageLoadListener listener) {
        this.mIPageLoadListener = listener;
    }

    public static WebDelegateImpl create(String url)
    {
        final Bundle args=new Bundle();
        args.putString(RouteKeys.URL.name(),url);
        final WebDelegateImpl delegateImpl=new WebDelegateImpl();
        delegateImpl.setArguments(args);
        return delegateImpl;
    }




    @Override
    public Object setLayout() {
        return getWebView();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        if(getUrl()!=null)
        {
            //用原生的方式模拟web 跳转并进行页面加载
            Router.getInstance().loadPage(this,getUrl());
        }
    }

    @Override
    public IWebViewInitializer setInitializer() {
        return this;
    }

    @Override
    public WebView initWebView(WebView webView) {
        return new WebViewInitializer().createWebView(webView);
    }

    @Override
    public WebViewClient initWebViewClient() {
        final WebViewClientImpl client=new WebViewClientImpl(this);
        client.setPageLoadListener(mIPageLoadListener);
        return client;
    }

    @Override
    public WebChromeClient initWebChromeClient() {
        return new WebChromeClientImpl();
    }
}
