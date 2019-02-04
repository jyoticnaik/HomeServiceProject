package com.example.homeservice

import android.annotation.SuppressLint
import android.icu.text.LocaleDisplayNames
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.homeservice.R
import org.w3c.dom.Text

class DescpActivity : AppCompatActivity() {

    @SuppressLint("LongLogTag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_descp)

        Log.d(TAG, "onCreate strated")

        getIncomingIntent()
    }

    @SuppressLint("LongLogTag")
    private fun getIncomingIntent() {
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.")
        if (intent.hasExtra("desc_head")) {
            Log.d(TAG, "getIncomingIntent: found intent extras")

            val head = intent.getStringExtra("desc_head")

            setHead(head)
        }
    }

    @SuppressLint("LongLogTag")
    private fun setHead(deschead: String) {
        Log.d(TAG, "setting head")
        val page_head = findViewById<TextView>(R.id.desc_name)
        page_head.text = deschead
    }

    companion object {
        private val TAG = "com.example.homeservice.DescpActivity"
    }
}
