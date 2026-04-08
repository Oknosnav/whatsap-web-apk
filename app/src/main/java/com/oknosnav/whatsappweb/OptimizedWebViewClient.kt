package com.oknosnav.whatsappweb

import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient

class OptimizedWebViewClient : WebViewClient() {

    private val blockedDomains = setOf(
        "google-analytics.com",
        "analytics.google.com",
        "googletagmanager.com",
        "facebook.com",
        "ads.google.com",
        "doubleclick.net",
        "intercom.io"
    )

    override fun shouldInterceptRequest(
        view: WebView?,
        request: WebResourceRequest?
    ): WebResourceResponse? {
        val url = request?.url?.toString() ?: return null

        for (domain in blockedDomains) {
            if (url.contains(domain)) {
                return WebResourceResponse("text/plain", "utf-8", null)
            }
        }

        return super.shouldInterceptRequest(view, request)
    }

    override fun onPageStarted(view: WebView?, url: String?, favicon: android.graphics.Bitmap?) {
        super.onPageStarted(view, url, favicon)
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
    }
}
