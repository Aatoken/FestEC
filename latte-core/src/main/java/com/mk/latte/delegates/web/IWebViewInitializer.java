package com.mk.latte.delegates.web;

import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * @author lenovo
 * @data 2017/11/9
 */

public interface IWebViewInitializer {

    /**
     * 初始化
     * @param webView
     * @return
     */
    WebView initWebView(WebView webView);

    /**
     * 本身浏览器的控制
     * @return
     */
    WebViewClient initWebViewClient();

    /**
     * 内部页面的控制
     * @return
     */
    WebChromeClient initWebChromeClient();

}
