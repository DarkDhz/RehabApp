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

        lateinit var option: Spinner
        var choice = false

        /*applicationContext.deleteSharedPreferences("USER")
        val pref = applicationContext.getSharedPreferences("USER", Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString("NAME", "ARNAU")
        editor.apply()*/

        option = findViewById(R.id.spinner)

        var options = arrayOf("Full-Body", "Hombro")
        option.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, options)

        option.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

        }


    }
    fun startExercise(view: View) {
        val intent = Intent(this, ViewExerciseActivity::class.java)
        if (option.selectedItemPosition > 1) {
            intent.putExtra("Full-Body", "fullbody")

        } else
            intent.putExtra("Rodilla", "rodilla")

    }
        startActivity(intent)
    }

    fun goToMuscle(view: View) {
        val intent = Intent(this, selMus2Activity::class.java)
        startActivity(intent)
    }


}





