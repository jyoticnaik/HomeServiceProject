package com.example.homeservice

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.ViewFlipper

class MainPage : AppCompatActivity() {

    internal var v_fliper: ViewFlipper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        val images = intArrayOf(
            R.drawable.painting,
            R.drawable.homecleaning,
            R.drawable.pestcontrol,
            R.drawable.skincare,
            R.drawable.haircare
        )
        v_fliper = findViewById(R.id.flippervw)

        for (image in images) {
            flipperImages(image)
        }
    }

    fun flipperImages(image: Int) {
        val imageView = ImageView(this)
        imageView.setBackgroundResource(image)

        v_fliper?.addView(imageView)
        v_fliper?.setFlipInterval(4000)
        v_fliper?.isAutoStart = true

        //animation
        v_fliper?.setInAnimation(this, android.R.anim.slide_in_left)
        v_fliper?.setOutAnimation(this, android.R.anim.slide_out_right)
    }
}
