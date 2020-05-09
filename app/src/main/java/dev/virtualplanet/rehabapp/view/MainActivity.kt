package dev.virtualplanet.rehabapp.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.firestore.FirebaseFirestore
import dev.virtualplanet.rehabapp.R
import dev.virtualplanet.rehabapp.controller.Controller
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDateTime

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.init()

    }

    private fun init() {

    }

    fun goToProfileActivity(view: View) {
        val intent = Intent(view.context, ProfileActivity::class.java)
        startActivity(intent)

    }

    fun goToExercicesActivity(view: View) {
        val intent = Intent(view.context, MainExerciciActivity::class.java)
        startActivity(intent)
    }

    fun goToProgressActivity(view: View) {
        val intent = Intent(view.context, SelectStaticActivity::class.java)
        startActivity(intent)
    }

    fun goToSettingsActivity(view: View) {
        val intent = Intent(view.context, SettingsActivity::class.java)
        startActivity(intent)
    }

    override fun onBackPressed() {
        //BLOCKED FOR SECURITY
    }

}
