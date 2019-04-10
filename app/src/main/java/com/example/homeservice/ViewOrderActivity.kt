package com.example.homeservice

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*
import java.util.ArrayList
import java.util.Objects

import java.util.Objects.*

class ViewOrderActivity : AppCompatActivity() {
    private var mOrder: RecyclerView? = null
    private val customerref = FirebaseFirestore.getInstance()
    private var orderListAdapter: orderListAdapter? = null
    private var current_user_email: String? = null

    private var ordersList: ArrayList<orders>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_order)

        ordersList = ArrayList()
        orderListAdapter = orderListAdapter(ordersList!!)
        mOrder = findViewById(R.id.order_list)
        mOrder!!.setHasFixedSize(true)
        mOrder!!.layoutManager = LinearLayoutManager(this)
        mOrder!!.adapter = orderListAdapter

        if (FirebaseAuth.getInstance().currentUser == null) {
            Toast.makeText(this, "Please Login First.", Toast.LENGTH_SHORT).show()
            finish()
            startActivity(Intent(this, MainPage::class.java))
        } else {
            if (FirebaseAuth.getInstance().currentUser != null) {
                current_user_email = FirebaseAuth.getInstance().currentUser!!.email!!.toString()
            }
            customerref.collection("Customers").document(current_user_email!!).collection("BookingDetails")
                .addSnapshotListener { documentSnapshots, e ->
                    if (e != null) {
                        Log.d(TAG, "Error: " + e.message)
                    }

                    for (doc in documentSnapshots!!.documentChanges) {
                        if (doc.type == DocumentChange.Type.ADDED || doc.type == DocumentChange.Type.MODIFIED) {
                            //                                String bks = doc.getDocument().getString("bookingStatus");
                            //                                String addr = doc.getDocument().getString("address");
                            //                                String gt = doc.getDocument().getString("grandTotal");
                            //                                String serv = doc.getDocument().get("services").toString();
                            val ord = doc.document.toObject(orders::class.java)
                            ordersList!!.add(ord)
                            orderListAdapter!!.notifyDataSetChanged()
                        }
                    }
                }
        }

    }

    companion object {

        private val TAG = "ViewOrderActivity"
    }
}
