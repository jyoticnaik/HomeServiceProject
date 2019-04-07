package com.example.homeservice

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.amulyakhare.textdrawable.TextDrawable
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

import java.text.NumberFormat
import java.util.ArrayList
import java.util.Locale

class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    var txt_service_name: TextView
    var txt_service_price: TextView
    var img_service_count: ImageView
    var parent_cartLayout: CardView

    init {
        txt_service_name = itemView.findViewById(R.id.cart_service_name)
        txt_service_price = itemView.findViewById(R.id.cart_service_price)
        img_service_count = itemView.findViewById(R.id.cart_service_count)
        parent_cartLayout = itemView.findViewById(R.id.parent_cartLayout)
    }

    override fun onClick(v: View) {

    }
}

class CartAdapter(listData: ArrayList<Service>, private val context: Context) : RecyclerView.Adapter<CartViewHolder>() {

    private var customerref: FirebaseFirestore? = FirebaseFirestore.getInstance()
    private var current_user_email = FirebaseAuth.getInstance().currentUser!!.email.toString()
    private var room_count: Int = 0

    private var listData = ArrayList<Service>()

    init {
        this.listData = listData
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val inflater = LayoutInflater.from(context)
        val itemView = inflater.inflate(R.layout.cart_layout, parent, false)
        return CartViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {


        customerref!!.collection("Customers").document(current_user_email).get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot != null) {

                    room_count = Integer.parseInt(documentSnapshot.get("NoOfRooms").toString())
                    val drawable = TextDrawable.builder().buildRoundRect("" + room_count, Color.DKGRAY, 10)
                    holder.img_service_count.setImageDrawable(drawable)
                } else {
                    Log.d("CartAdapter", "ERROR")
                }
            }

        val locale = Locale("en_IN", "IN")
        val fmt = NumberFormat.getCurrencyInstance(locale)
        val price = Integer.parseInt(listData[position].getserviceTotal())
        holder.txt_service_price.text = fmt.format(price.toLong())
        holder.txt_service_name.text = listData[position].getserviceName()

        holder.parent_cartLayout.setOnClickListener {
            showAlertDialog(position)
        }

    }

    private fun showAlertDialog(position: Int) {
        var alertdialog: AlertDialog.Builder
        alertdialog = AlertDialog.Builder(context);
        alertdialog.setTitle("Delete Service?")
        alertdialog.setMessage("Are you sure?")
        alertdialog.setPositiveButton("YES", DialogInterface.OnClickListener { dialog, which ->
            Database(context).cancleService(listData[position].getserviceName())
            Toast.makeText(context, "Service Order Deleted Successfully", Toast.LENGTH_SHORT).show()
        })
            .setNegativeButton("NO", DialogInterface.OnClickListener { dialog, which ->
                dialog.dismiss()
            })
        alertdialog.show()
    }

    override fun getItemCount(): Int {
        return listData.size
    }
}
