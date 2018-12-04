package com.wzdq.fengcai.module.mine.setting;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.wzdq.fengcai.R;
import com.wzdq.fengcai.base.BaseActivity;
import com.wzdq.fengcai.utils.WebBuilder;

public class AboutUsActivity extends BaseActivity {

    @Override
    public int getContentViewRes() {
        return R.layout.activity_about_us;
    }

    @Override
    public String getTitleContent() {
        return "关于我们";
    }

    @Override
    public void onFinally(@Nullable Bundle savedInstanceState) {
        WebView webView = mViewManager.findView(R.id.webView);
        final ProgressBar progressBar = mViewManager.findView(R.id.progressBar);

        WebBuilder builder = new WebBuilder(webView);
        builder.setJavaScriptEnable(true)
                .setOnProgressChangeListener(new WebBuilder.OnProgressChangeListener() {
                    @Override
                    public void progressChange(int progress) {
                        progressBar.setProgress(progress);
                    }

                    @Override
                    public void showProgress() {
                        progressBar.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void hideProgress() {
                        progressBar.setVisibility(View.GONE);
                    }
                }).builder();

        webView.loadUrl("http://www.wanzhuandiqiu.com/accounts/mien/About/index.html");
    }
}
