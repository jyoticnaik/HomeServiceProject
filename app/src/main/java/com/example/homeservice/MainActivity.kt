package com.example.homeservice

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.RelativeLayout

class MainActivity : AppCompatActivity() {

    internal lateinit var relativeLayout: RelativeLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        relativeLayout=findViewById(R.id.rlay)

        relativeLayout.setOnClickListener {
            val sharedIntent = Intent(this@MainActivity, MainPage::class.java)
            startActivity(sharedIntent)
        }

        Handler().postDelayed({
            val homeIntent = Intent(this@MainActivity, MainPage::class.java)
            startActivity(homeIntent)
            finish()
        }, SPLASH_TIME_OUT.toLong())
    }

    companion object {

        private val SPLASH_TIME_OUT = 4000
    }
}
