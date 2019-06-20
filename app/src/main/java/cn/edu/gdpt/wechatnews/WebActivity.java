package cn.edu.gdpt.wechatnews;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class WebActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private WebView web;
    private ProgressBar pppp;

    public static String url = "https://gdpmom.cn/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        initView();
        getWindow().setStatusBarColor(0xffFA7399);
        toolbar.setNavigationIcon(R.mipmap.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        web.getSettings().setJavaScriptEnabled(true);
        web.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                pppp.setProgress(newProgress);
                super.onProgressChanged(view, newProgress);
            }
        });

        web.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return true;
            }

        });

        web.loadUrl(url);
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        web = (WebView) findViewById(R.id.web);
        pppp = (ProgressBar) findViewById(R.id.pppp);
        //    pppp.setOnClickListener(this);
    }
}
