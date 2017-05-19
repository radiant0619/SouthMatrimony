package com.southmatrimony.matrimony;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class MainActivity extends Activity {
    private AdView mAdView;
    private WebView mWebView;

    @Override
    protected void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    protected void onResume() {

        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    protected void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mAdView.loadAd(adRequest);

        mWebView = (WebView) findViewById(R.id.activity_main_webview);
        if (savedInstanceState != null) {
            mWebView.restoreState(savedInstanceState);
        } else {
            // Force links and redirects to open in the WebView instead of in a browser
            mWebView.setWebViewClient(new WebViewClient());

            // Enable Javascript
            WebSettings webSettings = mWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);

            // Use remote resource
            mWebView.loadUrl("http://southmatrimony.com");

            // Stop local links and redirects from opening in browser instead of WebView
            mWebView.setWebViewClient(new MyAppWebViewClient());

            // Use local resource
            // mWebView.loadUrl("file:///android_asset/www/index.html");}
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        mWebView.saveState(outState);
    }

    // Prevent the back-button from closing the app
    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }
}