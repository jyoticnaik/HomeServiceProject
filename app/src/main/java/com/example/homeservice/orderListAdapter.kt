package com.example.homeservice

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class orderListAdapter(var ordersList: List<orders>) : RecyclerView.Adapter<orderListAdapter.ViewHolder>() {
    inner class ViewHolder(internal var mview: View) : RecyclerView.ViewHolder(mview) {
        internal var mbkst: TextView
        internal var maddr: TextView
        internal var mgt: TextView

        init {
            mbkst = mview.findViewById(R.id.bkstat)
            maddr = mview.findViewById(R.id.addr)
            mgt = mview.findViewById(R.id.grandt)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): orderListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.order_display, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: orderListAdapter.ViewHolder, position: Int) {
        holder.mbkst.text = ordersList[position].bookingStatus
        holder.maddr.text = ordersList[position].address
        holder.mgt.text = ordersList[position].grandTotal

    }

    override fun getItemCount(): Int {
        return ordersList.size
    }
}
