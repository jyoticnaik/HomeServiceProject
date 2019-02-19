package com.example.homeservice

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import java.io.IOException

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    //Intializing
    private var sigin: Button?=null
    private var username: EditText?=null
    private var password: EditText?=null
    private var fgtpss:TextView?=null
    private var img:ImageView? =null
    private var signuphp:TextView?=null

    private final var TAG="LoginActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

            //Declaration
            sigin = this.findViewById(R.id.signinBtn)
            username = findViewById(R.id.usernametxt)
            password = findViewById(R.id.passwordtxt)
            fgtpss = findViewById(R.id.fpasstxt)
            signuphp = findViewById(R.id.signuptxt)

            //sigin!!.setOnClickListener(this)
            //fgtpss!!.setOnClickListener(this)
            //signuphp!!.setOnClickListener(this)

        Log.d(TAG,"Entered LoginActivity")

    }

    override fun onClick(v: View?) {
        Toast.makeText(this,"PAGE NOT YET READY",Toast.LENGTH_LONG).show()
    }
}
