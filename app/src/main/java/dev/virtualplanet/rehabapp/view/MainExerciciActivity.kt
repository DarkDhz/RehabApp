package dev.virtualplanet.rehabapp.view
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import dev.virtualplanet.rehabapp.R

import dev.virtualplanet.rehabapp.controller.Controller
import dev.virtualplanet.rehabapp.view.adaptors.ExerciceSelectAdaptor
import dev.virtualplanet.rehabapp.view.adaptors.MuscleSelectAdaptor


class MainExerciciActivity : AppCompatActivity() {

    private val controller = Controller

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_exercici)
        loadList()
        //l = findViewById(android.R.id.list)

    }

    fun goToMuscle(view: View) {
        val intent = Intent(this, SelectMuscleActivity::class.java)
        startActivityForResult(intent,1234)
    }


    fun loadList() {
        val list = controller.loadSavedExercices(this)
        val adapt = MuscleSelectAdaptor(this, list)
        findViewById<ListView>(R.id.main_exercici_list).adapter = adapt
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        this.loadList()
    }

    fun goBack(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}





