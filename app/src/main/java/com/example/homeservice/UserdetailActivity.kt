package com.example.homeservice

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class UserdetailActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        if (v === submit_btn) {
            //TODO:ADD INFORMATION TO DATABASE
            startActivity(Intent(this, MainPage::class.java))
        }
    }

    private val firstname_tv: TextView? = null
    private val lastname_tv: TextView? = null
    private val mobileno_tv: TextView? = null
    private val addresst_v: TextView? = null
    private val noofroomst_v: TextView? = null
    private var firstname: EditText? = null
    private var lastname: EditText? = null
    private var mobileno: EditText? = null
    private var address: EditText? = null
    private var noofrooms: EditText? = null
    private var submit_btn: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_userdetail)

        firstname = findViewById(R.id.fnameet)
        lastname = findViewById(R.id.lnameet)
        mobileno = findViewById(R.id.contnoet)
        address = findViewById(R.id.addet)
        noofrooms = findViewById(R.id.roomnoet)

        submit_btn = findViewById(R.id.user_submit)

        submit_btn!!.setOnClickListener(this)
    }
}
