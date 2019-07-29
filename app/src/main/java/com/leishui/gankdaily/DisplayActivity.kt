package com.leishui.gankdaily

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_display.*

class DisplayActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)
        var url = this.intent.getStringExtra("url")
        webview.loadUrl(url)
        webview.webChromeClient = object :WebChromeClient(){ override fun onProgressChanged(view: WebView?, newProgress: Int) {
            myProgressBar.progress = newProgress
        }
        }
    }
}
