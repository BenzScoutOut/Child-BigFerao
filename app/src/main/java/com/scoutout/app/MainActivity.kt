package com.scoutout.app

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.KeyEvent
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.scoutout.app.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    internal var doubleBackToExitPressedOnce = false;
    var isDashboard = false;
    internal lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.webView.webViewClient = object: WebViewClient() {

            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                return super.shouldOverrideUrlLoading(view, request)
            }

            override fun doUpdateVisitedHistory(view: WebView?, url: String?, isReload: Boolean) {
                Log.d("urlLoad", "Do Update : ${view!!.url} : $isReload")
                isDashboard = view!!.url.contains("/dashboard") || view!!.url.contains("/login")
                super.doUpdateVisitedHistory(view, url, isReload)


            }
        }
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.settings.domStorageEnabled = true
        binding.webView.loadUrl(resources.getString(R.string.employer_url))
    }

    override fun onBackPressed() {
        if (binding.webView.canGoBack()) {

            if(isDashboard){
                var dialog = Dialog(this)
                dialog.setContentView(R.layout.dialog_exit)
                var buttonConfirm = dialog.findViewById<Button>(R.id.buttonConfirm)
                buttonConfirm.setOnClickListener {
                   finish()
                }
                var buttonCancel = dialog.findViewById<Button>(R.id.buttonCancel)
                buttonCancel.setOnClickListener {
                    dialog.hide()
                }
                dialog.show()
            }else{
                binding.webView.goBack()
            }
        }
    }

}
