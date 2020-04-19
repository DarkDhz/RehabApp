package dev.virtualplanet.rehabapp.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import dev.virtualplanet.rehabapp.R
import dev.virtualplanet.rehabapp.controller.Controller
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

            val age = intent.getStringExtra("AGE")
            val height = intent.getStringExtra("HEIGHT")
            val weight = intent.getStringExtra("WEIGHT")
            val wheel = intent.getBooleanExtra("WHEEL", false)

            Controller.changeProfile(this, age, height, weight, wheel)

        } else {
            Controller.loadProfile(this)
        }
    }

    fun returnToMain(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
