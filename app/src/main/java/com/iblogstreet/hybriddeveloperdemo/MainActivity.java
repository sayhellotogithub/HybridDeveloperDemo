package com.iblogstreet.hybriddeveloperdemo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity
        extends AppCompatActivity
{
    WebView mWebView;
    EditText mEturl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initEvent();
    }

    private void initEvent() {
        mWebView.setWebChromeClient(new WebChromeClient(){
            //处理加载进度
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            }
            //处理标题
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
            }
        });
        mWebView.setWebViewClient(new WebViewClient(){
            //页面加载完成
            @Override
            public void onPageFinished(WebView view, String url) {

            }
            //页面启动时
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }
        });
    }

    private void initData() {
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);

        JavaScriptMethods javaScriptMethods=new JavaScriptMethods(MainActivity.this,mWebView);
        //第一个参数是对象，第二个参数是对象的映射字符串，js是通过映射字符串来调用java中的方法的
        mWebView.addJavascriptInterface(javaScriptMethods,"javaInterface");
    }

    private void initView() {
        mWebView= (WebView) findViewById(R.id.webView);
        mEturl = (EditText) findViewById(R.id.et_url);
    }
    public void goToWeb(View view){
        String url=mEturl.getText().toString().trim();
        //从网络上加载
        //mWebView.loadUrl(url);
        //从本地加载，上线时用,加载速度更快
        mWebView.loadUrl("file:///android_asset/jQueryMobileDemo/JscommAndroid.html");

    }
    public void callJs(View view){
        //webview.loadUrl("javascript:方法名(参数)");
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("msg","let's conquer the world");
            jsonObject.put("name","hero");
        } catch (JSONException e) {
            e.printStackTrace();
        }


        mWebView.loadUrl("javascript:showMsg("+ jsonObject.toString()+")");

    }
}
