package dev.virtualplanet.rehabapp.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import dev.virtualplanet.rehabapp.R
import dev.virtualplanet.rehabapp.controller.Controller
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.activity_profile.*


class ProfileActivity : AppCompatActivity() {

    private val controller = Controller

    private val data = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.init()
        setContentView(R.layout.activity_profile)
    }

    fun editProfile(view: View) {
        val intent = Intent(this, EditProfileActivity::class.java)
        startActivity(intent)

    }

    private fun init() {
        if (intent.extras != null) {


            //val age = intent.getStringExtra("AGE")
            //Toast.makeText(this, age, Toast.LENGTH_LONG).show()
            //findViewById<TextView>(R.id.textView_Age_Value).text = age
            //Controller.changeProfile(this, age, "0", "0", false)
            /*
            val height = intent.getStringExtra("HEIGHT")
            val weight = intent.getStringExtra("WEIGHT")
            val wheel = intent.getBooleanExtra("WHEEL", false)

            Controller.changeProfile(this, age, height, weight, wheel)
            */
        } else {
           // this.findViewById<TextView>(R.id.textView_Age_Value).text = "33"
            //Controller.loadProfile(this)
        }
    }

    fun returnToMain(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
