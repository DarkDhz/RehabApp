package dev.virtualplanet.rehabapp.view

import android.content.Intent
import android.graphics.ColorSpace
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import dev.virtualplanet.rehabapp.R
import dev.virtualplanet.rehabapp.model.User
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //CHECK IF USER IS LOGED IF NOT requiereLogin()


        /*val myDB = FirebaseFirestore.getInstance()

        val solarSystem = myDB.collection("solar_system")

        solarSystem.add(mapOf(
            "name" to "Mercury",
            "number" to 1,
            "gravity" to 3.7
        ))

        solarSystem.add(mapOf(
            "name" to "Venus",
            "number" to 2,
            "gravity" to 8.87
        ))

        myDB.collection("solar_system")
            .get().addOnSuccessListener {
                it.forEach {
                    this.exercice_bt.text = it.get("name").toString()
                }
            }
        */
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
