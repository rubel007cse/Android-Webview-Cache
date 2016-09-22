package com.mrubel.wbt;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    WebView wb;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wb = (WebView) findViewById(R.id.mywb);
        wb.setWebViewClient(new MyBrowser());

        wb.getSettings().setAppCacheMaxSize( 5 * 1024 * 1024 );  
        wb.getSettings().setAppCachePath( getApplicationContext().getCacheDir().getAbsolutePath() );
        wb.getSettings().setAllowFileAccess( true );
        wb.getSettings().setAppCacheEnabled( true );
        wb.getSettings().setJavaScriptEnabled( true );
        wb.getSettings().setCacheMode( WebSettings.LOAD_DEFAULT );

        if ( !isNetworkAvailable() ) { // loading offline
            wb.getSettings().setCacheMode( WebSettings.LOAD_CACHE_ELSE_NETWORK );
        }

        wb.loadUrl( "http://www.mrubel.com" );


    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService( CONNECTIVITY_SERVICE );
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

}
