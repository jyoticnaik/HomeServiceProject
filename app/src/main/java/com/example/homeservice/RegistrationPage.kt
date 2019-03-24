package com.example.homeservice

import android.app.ProgressDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegistrationPage : AppCompatActivity(), View.OnClickListener {

    private var customerref: FirebaseFirestore ?= null

    override fun onClick(v: View?) {
        if (v === buttonLogin) {
            //OnClick of button
            registerUser()
        }
    }

    private fun registerUser() {
        val email = editTextemail!!.text.toString().trim { it <= ' ' }
        val password = editTextpass!!.text.toString().trim { it <= ' ' }
        val cnfrmpass = editTextcnfrmpass!!.text.toString().trim { it <= ' ' }

        if (TextUtils.isEmpty(email)) {
            // if email is empty
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show()
            return
        }
        if (TextUtils.isEmpty(password)) {
            //if password is empty
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show()
            return
        }

        //if validations are ok
        //Registring user

        if (password == cnfrmpass) {
            progressDialog!!.setMessage("Registring User")
            progressDialog!!.show()

            firebaseAuth!!.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    //User successfully Registered
                    progressDialog!!.dismiss()
                    customerref!!.collection("Customers").document(email)
                    Toast.makeText(this@RegistrationPage, "Registration Successful!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@RegistrationPage, "Could not register. Please Try Again.", Toast.LENGTH_LONG).show()
                }
            }
        } else {
            Toast.makeText(this, "Please Confirm password correctly.", Toast.LENGTH_LONG).show()
        }
    }

    private var buttonLogin: Button? = null
    private var editTextemail: EditText? = null
    private var editTextpass: EditText? = null
    private var editTextcnfrmpass: EditText? = null

    private var progressDialog: ProgressDialog? = null

    private var firebaseAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration_page)

        firebaseAuth = FirebaseAuth.getInstance()
        customerref = FirebaseFirestore.getInstance()

        progressDialog = ProgressDialog(this)
        buttonLogin = findViewById(R.id.registrationBtn)
        editTextemail = findViewById(R.id.regEmailTxt)
        editTextpass = findViewById(R.id.regpasswordtxt)
        editTextcnfrmpass = findViewById(R.id.confirmpasswordtxt)


        buttonLogin!!.setOnClickListener(this)

    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}
