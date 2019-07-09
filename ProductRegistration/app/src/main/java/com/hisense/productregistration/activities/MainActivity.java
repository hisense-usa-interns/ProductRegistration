package com.hisense.productregistration.activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.hisense.productregistration.R;
import com.hisense.productregistration.deviceInfo.DataGetter;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    public Context context = MainActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webview = new WebView(this);
        setContentView(webview);
        webview.loadUrl("https://www.google.com/");

        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);

        DataGetter dataGetter = new DataGetter(context);
        String serial = dataGetter.retrieveSerialNumber();
        String model = dataGetter.retrieveModel();

        

    }
}
