package com.example.homeservice

import android.annotation.TargetApi
import android.app.ActivityOptions
import android.app.ProgressDialog
import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Pair
import android.view.View
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

class UserdetailActivity : AppCompatActivity(), View.OnClickListener {

    private var customerref: FirebaseFirestore?= null
    private var current_user: FirebaseUser?= null
    private lateinit var current_user_email: String
    private var progressDialog: ProgressDialog? = null

    @TargetApi(Build.VERSION_CODES.ECLAIR)
    override fun onClick(v: View?) {
        if (v === submit_btn) {
            progressDialog!!.setMessage("Adding UserDetails")
            progressDialog!!.show()
            customerref = FirebaseFirestore.getInstance()
            current_user = FirebaseAuth.getInstance().currentUser
            current_user_email = current_user!!.email.toString()
            //var custid = customerref!!.collection("Customers").id

            val user = HashMap<String, Any>()
            user["FirstName"] = firstname?.text.toString()
            user["LastName"] = lastname?.text.toString()
            user["MobileNo"] = mobileno?.text.toString()
            user["Address"] = address?.text.toString()
            user["NoOfRooms"] = noofrooms?.text.toString()

            try {
                customerref!!.collection("Customers").document(current_user_email).set(user)
                    .addOnSuccessListener {
                        progressDialog!!.dismiss()
                        Toast.makeText(this@UserdetailActivity, "UserDetails Added Successfully!", Toast.LENGTH_SHORT).show()
                        val i = Intent(this, MainPage::class.java)
                        val pair = Pair.create<View,String>(submit_btn, "signupTrans")
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                            val options = ActivityOptions.makeSceneTransitionAnimation(this, pair)
                            startActivity(i, options.toBundle())
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                            finish()
                        } else {
                            startActivity(i)
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                            finish()
                        }
                    }
                    .addOnFailureListener{
                        Toast.makeText(this@UserdetailActivity, "Could not add data. Please Try Again.", Toast.LENGTH_LONG).show()
                        Log.w("UserdetailActivity", "Error getting documents: ")
                    }
            }catch (e: Exception){
                Log.w("UserdetailActivity", "Error getting documents: ",e)
            }
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

        progressDialog = ProgressDialog(this)

        firstname = findViewById(R.id.fnameet)
        lastname = findViewById(R.id.lnameet)
        mobileno = findViewById(R.id.contnoet)
        address = findViewById(R.id.addet)
        noofrooms = findViewById(R.id.roomnoet)

        submit_btn = findViewById(R.id.user_submit)

        submit_btn!!.setOnClickListener(this)
    }
}
