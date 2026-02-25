package com.discordmulti;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<WebView> webViews = new ArrayList<>();
    private List<View> tabButtons = new ArrayList<>();
    private int currentTab = 0;
    private static final int ACCOUNT_COUNT = 5;
    private LinearLayout webViewContainer;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webViewContainer = findViewById(R.id.webview_container);
        LinearLayout tabBar = findViewById(R.id.tab_bar);

        String[] accountLabels = {"Acc 1", "Acc 2", "Acc 3", "Acc 4", "Acc 5"};
        String[] accountEmojis = {"👤", "👥", "🧑", "🙋", "😎"};

        // Create WebViews and tabs
        for (int i = 0; i < ACCOUNT_COUNT; i++) {
            // Create isolated WebView for each account
            WebView webView = new WebView(this);
            WebSettings settings = webView.getSettings();
            settings.setJavaScriptEnabled(true);
            settings.setDomStorageEnabled(true);
            settings.setLoadWithOverviewMode(true);
            settings.setUseWideViewPort(true);
            settings.setBuiltInZoomControls(false);
            settings.setDisplayZoomControls(false);
            settings.setSupportZoom(true);
            settings.setMediaPlaybackRequiresUserGesture(false);
            settings.setUserAgentString(
                "Mozilla/5.0 (Linux; Android 11; Samsung Galaxy) " +
                "AppleWebKit/537.36 (KHTML, like Gecko) " +
                "Chrome/108.0.0.0 Mobile Safari/537.36"
            );

            webView.setWebViewClient(new WebViewClient());

            // Each WebView needs separate cookie storage — use private browsing trick
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.setAcceptCookie(true);
            cookieManager.setAcceptThirdPartyCookies(webView, true);

            webView.loadUrl("https://discord.com/app");

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            );
            webView.setLayoutParams(params);
            webView.setVisibility(i == 0 ? View.VISIBLE : View.GONE);
            webViewContainer.addView(webView);
            webViews.add(webView);

            // Create tab button
            LinearLayout tabBtn = new LinearLayout(this);
            tabBtn.setOrientation(LinearLayout.VERTICAL);
            tabBtn.setGravity(android.view.Gravity.CENTER);
            LinearLayout.LayoutParams tabParams = new LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.MATCH_PARENT, 1
            );
            tabBtn.setLayoutParams(tabParams);
            tabBtn.setBackgroundColor(i == 0 ? 0xFF5865F2 : 0xFF2C2F33);
            tabBtn.setPadding(4, 8, 4, 8);

            TextView emoji = new TextView(this);
            emoji.setText(accountEmojis[i]);
            emoji.setTextSize(18);
            emoji.setGravity(android.view.Gravity.CENTER);

            TextView label = new TextView(this);
            label.setText(accountLabels[i]);
            label.setTextColor(0xFFFFFFFF);
            label.setTextSize(10);
            label.setGravity(android.view.Gravity.CENTER);

            tabBtn.addView(emoji);
            tabBtn.addView(label);

            final int tabIndex = i;
            tabBtn.setOnClickListener(v -> switchTab(tabIndex));

            tabBar.addView(tabBtn);
            tabButtons.add(tabBtn);
        }
    }

    private void switchTab(int index) {
        // Hide current
        webViews.get(currentTab).setVisibility(View.GONE);
        tabButtons.get(currentTab).setBackgroundColor(0xFF2C2F33);

        // Show new
        currentTab = index;
        webViews.get(currentTab).setVisibility(View.VISIBLE);
        tabButtons.get(currentTab).setBackgroundColor(0xFF5865F2);
    }

    @Override
    public void onBackPressed() {
        WebView current = webViews.get(currentTab);
        if (current.canGoBack()) {
            current.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
