package com.example.homeservice

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.FirebaseFirestore
import java.lang.Boolean.TRUE

import java.text.NumberFormat
import java.util.ArrayList
import java.util.Locale

class Cart : AppCompatActivity(), View.OnClickListener {

    private var customerref: FirebaseFirestore? = FirebaseFirestore.getInstance()
    private var current_user_email = FirebaseAuth.getInstance().currentUser!!.email.toString()

    internal lateinit var recyclerView: RecyclerView
    internal lateinit var layoutManager: RecyclerView.LayoutManager

    internal lateinit var firebaseFirestore: FirebaseFirestore
    internal var request: DatabaseReference? = null

    internal lateinit var txtGrandTotal: TextView
    internal lateinit var btnBookNow: Button

    internal var carts: ArrayList<Service> = ArrayList()
    internal lateinit var adapter: CartAdapter

    private lateinit var scust_addr: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_to_cart)

        //FIREBASE
        firebaseFirestore = FirebaseFirestore.getInstance()
        //request = firebaseFirestore.getClass("Requests");

        //INIT
        recyclerView = findViewById(R.id.listCart)
        recyclerView.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        txtGrandTotal = findViewById(R.id.grand_total)
        btnBookNow = findViewById(R.id.confirmation_btn)

        btnBookNow.setOnClickListener(this)
        loadServiceList()

    }

    private fun loadServiceList() {
        carts = Database(this).cart
        adapter = CartAdapter(carts, this)
        recyclerView.adapter = adapter

        //Calculate Grand Total
        var gtotal = 0
        for (service in carts)
            gtotal += Integer.parseInt(service.getserviceTotal())

        val locale = Locale("en_IN", "IN")
        val fmt = NumberFormat.getCurrencyInstance(locale)

        txtGrandTotal.text = fmt.format(gtotal.toLong())
    }

    override fun onClick(v: View?) {
        //Create com.example.homeservice.BookService

        val progress = ProgressDialog(this)
        progress.setTitle("Loading")
        progress.setMessage("Wait while loading...")
        progress.setCancelable(false) // disable dismiss by tapping outside of the dialog
        progress.show()

        customerref!!.collection("Customers").document(current_user_email).get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot != null) {
                    scust_addr = documentSnapshot.get("Address").toString()
                    val bs = BookService("TRUE",txtGrandTotal.text.toString(),scust_addr,carts)
                    customerref!!.collection("Customers").document(current_user_email).collection("BookingDetails").add(bs).addOnSuccessListener {
                        Toast.makeText(this,"Service Booked!",Toast.LENGTH_SHORT).show()
                        progress.dismiss()
                        finish()
                    }.addOnFailureListener {
                        Toast.makeText(this,"Error while booking.",Toast.LENGTH_SHORT).show()
                    }

                    Database(this).cleanCart()
                } else {
                    Toast.makeText(this, "Error loading room count", Toast.LENGTH_SHORT).show()
                }
            }


    }

}
