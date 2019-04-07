package com.example.homeservice

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.support.v7.widget.CardView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import android.support.v7.widget.Toolbar
import android.widget.ViewFlipper
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainPage : AppCompatActivity(), View.OnClickListener {

    internal var v_fliper: ViewFlipper? = null
    private var homecv: CardView? = null
    private var pestcv: CardView? = null
    private var paintcv: CardView? = null
    private var skincv: CardView? = null
    private var haircv: CardView? = null
    private var makeupcv: CardView? = null
    private var customerref: FirebaseFirestore?= null

    private var firebaseAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        val toolbar = findViewById<Toolbar>(R.id.app_bar)
        setSupportActionBar(toolbar)

        val images = intArrayOf(
            R.drawable.painting,
            R.drawable.homecleaning,
            R.drawable.pestcontrol,
            R.drawable.skincare,
            R.drawable.haircare
        )
        v_fliper = findViewById(R.id.flippervw)

        for (image in images) {
            flipperImages(image)
        }

        //Defining cards
        homecv = findViewById(R.id.homecleaning_cv)
        pestcv = findViewById(R.id.pest_control_cv)
        paintcv = findViewById(R.id.painting_cv)
        skincv = findViewById(R.id.skincare_cv)
        haircv = findViewById(R.id.haircare_cv)
        makeupcv = findViewById(R.id.makeup_cv)


        //Adding onClick Listener;
        homecv!!.setOnClickListener(this)
        pestcv!!.setOnClickListener(this)
        paintcv!!.setOnClickListener(this)
        skincv!!.setOnClickListener(this)
        haircv!!.setOnClickListener(this)
        makeupcv!!.setOnClickListener(this)

    }

    fun flipperImages(image: Int) {
        val imageView = ImageView(this)
        imageView.setBackgroundResource(image)

        v_fliper?.addView(imageView)
        v_fliper?.setFlipInterval(4000)
        v_fliper?.isAutoStart = true

        //animation
        v_fliper?.setInAnimation(this, android.R.anim.slide_in_left)
        v_fliper?.setOutAnimation(this, android.R.anim.slide_out_right)
    }

    //Adds menu to the action bar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater = menuInflater

        firebaseAuth = FirebaseAuth.getInstance()
        customerref = FirebaseFirestore.getInstance()

        if (firebaseAuth?.getCurrentUser() == null) {
            //Toast.makeText(this,"CurrentUser: "+firebaseAuth!!.currentUser.toString(),Toast.LENGTH_SHORT).show()
            inflater.inflate(R.menu.menu, menu)
            Toast.makeText(this, "Please Login First!", Toast.LENGTH_SHORT).show()
        }
        else {
            inflater.inflate(R.menu.menu_after, menu)
        }

        return super.onCreateOptionsMenu(menu)
    }

    //function to be added for Onclick of menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val i:Intent
        return when(item.itemId){
            R.id.action_logout -> {
                FirebaseAuth.getInstance().signOut()
                Toast.makeText(this,"Logout Successfull!",Toast.LENGTH_SHORT).show()
                i = Intent(this,LoginActivity::class.java)
                startActivity(i)
                true
            }
            R.id.action_login ->{
                i= Intent(this,LoginActivity::class.java)
                startActivity(i)
                true
            }
            R.id.action_cart->{
                //TODO:ADD FUNCTIONS ON CLICK OF ADD TO CART
                i = Intent(this,Cart::class.java)
                startActivity(i)
                //Toast.makeText(this,"com.example.homeservice.Cart Page Not created yet.",Toast.LENGTH_LONG).show()
                true
            }
            else->super.onOptionsItemSelected(item)
        }
        //return super.onOptionsItemSelected(item)
    }

    override fun onClick(v: View?) {
        val i: Intent
        //val t = Toast.makeText(this, "Hello There!", Toast.LENGTH_SHORT)

        if (v != null) {
            when (v.id) {

                R.id.homecleaning_cv -> {i=Intent(this,HomeCleaningPage::class.java).apply {
                    putExtra("CardSelected","homecv")
                }
                    startActivity(i)}

                R.id.pest_control_cv -> {i=Intent(this,HomeCleaningPage::class.java).apply {
                    putExtra("CardSelected","pestcv")
                }
                    startActivity(i)}

                R.id.painting_cv -> {i=Intent(this,HomeCleaningPage::class.java).apply {
                    putExtra("CardSelected","paintcv")
                }
                    startActivity(i)}

                R.id.skincare_cv -> {i=Intent(this,HomeCleaningPage::class.java).apply {
                    putExtra("CardSelected","skincv")
                }
                    startActivity(i)}

                R.id.haircare_cv -> {i=Intent(this,HomeCleaningPage::class.java).apply {
                    putExtra("CardSelected","haircv")
                }
                    startActivity(i)}

                R.id.makeup_cv -> {i=Intent(this,HomeCleaningPage::class.java).apply {
                    putExtra("CardSelected","makeupcv")
                }
                    startActivity(i)}
            }
        }
        else
            Toast.makeText(this,"Empty",Toast.LENGTH_SHORT).show()
    }
}
