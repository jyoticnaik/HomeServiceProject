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

    //Description for each service
    private val mDesc=ArrayList<String>()

    private val mPrice=ArrayList<String>()

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
             mDesc.add("1.Complete Deep Cleaning of all areas of the house including Bathroom,Kitchen,Dining Room,Bedroom and Balcony.\n" +
                     "2.Deep Cleaning and cleaning of curtains,sofa set and chairs using Professional grade cleaning solution.\n" +
                     "3.Cleaning equipment and vacuum cleaners by proffessionals cleaning staff.\n" +
                     "4.Deep Cleaning of external surfaces,cabinets and appliance exteriors, and removal of grease and oil stains.\n" +
                     "5.Deep cleaning of floor, WC, seat, sink, fittings and walls.\n" +
                     "6.Deep cleaning of floor, windows, furniture and light fittings.\n" +
                     "7.Deep cleaning grill work and windows.\n" +
                     "8.Dry vacuum cleaning of sofa, carpent, and curtains.\n" +
                     "9.Cleaning of fans,lights,windows,railing,cabinets and switchboards.")
             mPrice.add("1RK - Rs 2500\n" +
                     "1BHK – Rs 3000\n" +
                     "2BHK – RS 4000\n" +
                     "3BHK – RS 5000\n" +
                     "4BHK – RS 6000\n" +
                     "5 BHK – RS 7000\n")

            mImageUrl.add(R.drawable.carpetcleaning)
            mNames.add("Furniture Polishing & Cleaning")
             mDesc.add(R.string.exp.toString())
             mPrice.add("1RK - Rs 2500" +
                     "1BHK – Rs 3000" +
                     "2BHK – RS 4000" +
                     "3BHK – RS 5000" +
                     "4BHK – RS 6000" +
                     "5 BHK – RS 7000")


            mImageUrl.add(R.drawable.janitor)
            mNames.add("Manual Cleaning")
             mDesc.add(R.string.exp.toString())
             mPrice.add("1RK - Rs 2500" +
                     "1BHK – Rs 3000" +
                     "2BHK – RS 4000" +
                     "3BHK – RS 5000" +
                     "4BHK – RS 6000" +
                     "5 BHK – RS 7000")

            mImageUrl.add(R.drawable.vacuumcleaner)
            mNames.add("Floor Scrubbing")
             mDesc.add(R.string.exp.toString())
             mPrice.add("1RK - Rs 2500" +
                     "1BHK – Rs 3000" +
                     "2BHK – RS 4000" +
                     "3BHK – RS 5000" +
                     "4BHK – RS 6000" +
                     "5 BHK – RS 7000")

            Log.d(TAG,"Home Service if structure")

            initRecycleView()
        } else if (s == "pestcv") {
            mImageUrl.add(R.drawable.insect)
            mNames.add("Biological Pest Control")
             mDesc.add(R.string.exp.toString())
             mPrice.add("1RK - Rs 2500" +
                     "1BHK – Rs 3000" +
                     "2BHK – RS 4000" +
                     "3BHK – RS 5000" +
                     "4BHK – RS 6000" +
                     "5 BHK – RS 7000")

            mImageUrl.add(R.drawable.insecticide)
            mNames.add("Chemical Pest Control")
             mDesc.add(R.string.exp.toString())
             mPrice.add("1RK - Rs 2500" +
                     "1BHK – Rs 3000" +
                     "2BHK – RS 4000" +
                     "3BHK – RS 5000" +
                     "4BHK – RS 6000" +
                     "5 BHK – RS 7000")

            mImageUrl.add(R.drawable.mortarandpestle)
            mNames.add("Herbal Pest Control")
             mDesc.add(R.string.exp.toString())
             mPrice.add("1RK - Rs 2500" +
                     "1BHK – Rs 3000" +
                     "2BHK – RS 4000" +
                     "3BHK – RS 5000" +
                     "4BHK – RS 6000" +
                     "5 BHK – RS 7000")

            initRecycleView()
        } else if (s == "paintcv") {
            mImageUrl.add(R.drawable.mattepaint)
            mNames.add("Matte Paint")
             mDesc.add(R.string.exp.toString())
             mPrice.add("1RK - Rs 2500" +
                     "1BHK – Rs 3000" +
                     "2BHK – RS 4000" +
                     "3BHK – RS 5000" +
                     "4BHK – RS 6000" +
                     "5 BHK – RS 7000")

            mImageUrl.add(R.drawable.matteenamel)
            mNames.add("Matte Enamel")
             mDesc.add(R.string.exp.toString())
             mPrice.add("1RK - Rs 2500" +
                     "1BHK – Rs 3000" +
                     "2BHK – RS 4000" +
                     "3BHK – RS 5000" +
                     "4BHK – RS 6000" +
                     "5 BHK – RS 7000")

             mImageUrl.add(R.drawable.paint_satin)
             mNames.add("Satin Finish")
             mDesc.add(R.string.exp.toString())
             mPrice.add("1RK - Rs 2500" +
                     "1BHK – Rs 3000" +
                     "2BHK – RS 4000" +
                     "3BHK – RS 5000" +
                     "4BHK – RS 6000" +
                     "5 BHK – RS 7000")

             mImageUrl.add(R.drawable.paint_semigloss)
             mNames.add("Semi-gloss Finish")
             mDesc.add(R.string.exp.toString())
             mPrice.add("1RK - Rs 2500" +
                     "1BHK – Rs 3000" +
                     "2BHK – RS 4000" +
                     "3BHK – RS 5000" +
                     "4BHK – RS 6000" +
                     "5 BHK – RS 7000")

             mImageUrl.add(R.drawable.paint_gloss)
             mNames.add("gloss_paint Paint")
             mDesc.add(R.string.exp.toString())
             mPrice.add("1RK - Rs 2500" +
                     "1BHK – Rs 3000" +
                     "2BHK – RS 4000" +
                     "3BHK – RS 5000" +
                     "4BHK – RS 6000" +
                     "5 BHK – RS 7000")

            initRecycleView()
        } else if (s == "skincv") {
            mImageUrl.add(R.drawable.dry_skin)
            mNames.add("Dry Skin")
             mDesc.add(R.string.exp.toString())
             mPrice.add("1RK - Rs 2500" +
                     "1BHK – Rs 3000" +
                     "2BHK – RS 4000" +
                     "3BHK – RS 5000" +
                     "4BHK – RS 6000" +
                     "5 BHK – RS 7000")

            mImageUrl.add(R.drawable.sensitive_skin)
             mNames.add("Sensitive Skin")
             mDesc.add(R.string.exp.toString())
             mPrice.add("1RK - Rs 2500" +
                     "1BHK – Rs 3000" +
                     "2BHK – RS 4000" +
                     "3BHK – RS 5000" +
                     "4BHK – RS 6000" +
                     "5 BHK – RS 7000")

             mImageUrl.add(R.drawable.oily_skin)
             mNames.add("Oil Skin")
             mDesc.add(R.string.exp.toString())
             mPrice.add("1RK - Rs 2500" +
                     "1BHK – Rs 3000" +
                     "2BHK – RS 4000" +
                     "3BHK – RS 5000" +
                     "4BHK – RS 6000" +
                     "5 BHK – RS 7000")

            initRecycleView()
        } else if (s == "haircv") {
            mImageUrl.add(R.drawable.haircutting)
            mNames.add("Customized Cut")
             mDesc.add(R.string.exp.toString())
             mPrice.add("1RK - Rs 2500" +
                     "1BHK – Rs 3000" +
                     "2BHK – RS 4000" +
                     "3BHK – RS 5000" +
                     "4BHK – RS 6000" +
                     "5 BHK – RS 7000")

            mImageUrl.add(R.drawable.hairdryer)
            mNames.add("Shampoo & Blow Dry")
             mDesc.add(R.string.exp.toString())
             mPrice.add("1RK - Rs 2500" +
                     "1BHK – Rs 3000" +
                     "2BHK – RS 4000" +
                     "3BHK – RS 5000" +
                     "4BHK – RS 6000" +
                     "5 BHK – RS 7000")

             mImageUrl.add(R.drawable.hairhighlights)
             mNames.add("Personalized Color & Highlights")
             mDesc.add(R.string.exp.toString())
             mPrice.add("1RK - Rs 2500" +
                     "1BHK – Rs 3000" +
                     "2BHK – RS 4000" +
                     "3BHK – RS 5000" +
                     "4BHK – RS 6000" +
                     "5 BHK – RS 7000")

             mImageUrl.add(R.drawable.formalstyling)
             mNames.add("Formal Styling")
             mDesc.add(R.string.exp.toString())
             mPrice.add("1RK - Rs 2500" +
                     "1BHK – Rs 3000" +
                     "2BHK – RS 4000" +
                     "3BHK – RS 5000" +
                     "4BHK – RS 6000" +
                     "5 BHK – RS 7000")

             mImageUrl.add(R.drawable.bridal)
             mNames.add("Bridal & Wedding Hairstyles")
             mDesc.add(R.string.exp.toString())
             mPrice.add("1RK - Rs 2500" +
                     "1BHK – Rs 3000" +
                     "2BHK – RS 4000" +
                     "3BHK – RS 5000" +
                     "4BHK – RS 6000" +
                     "5 BHK – RS 7000")

             mImageUrl.add(R.drawable.hairextensiond)
             mNames.add("Extensions")
             mDesc.add(R.string.exp.toString())
             mPrice.add("1RK - Rs 2500" +
                     "1BHK – Rs 3000" +
                     "2BHK – RS 4000" +
                     "3BHK – RS 5000" +
                     "4BHK – RS 6000" +
                     "5 BHK – RS 7000")

             mImageUrl.add(R.drawable.hairsmoothning)
             mNames.add("Hair Smoothing System")
             mDesc.add(R.string.exp.toString())
             mPrice.add("1RK - Rs 2500" +
                     "1BHK – Rs 3000" +
                     "2BHK – RS 4000" +
                     "3BHK – RS 5000" +
                     "4BHK – RS 6000" +
                     "5 BHK – RS 7000")

            initRecycleView()
        } else if (s == "makeupcv") {
            mImageUrl.add(R.drawable.lipgloss)
            mNames.add("Lips")
             mDesc.add(R.string.exp.toString())
             mPrice.add("1RK - Rs 2500" +
                     "1BHK – Rs 3000" +
                     "2BHK – RS 4000" +
                     "3BHK – RS 5000" +
                     "4BHK – RS 6000" +
                     "5 BHK – RS 7000")

            mImageUrl.add(R.drawable.eyeshadow)
            mNames.add("Eyes")
             mDesc.add(R.string.exp.toString())
             mPrice.add("1RK - Rs 2500" +
                     "1BHK – Rs 3000" +
                     "2BHK – RS 4000" +
                     "3BHK – RS 5000" +
                     "4BHK – RS 6000" +
                     "5 BHK – RS 7000")

             mImageUrl.add(R.drawable.foundation)
             mNames.add("Face Contouring")
             mDesc.add(R.string.exp.toString())
             mPrice.add("1RK - Rs 2500" +
                     "1BHK – Rs 3000" +
                     "2BHK – RS 4000" +
                     "3BHK – RS 5000" +
                     "4BHK – RS 6000" +
                     "5 BHK – RS 7000")

             mImageUrl.add(R.drawable.customised)
             mNames.add("Customized Look")
             mDesc.add(R.string.exp.toString())
             mPrice.add("1RK - Rs 2500" +
                     "1BHK – Rs 3000" +
                     "2BHK – RS 4000" +
                     "3BHK – RS 5000" +
                     "4BHK – RS 6000" +
                     "5 BHK – RS 7000")

             mImageUrl.add(R.drawable.mascara)
             mNames.add("False Lash Application")
             mDesc.add(R.string.exp.toString())
             mPrice.add("1RK - Rs 2500" +
                     "1BHK – Rs 3000" +
                     "2BHK – RS 4000" +
                     "3BHK – RS 5000" +
                     "4BHK – RS 6000" +
                     "5 BHK – RS 7000")

            initRecycleView()
        } else {
            Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
        }
    }

    private fun initRecycleView() {

        try{
        Log.d(TAG, "initRecycleView: init recycleview.")
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val adapter = RecyclerViewAdapter(mNames,mImageUrl,mDesc,mPrice,this)
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
