package com.oknosnav.whatsappweb

import android.widget.ProgressBar
import android.webkit.WebChromeClient
import android.webkit.WebView

class OptimizedWebChromeClient(private val progressBar: ProgressBar) : WebChromeClient() {

    override fun onProgressChanged(view: WebView?, newProgress: Int) {
        super.onProgressChanged(view, newProgress)
        
        progressBar.progress = newProgress
        
        if (newProgress < 100) {
            progressBar.visibility = ProgressBar.VISIBLE
        } else {
            progressBar.visibility = ProgressBar.GONE
        }
    }
}
