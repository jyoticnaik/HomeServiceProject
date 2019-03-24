package com.example.homeservice

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ProgressDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.Gravity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class PopupActivity : Activity() {

    private var customerref: FirebaseFirestore? = null
    private lateinit var current_user_email: String
    private var room_count: Int = 0
    private var price = 1250
    private var cust_name: TextView? = null
    private var cust_mobno: TextView? = null
    private var cust_addr: TextView? = null
    private var roomcount: TextView? = null
    private var sel_service: TextView? = null
    private var service_price: TextView? = null
    private var total: TextView? = null
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

        progressDialog!!.setMessage("Registring User")
        progressDialog!!.show()
        current_user_email = FirebaseAuth.getInstance().currentUser!!.email.toString()
        customerref!!.collection("Customers").document(current_user_email).get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot != null) {
                    progressDialog!!.dismiss()
                    room_count = Integer.parseInt(documentSnapshot.get("NoOfRooms").toString())
                    cust_mobno?.text = documentSnapshot.get("FirstName").toString() + " " + documentSnapshot.get("LastName")
                    cust_addr?.text = documentSnapshot.get("Address").toString()
                    roomcount?.text = room_count.toString()
                } else {
                    Toast.makeText(this, "Error loading room count", Toast.LENGTH_SHORT).show()
                }
            }

        getIncomingIntent()
        total_Calculate()
    }

    private fun total_Calculate() {
        for (i in 1..100) {
            if (room_count == i) {
                price = price * i
            }
        }
        service_price?.text = "1250"
        total?.text = price.toString()
    }

    private fun getIncomingIntent() {
        if (intent.hasExtra("service_name")) {
            val snm = intent.getStringExtra("service_name").toString()
            sel_service?.text = snm
            return
        }
    }


    companion object {

        private val TAG = "PopupActivity"
    }
}

