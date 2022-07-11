package com.example.readnewrss;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    private TextView txtback;
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        webView = (WebView) findViewById(R.id.webViewTinTuc);
        txtback = (TextView) findViewById(R.id.txtBack);
        Intent intent = getIntent();

        // lấy link để hiển thị dưới dạng webView
        String link = intent.getStringExtra("linkBai");
        webView.loadUrl(link);
        webView.setWebViewClient(new WebViewClient());

        txtback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}