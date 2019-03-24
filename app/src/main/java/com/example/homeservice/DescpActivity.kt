package com.example.homeservice

import android.annotation.SuppressLint
import android.content.Intent
import android.icu.text.LocaleDisplayNames
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.homeservice.R
import org.w3c.dom.Text

abstract class DescpActivity : AppCompatActivity(), View.OnClickListener {

    private var proceed_btn: Button?=null
    private lateinit var head: String
    private lateinit var  desc: String
    private lateinit var  price: String

    @SuppressLint("LongLogTag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_descp)

        proceed_btn = this.findViewById(R.id.submit_btn)
        proceed_btn!!.setOnClickListener(this)
        Log.d(TAG, "onCreate strated")

        getIncomingIntent()
    }

    @SuppressLint("LongLogTag")
    private fun getIncomingIntent() {
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.")
        if (intent.hasExtra("desc_head")) {
            Log.d(TAG, "getIncomingIntent: found intent extras")

            head = intent.getStringExtra("desc_head")

            setHead(head)
        }

        //Declared in SecondPage class, sent extra through RecyclerView class
        if(intent.hasExtra("desc")){
            Log.d(TAG,"getIncomingIntent: found intent extras description")

            desc=intent.getStringExtra("desc")

            setDesc(desc)
        }

        if(intent.hasExtra("price")){
            Log.d(TAG,"getIncomingIntent: found intent extras price")

            price=intent.getStringExtra("price")

            setPrice(price)
        }

    }

    @SuppressLint("LongLogTag")
    private fun setPrice(price:String){
        Log.d(TAG, "setting price")
        val page_price = findViewById<TextView>(R.id.serv_price)
        page_price.text = price
    }

    @SuppressLint("LongLogTag")
    private fun setDesc(desc: String){
        Log.d(TAG, "setting description")
        val page_desc = findViewById<TextView>(R.id.Description)
        page_desc.text = desc
    }

    @SuppressLint("LongLogTag")
    private fun setHead(deschead: String) {
        Log.d(TAG, "setting head")
        val page_head = findViewById<TextView>(R.id.desc_name)
        page_head.text = deschead
    }

    override fun onClick(v: View?) {
        //TODO:ADD FUNCTIONS
        val intent = Intent(this, PopupActivity::class.java)
        intent.putExtra("service_name", head)
        startActivity(intent)
    }

    companion object {
        private val TAG = "com.example.homeservice.DescpActivity"
    }
}
