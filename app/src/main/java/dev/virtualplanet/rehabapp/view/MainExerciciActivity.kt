package dev.virtualplanet.rehabapp.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import dev.virtualplanet.rehabapp.R


class MainExerciciActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_exercici)

        /*applicationContext.deleteSharedPreferences("USER")
        val pref = applicationContext.getSharedPreferences("USER", Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString("NAME", "ARNAU")
        editor.apply()*/
        
    }
    fun goToMuscle(view: View) {
        val intent = Intent(this, SelectMuscleActivity::class.java)
        startActivity(intent)
    }

}
