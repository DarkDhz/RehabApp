package dev.virtualplanet.rehabapp.view


import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import dev.virtualplanet.rehabapp.R
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner

import dev.virtualplanet.rehabapp.controller.Controller


class MainExerciciActivity : AppCompatActivity() {

    private val controller = Controller

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_exercici)


        var choice = false

        /*applicationContext.deleteSharedPreferences("USER")
        val pref = applicationContext.getSharedPreferences("USER", Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString("NAME", "ARNAU")
        editor.apply()*/

        val option = findViewById<Spinner>(R.id.spinner)

        val options = arrayOf("Full-Body", "Hombro")
        option.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, options)



    }

    fun startExercices(view: View) {
        val intent = Intent(this, ViewExercicesActivity::class.java)
        val option = findViewById<Spinner>(R.id.spinner)
        if (option.selectedItemPosition > 1) {
            intent.putExtra("Full-Body", "fullbody")

        } else {
            intent.putExtra("Rodilla", "rodilla")

        }
        startActivity(intent)


    }

    fun goToMuscle(view: View) {
        val intent = Intent(this, selMus2Activity::class.java)
        startActivity(intent)
    }


}





