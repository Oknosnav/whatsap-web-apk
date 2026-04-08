package com.oknosnav.whatsappweb

import android.os.Build
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import android.webkit.CookieManager

class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView = findViewById(R.id.webView)
        progressBar = findViewById(R.id.progressBar)

        setupWebView()

        if (savedInstanceState == null) {
            webView.loadUrl("https://web.whatsapp.com")
        } else {
            webView.restoreState(savedInstanceState)
        }
    }

    private fun setupWebView() {
        webView.apply {
            settings.apply {
                javaScriptEnabled = true
                domStorageEnabled = true
                databaseEnabled = true
                useWideViewPort = true
                loadWithOverviewMode = true
                
                cacheMode = WebSettings.LOAD_DEFAULT
                setAppCachePath(cacheDir.absolutePath)
                setAppCacheEnabled(true)
                
                setSupportZoom(false)
                builtInZoomControls = false
                displayZoomControls = false
                
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                }
                
                userAgentString = "WhatsAppWeb-APK/1.0"
            }

            setLayerType(View.LAYER_TYPE_HARDWARE, null)
            webViewClient = OptimizedWebViewClient()

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                WebView.setWebContentsDebuggingEnabled(true)
            }

            val cookieManager = CookieManager.getInstance()
            cookieManager.setAcceptCookie(true)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                cookieManager.setAcceptThirdPartyCookies(this)
            }

            webChromeClient = OptimizedWebChromeClient(progressBar)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        webView.saveState(outState)
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        webView.destroy()
    }
}
