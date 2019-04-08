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
    private val mDesc = ArrayList<String>()

    private val mPrice = ArrayList<String>()

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
            mDesc.add(
                "1.Complete Deep Cleaning of all areas of the house including Bathroom,Kitchen,Dining Room,Bedroom and Balcony.\n" +
                        "2.Deep Cleaning and cleaning of curtains,sofa set and chairs using Professional grade cleaning solution.\n" +
                        "3.Cleaning equipment and vacuum cleaners by proffessionals cleaning staff.\n" +
                        "4.Deep Cleaning of external surfaces,cabinets and appliance exteriors, and removal of grease and oil stains.\n" +
                        "5.Deep cleaning of floor, WC, seat, sink, fittings and walls.\n" +
                        "6.Deep cleaning of floor, windows, furniture and light fittings.\n" +
                        "7.Deep cleaning grill work and windows.\n" +
                        "8.Dry vacuum cleaning of sofa, carpent, and curtains.\n" +
                        "9.Cleaning of fans,lights,windows,railing,cabinets and switchboards."
            )
            mPrice.add(
                "1RK - Rs 2500\n" +
                        "1BHK – Rs 3000\n" +
                        "2BHK – RS 4000\n" +
                        "3BHK – RS 5000\n" +
                        "4BHK – RS 6000\n" +
                        "5 BHK – RS 7000\n"
            )

            mImageUrl.add(R.drawable.carpetcleaning)
            mNames.add("Furniture Polishing & Cleaning")
            mDesc.add(
                "1.Dry Vacuuming using vacuum cleaner to remove dust\n" +
                        "2.Shampooing using biodegradable solution\n" +
                        "3.Wet vacuuming post shampooing for removing dirt\n" +
                        "4.Polishing is done for leather sofas\n" +
                        "5.Cleaning Time: 1-2 Hours\n" +
                        "6.Cleaning Staff: 1\n" +
                        "7.Polishing\n" +
                        "8.Dry Cleaning\n" +
                        "9.Complete Cleaning of Sofa\n" +
                        "10.Overall cleaning of dining table and chairs\n" +
                        "Cleaning Solutions Used:\n" +
                        " " + "Chemical: Biodegradable chemicals like Taski etc\n" +
                        "Equipment Used:\n" +
                        " " + "Vacuum Cleaner(Wet and dry) and hand brushes\n" +
                        "Air Blower for removing dust.\n" +
                        "Does Not Include:\n" +
                        " " + "Any other furniture that are not mention will not be consider in service\n" +
                        "Polishing Done only on required stuff that are made by furniture."
            )
            mPrice.add(
                "1RK - Rs 2500\n" +
                        "1BHK – Rs 3000\n" +
                        "2BHK – RS 4000\n" +
                        "3BHK – RS 5000\n" +
                        "4BHK – RS 6000\n" +
                        "5 BHK – RS 7000\n"
            )


            mImageUrl.add(R.drawable.janitor)
            mNames.add("Manual Cleaning")
            mDesc.add(
                "1.Manual Cleaning is done by person only\n" +
                        "2.Manual Cleaning is basic manual cleaning process where person not use any advanced equipment\n" +
                        "3.Cleaning Time: 2-3 Hours\n" +
                        "4.Manul Cleaning of all areas of the house inlcuding Bathroom, Kitchen, Living Room, Dining Room, Bedroom.\n" +
                        "5.Manual Cleaning and cleaning of curtains, sofa set and chairs\n" +
                        "6.Manual Cleaning of external surfaces, cabinets and removal of grease and oil stains.\n" +
                        "7.Cleaning of floor, WC seat, sink, fittings and walls.\n" +
                        "8.Cleaning of floor, windows, furniture and light fittings.\n" +
                        "9.Cleaning Floor, grill work and windows.\n" +
                        "10.Cleaning of doors, cupboards, handles and wardrobe exteriors."
            )

            mPrice.add(
                "1RK - Rs 2500\n" +
                        "1BHK – Rs 3000\n" +
                        "2BHK – RS 4000\n" +
                        "3BHK – RS 5000\n" +
                        "4BHK – RS 6000\n" +
                        "5 BHK – RS 7000\n"
            )

            mImageUrl.add(R.drawable.vacuumcleaner)
            mNames.add("Floor Scrubbing")
            mDesc.add(
                "1.Removal of stains and spots\n" +
                        "2.Loosening of ground-in soils\n" +
                        "3.Shampooing\n" +
                        "4.Drying using vacuuming\n" +
                        "5.1-3 Hours\n" +
                        "6.Servicemen: 1\n" +
                        "7.Any hard/major stains may not immediately be removed\n" +
                        "8.Wet vacuuming post shampooing for removing dirt.\n" +
                        "9.Complete Cleaning of Floor\n" +
                        "10.Cleaning of carpet\n" +
                        "Cleaning Solutions Used:\n" +
                        " " + "Professional grade cleaning solutions by Diversey.\n" +
                        "Equipment Used:\n" +
                        " " + "Vacuum Cleaner\n" +
                        " " + "Hand Brushes"
            )
            mPrice.add(
                "1RK - Rs 2500\n" +
                        "1BHK – Rs 3000\n" +
                        "2BHK – RS 4000\n" +
                        "3BHK – RS 5000\n" +
                        "4BHK – RS 6000\n" +
                        "5 BHK – RS 7000\n"
            )

            Log.d(TAG, "Home Service if structure")

            initRecycleView()
        } else if (s == "pestcv") {
            mImageUrl.add(R.drawable.insect)
            mNames.add("Biological Pest Control")
            mDesc.add(
                "1.Our well-trained staff applies this gel to areas which are more prone to pests attack such as electrical control boxes, computers, cabinet corners and kitchen appliances. They take care to make sure that no area vulnerable to infestation is left untreated.\n" +
                        "The cockroaches get attracted to the gel and once they have consumed the gel, they spread the contamination to other cockroaches.\n" +
                        "2.Within a week the results are evident and the cockroaches/ants are completely eradicated within 3 weeks.\n" +
                        "3.This is a safe, quick, effective and most advanced gel treatment for getting rid of cockroaches, ants, silverfish, spiders, etc."
            )
            mPrice.add(
                "1RK - Rs 2500\n" +
                        "1BHK – Rs 3000\n" +
                        "2BHK – RS 4000\n" +
                        "3BHK – RS 5000\n" +
                        "4BHK – RS 6000\n" +
                        "5 BHK – RS 7000\n"
            )

            mImageUrl.add(R.drawable.insecticide)
            mNames.add("Chemical Pest Control")
            mDesc.add(
                "1.Our well-trained staff sprays the solutions (approved by World Health Organization) in areas which are more prone to pests attack such as drainage pipes, benches in the society compounds, furniture etc. They ensure that no area vulnerable to infestation is left untreated.\n" +
                        "2.The pests are immediately affected by the composition of the spray and within few hours the cockroaches/ants/silverfish are completely eradicated.\n" +
                        "3.This is a safe, quick and effective treatment for getting rid of crawling insects such as\n" +
                        "\n" +
                        "Cockroaches\n" +
                        "Ants\n" +
                        "Silverfish\n" +
                        "Spiders\n" +
                        "Other crawling insects\n" +
                        "With the use of special chemicals, this can be a complete odourless treatment."
            )
            mPrice.add(
                "1RK - Rs 2500\n" +
                        "1BHK – Rs 3000\n" +
                        "2BHK – RS 4000\n" +
                        "3BHK – RS 5000\n" +
                        "4BHK – RS 6000\n" +
                        "5 BHK – RS 7000\n"
            )

            //TODO:Changes to be done from here.
            mImageUrl.add(R.drawable.mortarandpestle)
            mNames.add("Herbal Pest Control")
            mDesc.add(R.string.exp.toString())
            mPrice.add(
                "1RK - Rs 2500" +
                        "1BHK – Rs 3000" +
                        "2BHK – RS 4000" +
                        "3BHK – RS 5000" +
                        "4BHK – RS 6000" +
                        "5 BHK – RS 7000"
            )

            initRecycleView()
        } else if (s == "paintcv") {
            mImageUrl.add(R.drawable.mattepaint)
            mNames.add("Matte Paint")
            mDesc.add(R.string.exp.toString())
            mPrice.add(
                "1RK - Rs 2500" +
                        "1BHK – Rs 3000" +
                        "2BHK – RS 4000" +
                        "3BHK – RS 5000" +
                        "4BHK – RS 6000" +
                        "5 BHK – RS 7000"
            )

            mImageUrl.add(R.drawable.matteenamel)
            mNames.add("Matte Enamel")
            mDesc.add(R.string.exp.toString())
            mPrice.add(
                "1RK - Rs 2500" +
                        "1BHK – Rs 3000" +
                        "2BHK – RS 4000" +
                        "3BHK – RS 5000" +
                        "4BHK – RS 6000" +
                        "5 BHK – RS 7000"
            )

            mImageUrl.add(R.drawable.paint_satin)
            mNames.add("Satin Finish")
            mDesc.add(R.string.exp.toString())
            mPrice.add(
                "1RK - Rs 2500" +
                        "1BHK – Rs 3000" +
                        "2BHK – RS 4000" +
                        "3BHK – RS 5000" +
                        "4BHK – RS 6000" +
                        "5 BHK – RS 7000"
            )

            mImageUrl.add(R.drawable.paint_semigloss)
            mNames.add("Semi-gloss Finish")
            mDesc.add(R.string.exp.toString())
            mPrice.add(
                "1RK - Rs 2500" +
                        "1BHK – Rs 3000" +
                        "2BHK – RS 4000" +
                        "3BHK – RS 5000" +
                        "4BHK – RS 6000" +
                        "5 BHK – RS 7000"
            )

            mImageUrl.add(R.drawable.paint_gloss)
            mNames.add("Gloss_paint Paint")
            mDesc.add(R.string.exp.toString())
            mPrice.add(
                "1RK - Rs 2500" +
                        "1BHK – Rs 3000" +
                        "2BHK – RS 4000" +
                        "3BHK – RS 5000" +
                        "4BHK – RS 6000" +
                        "5 BHK – RS 7000"
            )

            initRecycleView()
        } else if (s == "skincv") {
            mImageUrl.add(R.drawable.dry_skin)
            mNames.add("Dry Skin")
            mDesc.add(R.string.exp.toString())
            mPrice.add(
                "1RK - Rs 2500" +
                        "1BHK – Rs 3000" +
                        "2BHK – RS 4000" +
                        "3BHK – RS 5000" +
                        "4BHK – RS 6000" +
                        "5 BHK – RS 7000"
            )

            mImageUrl.add(R.drawable.sensitive_skin)
            mNames.add("Sensitive Skin")
            mDesc.add(R.string.exp.toString())
            mPrice.add(
                "1RK - Rs 2500" +
                        "1BHK – Rs 3000" +
                        "2BHK – RS 4000" +
                        "3BHK – RS 5000" +
                        "4BHK – RS 6000" +
                        "5 BHK – RS 7000"
            )

            mImageUrl.add(R.drawable.oily_skin)
            mNames.add("Oil Skin")
            mDesc.add(R.string.exp.toString())
            mPrice.add(
                "1RK - Rs 2500" +
                        "1BHK – Rs 3000" +
                        "2BHK – RS 4000" +
                        "3BHK – RS 5000" +
                        "4BHK – RS 6000" +
                        "5 BHK – RS 7000"
            )

            initRecycleView()
        } else if (s == "haircv") {
            mImageUrl.add(R.drawable.haircutting)
            mNames.add("Customized Cut")
            mDesc.add(R.string.exp.toString())
            mPrice.add(
                "1RK - Rs 2500" +
                        "1BHK – Rs 3000" +
                        "2BHK – RS 4000" +
                        "3BHK – RS 5000" +
                        "4BHK – RS 6000" +
                        "5 BHK – RS 7000"
            )

            mImageUrl.add(R.drawable.hairdryer)
            mNames.add("Shampoo & Blow Dry")
            mDesc.add(R.string.exp.toString())
            mPrice.add(
                "1RK - Rs 2500" +
                        "1BHK – Rs 3000" +
                        "2BHK – RS 4000" +
                        "3BHK – RS 5000" +
                        "4BHK – RS 6000" +
                        "5 BHK – RS 7000"
            )

            mImageUrl.add(R.drawable.hairhighlights)
            mNames.add("Personalized Color & Highlights")
            mDesc.add(R.string.exp.toString())
            mPrice.add(
                "1RK - Rs 2500" +
                        "1BHK – Rs 3000" +
                        "2BHK – RS 4000" +
                        "3BHK – RS 5000" +
                        "4BHK – RS 6000" +
                        "5 BHK – RS 7000"
            )

            mImageUrl.add(R.drawable.formalstyling)
            mNames.add("Formal Styling")
            mDesc.add(R.string.exp.toString())
            mPrice.add(
                "1RK - Rs 2500" +
                        "1BHK – Rs 3000" +
                        "2BHK – RS 4000" +
                        "3BHK – RS 5000" +
                        "4BHK – RS 6000" +
                        "5 BHK – RS 7000"
            )

            mImageUrl.add(R.drawable.bridal)
            mNames.add("Bridal & Wedding Hairstyles")
            mDesc.add(R.string.exp.toString())
            mPrice.add(
                "1RK - Rs 2500" +
                        "1BHK – Rs 3000" +
                        "2BHK – RS 4000" +
                        "3BHK – RS 5000" +
                        "4BHK – RS 6000" +
                        "5 BHK – RS 7000"
            )

            mImageUrl.add(R.drawable.hairextensiond)
            mNames.add("Extensions")
            mDesc.add(R.string.exp.toString())
            mPrice.add(
                "1RK - Rs 2500" +
                        "1BHK – Rs 3000" +
                        "2BHK – RS 4000" +
                        "3BHK – RS 5000" +
                        "4BHK – RS 6000" +
                        "5 BHK – RS 7000"
            )

            mImageUrl.add(R.drawable.hairsmoothning)
            mNames.add("Hair Smoothing System")
            mDesc.add(R.string.exp.toString())
            mPrice.add(
                "1RK - Rs 2500" +
                        "1BHK – Rs 3000" +
                        "2BHK – RS 4000" +
                        "3BHK – RS 5000" +
                        "4BHK – RS 6000" +
                        "5 BHK – RS 7000"
            )

            initRecycleView()
        } else if (s == "makeupcv") {
            mImageUrl.add(R.drawable.lipgloss)
            mNames.add("Lips")
            mDesc.add(R.string.exp.toString())
            mPrice.add(
                "1RK - Rs 2500" +
                        "1BHK – Rs 3000" +
                        "2BHK – RS 4000" +
                        "3BHK – RS 5000" +
                        "4BHK – RS 6000" +
                        "5 BHK – RS 7000"
            )

            mImageUrl.add(R.drawable.eyeshadow)
            mNames.add("Eyes")
            mDesc.add(R.string.exp.toString())
            mPrice.add(
                "1RK - Rs 2500" +
                        "1BHK – Rs 3000" +
                        "2BHK – RS 4000" +
                        "3BHK – RS 5000" +
                        "4BHK – RS 6000" +
                        "5 BHK – RS 7000"
            )

            mImageUrl.add(R.drawable.foundation)
            mNames.add("Face Contouring")
            mDesc.add(R.string.exp.toString())
            mPrice.add(
                "1RK - Rs 2500" +
                        "1BHK – Rs 3000" +
                        "2BHK – RS 4000" +
                        "3BHK – RS 5000" +
                        "4BHK – RS 6000" +
                        "5 BHK – RS 7000"
            )

            mImageUrl.add(R.drawable.customised)
            mNames.add("Customized Look")
            mDesc.add(R.string.exp.toString())
            mPrice.add(
                "1RK - Rs 2500" +
                        "1BHK – Rs 3000" +
                        "2BHK – RS 4000" +
                        "3BHK – RS 5000" +
                        "4BHK – RS 6000" +
                        "5 BHK – RS 7000"
            )

            mImageUrl.add(R.drawable.mascara)
            mNames.add("False Lash Application")
            mDesc.add(R.string.exp.toString())
            mPrice.add(
                "1RK - Rs 2500" +
                        "1BHK – Rs 3000" +
                        "2BHK – RS 4000" +
                        "3BHK – RS 5000" +
                        "4BHK – RS 6000" +
                        "5 BHK – RS 7000"
            )

            initRecycleView()
        } else {
            Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
        }
    }

    private fun initRecycleView() {

        try {
            Log.d(TAG, "initRecycleView: init recycleview.")
            val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
            val adapter = RecyclerViewAdapter(mNames, mImageUrl, mDesc, mPrice, this)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(this)
        } catch (e: Exception) {
            Log.d(TAG, "Exception occured: " + e.localizedMessage)
        }
    }

    companion object {

        private val TAG = "HC"
    }
}
