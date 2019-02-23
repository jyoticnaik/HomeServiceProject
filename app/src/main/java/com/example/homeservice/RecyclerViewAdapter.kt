package com.example.homeservice

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.layout_listitem.view.*

import java.util.ArrayList

class RecyclerViewAdapter(mImageName: ArrayList<String>, mImage: ArrayList<Int>,mDesc: ArrayList<String>,mPrice: ArrayList<String>, private val mContext: Context) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private var mImageName = ArrayList<String>()
    private var mImage = ArrayList<Int>()
    private var mDesc = ArrayList<String>()
    private var mPrice=ArrayList<String>()

    private val TAG="RecyclerViewAdapter"

    init {
        Log.d(TAG,"Entered RecyclerViewAapter")
        this.mImageName = mImageName
        this.mImage = mImage
        this.mDesc = mDesc
        this.mPrice = mPrice
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_listitem, parent, false)
        val holder = ViewHolder(view)
        return holder
    }

    @SuppressLint("LongLogTag")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder: called")

        //Takes the context, tells glide that we want it as a bitmap we are loading into imageview.
        //Glide.with(mContext).asBitmap().load(mImage[position]).into(holder.image)

        Glide.with(mContext).load(mImage[position]).into(holder.image)

        holder.image_name.text = mImageName[position]

        holder.parentlayout.setOnClickListener {
            Log.d(TAG, "onClick: clickedon: " + mImageName[position])
            Toast.makeText(mContext, mImageName[position], Toast.LENGTH_SHORT).show()

            val intent = Intent(mContext, DescpActivity::class.java)
            intent.putExtra("desc_head", mImageName.get(position))
            intent.putExtra("desc",mDesc.get(position))
            intent.putExtra("price",mPrice.get(position))
            mContext.startActivity(intent)

        }

    }

    override fun getItemCount(): Int {
        return mImageName.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var image: CircleImageView
        internal var image_name: TextView
        internal var parentlayout: RelativeLayout

        init {

            image = itemView.findViewById(R.id.image)
            image_name = itemView.findViewById(R.id.image_name)
            parentlayout = itemView.findViewById(R.id.parent_layout)
        }
    }

}
