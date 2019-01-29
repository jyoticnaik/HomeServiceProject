package com.example.homeservice

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import java.util.ArrayList

class HomeCleaningPage : AppCompatActivity() {

    //vars
    private val mNames = ArrayList<String>()
    private val mImageUrl = ArrayList<Int>()
    //private val mImageUrl = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_cleaning_page)

        getIncomingIntent()
    }

    private fun getIncomingIntent() {
        if (intent.hasExtra("CardSelected")) {
            val card = intent.getStringExtra("CardSelected")
            when (card) {
                "homecv" -> initImageBitmaps("homecv")
                "pestcv" -> initImageBitmaps("pestcv")
                "paintcv" -> initImageBitmaps("paintcv")
                "skincv" -> initImageBitmaps("skincv")
                "haircv" -> initImageBitmaps("haircv")
                "makeupcv" -> initImageBitmaps("makeupcv")
            }
        }
    }

    private fun initImageBitmaps(s: String) {
        Log.d(TAG, "initImageBitmaps: prparing bitmaps.")

         if (s == "homecv") {
            mImageUrl.add(R.drawable.deepclean)
            mNames.add("Deep Home Cleaning")

            mImageUrl.add(R.drawable.carpetcleaning)
            mNames.add("Furniture Polishing & Cleaning")

            mImageUrl.add(R.drawable.janitor)
            mNames.add("Manual Cleaning")

            mImageUrl.add(R.drawable.vacuumcleaner)
            mNames.add("Floor Scrubbing")

            Log.d(TAG,"Home Service if structure")

            initRecycleView()
        } else if (s == "pestcv") {
            mImageUrl.add(R.drawable.insect)
            mNames.add("Biological Pest Control")

            mImageUrl.add(R.drawable.insecticide)
            mNames.add("Chemical Pest Control")

            mImageUrl.add(R.drawable.mortarandpestle)
            mNames.add("Herbal Pest Control")

            initRecycleView()
        } else if (s == "paintcv") {
            mImageUrl.add(R.drawable.mattepaint)
            mNames.add("Matte Paint")

            mImageUrl.add(R.drawable.matteenamel)
            mNames.add("Matte Enamel")

             mImageUrl.add(R.drawable.paint_satin)
             mNames.add("Satin Finish")

             mImageUrl.add(R.drawable.paint_semigloss)
             mNames.add("Semi-gloss Finish")

             mImageUrl.add(R.drawable.paint_gloss)
             mNames.add("gloss_paint Paint")

            initRecycleView()
        } else if (s == "skincv") {
            mImageUrl.add(R.drawable.dry_skin)
            mNames.add("Dry Skin")

            mImageUrl.add(R.drawable.sensitive_skin)
             mNames.add("Sensitive Skin")

             mImageUrl.add(R.drawable.oily_skin)
             mNames.add("Oil Skin")

            initRecycleView()
        } else if (s == "haircv") {
            mImageUrl.add(R.drawable.haircutting)
            mNames.add("Customized Cut")

            mImageUrl.add(R.drawable.hairdryer)
            mNames.add("Shampoo & Blow Dry")

             mImageUrl.add(R.drawable.hairhighlights)
             mNames.add("Personalized Color & Highlights")

             mImageUrl.add(R.drawable.formalstyling)
             mNames.add("Formal Styling")

             mImageUrl.add(R.drawable.bridal)
             mNames.add("Bridal & Wedding Hairstyles")

             mImageUrl.add(R.drawable.hairextensiond)
             mNames.add("Extensions")

             mImageUrl.add(R.drawable.hairsmoothning)
             mNames.add("Hair Smoothing System")

            initRecycleView()
        } else if (s == "makeupcv") {
            mImageUrl.add(R.drawable.lipgloss)
            mNames.add("Lips")

            mImageUrl.add(R.drawable.eyeshadow)
            mNames.add("Eyes")

             mImageUrl.add(R.drawable.foundation)
             mNames.add("Face Contouring")

             mImageUrl.add(R.drawable.customised)
             mNames.add("Customized Look")

             mImageUrl.add(R.drawable.mascara)
             mNames.add("False Lash Application")

            initRecycleView()
        } else {
            Toast.makeText(this, "Error", Toast.LENGTH_LONG)
        }
    }

    private fun initRecycleView() {

        try{
        Log.d(TAG, "initRecycleView: init recycleview.")
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val adapter = RecyclerViewAdapter(mNames,mImageUrl,this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)}
        catch (e: Exception){
            Log.d(TAG,"Exception occured: "+e.localizedMessage)
        }
    }

    companion object {

        private val TAG = "HC"
    }
}
