package com.wzdq.fengcai.module.mine.expand;

import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.wzdq.fengcai.R;
import com.wzdq.fengcai.base.BaseActivity;
import com.wzdq.fengcai.utils.WebBuilder;

public class PersonExpandActivity extends BaseActivity {

    @Override
    public int getContentViewRes() {
        return R.layout.activity_person_expand;
    }

    @Override
    public String getTitleContent() {
        return "市场拓展";
    }

    @Override
    public void onFinally(@Nullable Bundle savedInstanceState) {
        WebView mWebView = mViewManager.findView(R.id.webView);
        final ProgressBar mProgressBar = mViewManager.findView(R.id.progressBar);
        WebBuilder builder = new WebBuilder(mWebView);
        builder.setJavaScriptEnable(true)
                .addJavaScriptInterface(this,"fengcai")
                .setOnProgressChangeListener(new WebBuilder.OnProgressChangeListener() {
                    @Override
                    public void progressChange(int progress) {
                        mProgressBar.setProgress(progress);
                    }

                    @Override
                    public void showProgress() {
                        if (mProgressBar.getVisibility() == View.GONE){
                            mProgressBar.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void hideProgress() {
                        if (mProgressBar.getVisibility() == View.VISIBLE){
                            mProgressBar.setVisibility(View.GONE);
                        }
                    }
                }).builder();
        mWebView.loadUrl("http://www.wanzhuandiqiu.com/accounts/mien/market/index.html");
    }
}
