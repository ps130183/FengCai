package com.wzdq.fengcai.utils;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by PengSong on 18/11/30.
 */

public class WebBuilder {

    private WebView webView;

    private boolean javaScriptEnable;
    private Object javaScriptInterface;
    private String javaScriptRname;

    public WebBuilder(WebView webView) {
        this.webView = webView;
    }

    public WebBuilder setJavaScriptEnable(boolean enable) {
        this.javaScriptEnable = enable;
        return this;
    }

    public WebBuilder addJavaScriptInterface(Object obj, String name) {
        javaScriptInterface = obj;
        javaScriptRname = name;
        return this;
    }

    @SuppressLint("JavascriptInterface")
    public void builder() {
        if (webView == null) {
            return;
        }

//        WebSettings settings = webView.getSettings();
        setUpWebViewDefaults(webView);

        if (javaScriptInterface != null) {
            webView.addJavascriptInterface(javaScriptInterface, javaScriptRname);
        }

        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (onProgressChangeListener != null && newProgress <= 90){
                    onProgressChangeListener.progressChange(newProgress);
                }
            }
        });

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                // 加载某些网站的时候会报:ERR_CONNECTION_REFUSED,因此需要在这里取消进度条的显示
                if (onProgressChangeListener != null){
                    onProgressChangeListener.hideProgress();
                }
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                if (onProgressChangeListener != null){
                    onProgressChangeListener.showProgress();
                }
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (onProgressChangeListener != null){
                    onProgressChangeListener.hideProgress();
                }
            }
        });

    }

    public interface OnProgressChangeListener{
        void progressChange(int progress);
        void showProgress();
        void hideProgress();
    }

    private OnProgressChangeListener onProgressChangeListener;

    public WebBuilder setOnProgressChangeListener(OnProgressChangeListener onProgressChangeListener) {
        this.onProgressChangeListener = onProgressChangeListener;
        return this;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void setUpWebViewDefaults(WebView webView) {
        WebSettings settings = webView.getSettings();

        // Enable Javascript
        settings.setJavaScriptEnabled(javaScriptEnable);

        // Use WideViewport and Zoom out if there is no viewport defined
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);

        // Enable pinch to zoom without the zoom buttons
        settings.setBuiltInZoomControls(false);

        // Allow use of Local Storage
        settings.setDomStorageEnabled(true);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB) {
            // Hide the zoom controls for HONEYCOMB+
            settings.setDisplayZoomControls(false);
        }

        // Enable remote debugging via chrome://inspect
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }

    }


}
