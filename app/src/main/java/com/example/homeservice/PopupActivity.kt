package com.example.homeservice

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ProgressDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class PopupActivity : Activity(), View.OnClickListener {

    private var customerref: FirebaseFirestore? = FirebaseFirestore.getInstance()
    private var current_user_email = FirebaseAuth.getInstance().currentUser!!.email.toString()
    private var room_count: Int = 0
    private var price = 1250

    private var cust_name: TextView? = null
    private var cust_mobno: TextView? = null
    private var cust_addr: TextView? = null
    private var roomcount: TextView? = null
    private var sel_service: TextView? = null
    private var service_price: TextView? = null
    private var total: TextView? = null

    private var scust_name: String? = null
    private var scust_mobno: String? = null
    private var scust_addr: String? = null
    private var sroomcount: String? = null
    private var ssel_service: String? = null
    private var sservice_price: String? = null
    private var stotal: String? = null

    private var addtocart: Button? = null

    private var progressDialog: ProgressDialog? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popup)

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

        progressDialog!!.setMessage("Loading")
        progressDialog!!.show()

        customerref!!.collection("Customers").document(current_user_email).get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot != null) {
                    progressDialog!!.dismiss()
                    room_count = Integer.parseInt(documentSnapshot.get("NoOfRooms").toString())

                    sroomcount = room_count.toString()
                    scust_name = documentSnapshot.get("FirstName").toString() + " " + documentSnapshot.get("LastName")
                    scust_addr = documentSnapshot.get("Address").toString()
                    scust_mobno = documentSnapshot.get("MobileNo").toString()

                } else {
                    Toast.makeText(this, "Error loading room count", Toast.LENGTH_SHORT).show()
                }
            }

        getIncomingIntent()
        total_Calculate()
        fillData()
        addtocart?.setOnClickListener(this)
    }


    private fun total_Calculate() {
        for (i in 1..100) {
            if (room_count == i) {
                price = price * i
            }
        }

        sservice_price = "1250"
        stotal = price.toString()
        return
    }

    private fun getIncomingIntent() {
        if (intent.hasExtra("service_name")) {
            val snm = intent.getStringExtra("service_name").toString()
            ssel_service = snm
            return
        }
    }


    private fun fillData() {
        cust_name?.text = scust_name
        cust_addr?.text = scust_addr
        cust_mobno?.text = scust_mobno
        roomcount?.text = sroomcount
        sel_service?.text = ssel_service
        service_price?.text = sservice_price
        total?.text = stotal
    }

    override fun onClick(v: View?) {
        progressDialog!!.setMessage("Loading")
        progressDialog!!.show()
        val cart = HashMap<String, String?>()
        cart["SelectedService"] = ssel_service
        cart["ServicePrice"] = sservice_price
        cart["SubTotal"] = stotal
        customerref?.collection("Customers")?.document(current_user_email)?.collection("CartDetails")?.add(cart)
            ?.addOnSuccessListener {
                progressDialog!!.dismiss()
                Toast.makeText(this,"Added to cart successfully",Toast.LENGTH_SHORT).show()
                finish()
            }
            ?.addOnFailureListener{
                progressDialog!!.dismiss()
                Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show()
                Log.w(TAG,"Exception :")
            }
    }

    companion object {

        private val TAG = "PopupActivity"
    }
}

