package com.example.homeservice

import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import java.io.IOException

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    //Intializing
    private var sigin: Button?=null
    private var username: EditText?=null
    private var password: EditText?=null
    private var fgtpss:TextView?=null
    //private var img:ImageView? =null
    private var signuphp:TextView?=null

    private var progressDialog: ProgressDialog? = null

    private var firebaseAuth: FirebaseAuth? = null

    private var TAG="LoginActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        firebaseAuth = FirebaseAuth.getInstance()
        //Checks if user has already loggedin
        if (firebaseAuth!!.currentUser != null) {
            Toast.makeText(this, "You are logged in.", Toast.LENGTH_SHORT).show()
        }

            //Declaration
            sigin = this.findViewById(R.id.signinBtn)
            username = findViewById(R.id.usernametxt)
            password = findViewById(R.id.passwordtxt)
            fgtpss = findViewById(R.id.fpasstxt)
            signuphp = findViewById(R.id.signuptxt)

            sigin!!.setOnClickListener(this)
            fgtpss!!.setOnClickListener(this)
            signuphp!!.setOnClickListener(this)

        Log.d(TAG,"Entered LoginActivity")

    }

    private fun userLogin() {
        val email = username!!.text.toString().trim { it <= ' ' }
        val pass = password!!.text.toString().trim { it <= ' ' }

        //Checkin if email and password field are empty
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter email id.", Toast.LENGTH_SHORT).show()
            return
        }

        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "Please enter password.", Toast.LENGTH_SHORT).show()
            return
        }

        //progress Dialog bar because the process include internet use which may take time.
        progressDialog!!.setMessage("Signing In Please Wait...")
        progressDialog!!.show()

        firebaseAuth!!.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    //start the main activity
                    finish()
                    startActivity(Intent(this,MainPage::class.java))
                } else {
                    Toast.makeText(this@LoginActivity, "OOPS! Sigin Unsuccessful.", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun forgetpass(){
        val email = username!!.text.toString().trim { it <= ' ' }
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter email id.", Toast.LENGTH_SHORT).show()
            return
        }

        firebaseAuth!!.sendPasswordResetEmail(email).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(this@LoginActivity, "Please check your email.", Toast.LENGTH_SHORT).show()
            } else {
                val message = task.exception!!.message
                Toast.makeText(this@LoginActivity, "Procedure Unsuccessful: $message", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onClick(v: View) {

        if (v === sigin) {
            userLogin()
        }
        if (v === signuphp) {
            finish() //Closes the activity from background
            startActivity(Intent(this, RegistrationPage::class.java))
        }
        if(v == fgtpss){
            forgetpass()
        }

    }
}
