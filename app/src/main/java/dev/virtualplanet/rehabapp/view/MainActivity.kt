package dev.virtualplanet.rehabapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import dev.virtualplanet.rehabapp.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //CHECK IF USER IS LOGED IF NOT requiereLogin()
    }

    fun goToProfileActivity(view: View) {
        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)

    }

    fun requiereLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    fun goToExercicesActivity(view: View) {
        val intent = Intent(this, MainExerciciActivity::class.java)
        startActivity(intent)
    }

    fun goToProgressActivity(view: View) {
        val intent = Intent(this, ProgressActivity::class.java)
        startActivity(intent)
    }

    fun goToSettingsActivity(view: View) {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }

}
