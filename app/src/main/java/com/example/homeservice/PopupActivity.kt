package com.example.homeservice

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.*

class PopupActivity : Activity(), View.OnClickListener {

    //  private val myDB = Database(this@PopupActivity)

    private var firebaseAuth: FirebaseUser? = null

    private var customerref: FirebaseFirestore? = FirebaseFirestore.getInstance()
    private lateinit var current_user_email: String //= FirebaseAuth.getInstance().currentUser!!.email.toString()
    private var room_count: Int = 0
    private var price_services = 1250
    private var price_beauty = 500

    private var cust_name: TextView? = null
    private var cust_mobno: TextView? = null
    private var cust_addr: TextView? = null
    private var roomcount: TextView? = null
    private var sel_service: TextView? = null
    private var service_price: TextView? = null
    private var total: TextView? = null

    private lateinit var scust_name: String
    private lateinit var scust_mobno: String
    private lateinit var scust_addr: String
    private lateinit var sroomcount: String
    private lateinit var ssel_service: String
    private lateinit var sservice_price: String
    private lateinit var stotal: String

    private var addtocart: Button? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popup)

        if (FirebaseAuth.getInstance().currentUser == null) {
            Toast.makeText(this, "Please Login First!", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        } else {
            firebaseAuth = FirebaseAuth.getInstance().currentUser
            current_user_email = FirebaseAuth.getInstance().currentUser!!.email.toString()
        }

        val dm = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)

        val width = dm.widthPixels
        val height = dm.heightPixels

        window.setLayout((width * .8).toInt(), (height * .7).toInt())

        val params = window.attributes
        params.gravity = Gravity.CENTER
        params.x = 0
        params.y = -20

        window.attributes = params

        cust_name = findViewById(R.id.customer_name)
        cust_mobno = findViewById(R.id.customer_mobileno)
        cust_addr = findViewById(R.id.customer_address)
        roomcount = findViewById(R.id.customer_roocount)
        sel_service = findViewById(R.id.selected_service)
        service_price = findViewById(R.id.service_price)
        total = findViewById(R.id.service_total)
        addtocart = findViewById(R.id.addtocart_btn)

        val progress = ProgressDialog(this)
        progress.setTitle("Loading")
        progress.setMessage("Wait while loading...")
        progress.setCancelable(false) // disable dismiss by tapping outside of the dialog
        progress.show()

        if (firebaseAuth == null) {
            Toast.makeText(this, "Please Login First!", Toast.LENGTH_SHORT).show()
            progress.dismiss()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        } else {
            customerref!!.collection("Customers").document(current_user_email).get()
                .addOnSuccessListener { documentSnapshot ->
                    if (documentSnapshot != null) {

                        room_count = Integer.parseInt(documentSnapshot.get("NoOfRooms").toString())

                        sroomcount = room_count.toString()
                        scust_name =
                            documentSnapshot.get("FirstName").toString() + " " + documentSnapshot.get("LastName")
                        scust_addr = documentSnapshot.get("Address").toString()
                        scust_mobno = documentSnapshot.get("MobileNo").toString()

                        Log.d(
                            "PopUPActivity",
                            " " + room_count + " " + sroomcount + " " + scust_name + scust_mobno + scust_addr
                        )
                        getIncomingIntent()

                        fillData()
                        progress.dismiss()
                    } else {
                        progress.dismiss()
                        Toast.makeText(this, "Error loading room count", Toast.LENGTH_SHORT).show()
                    }
                }

        }

        addtocart?.setOnClickListener(this)

    }

    private fun getIncomingIntent() {
        if (intent.hasExtra("service_name")) {
            val snm = intent.getStringExtra("service_name").toString().trim()

            //Log.d("POPACTIVITY", "snm: " + snm + " \n" + "ssel_service: " + ssel_service)
            ssel_service = snm
            //TODO: to make some changes for different services
            if (snm.equals("Deep Home Cleaning") || snm.equals("Furniture Polishing & Cleaning") || snm.equals(
                    "Manual Cleaning"
                )
                || snm.equals("Floor Scrubbing") || snm.equals("Chemical Pest Control") || snm.equals("Biological Pest Control")
                || snm.equals("Herbal Pest Control") || snm.equals("Matte Paint") || snm.equals("") || snm.equals(
                    ""
                ) || snm.equals(
                    ""
                )
                || snm.equals("Matte Enamel") || snm.equals("Satin Finish") || snm.equals("Semi-gloss Finish") || snm.equals(
                    "Gloss_paint Paint"
                )
            ) {
                total_Calculate_Services()
            } else if (snm.equals("Dry Skin") || snm.equals("Sensitive Skin") || snm.equals("Oil Skin") || snm.equals(
                    "Customized Cut"
                )
                || snm.equals("Shampoo & Blow Dry") || snm.equals("Personalized Color & Highlights") || snm.equals(
                    "Formal Styling"
                )
                || snm.equals("Bridal & Wedding Hairstyles") || snm.equals("Extensions") || snm.equals("Hair Smoothing System")
            ) {
                total_Calculate_Beauty()
            }
        }

        Toast.makeText(this, "Selected Service: " + ssel_service, Toast.LENGTH_SHORT).show()
    }


    private fun total_Calculate_Services() {
        for (i in 1..100) {
            if (room_count == i) {
                price_services = price_services * i
            }
        }

        sservice_price = "1250"
        stotal = price_services.toString()
    }

    private fun total_Calculate_Beauty() {
        sservice_price = "500"
        stotal = price_services.toString()
    }


    private fun fillData() {

        cust_name?.text = scust_name
        cust_addr?.text = "Address: " + scust_addr
        cust_mobno?.text = "Mobile No.: " + scust_mobno
        roomcount?.text = "Room Count: " + sroomcount
        sel_service?.text = "Selected Service: " + ssel_service
        service_price?.text = "Service Price: " + sservice_price
        total?.text = "Total: " + stotal

        Log.d("FillData()", " " + scust_name + " " + scust_addr + " " + scust_mobno + " " + sroomcount)
    }

    override fun onClick(v: View?) {
        Log.d("FirebaseAuth: ", "OOOOONNNNNCLLLICCCK: " + firebaseAuth.toString())

        val progress = ProgressDialog(this)
        progress.setTitle("Loading")
        progress.setMessage("Wait while loading...")
        progress.setCancelable(false) // disable dismiss by tapping outside of the dialog
        progress.show()

        if (firebaseAuth == null) {
            Toast.makeText(this, "Please Login First!", Toast.LENGTH_SHORT).show()
            progress.dismiss()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        } else {
            if (FirebaseAuth.getInstance().currentUser != null) {
                val cart = HashMap<String, String?>()
                cart["SelectedService"] = ssel_service
                cart["ServicePrice"] = sservice_price
                cart["SubTotal"] = stotal
                customerref?.collection("Customers")?.document(current_user_email)?.collection("CartDetails")?.add(cart)
                    ?.addOnSuccessListener {

                        // To dismiss the dialog
                        progress.dismiss()
                        Toast.makeText(this, "Added to cart successfully", Toast.LENGTH_SHORT).show()

                        Database(getBaseContext()).addToCart(
                            Service(
                                ssel_service,
                                sservice_price,
                                stotal
                            )
                        )
                        //autoFillEditText()
                        finish()
                    }
                    ?.addOnFailureListener {
                        progress.dismiss()
                        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                        Log.w(TAG, "Exception :")
                    }

            } else {
                Toast.makeText(this, "Please Login First!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    //    fun autoFillEditText() {
//        val res = myDB.getAllData()
//        if (res.getCount() == 0) {
//            Toast.makeText(this, "NO DATA", Toast.LENGTH_SHORT).show()
//            Log.d("POPUP","NO DATA")
//        } else {
//            while (res.moveToNext()) {
//                Log.d("POPUP", res.getColumnName(0)+" " + res.getString(0)
//                +"\n" + res.getColumnName(1)+" " +res.getString(1)
//                +"\n"+ res.getColumnName(2)+" " +res.getString(2)
//                +"\n"+ res.getColumnName(3)+" " +res.getString(3))
//            }
//        }
//    }
    companion object {

        private val TAG = "PopupActivity"
    }
}

