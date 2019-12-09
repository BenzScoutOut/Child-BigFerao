package com.scoutout.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            //Do something after 100ms

            var gotoMain = Intent(this, MainActivity::class.java)
            startActivity(gotoMain)
            finish()
        }, 1000)
    }
}
