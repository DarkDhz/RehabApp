package dev.virtualplanet.rehabapp.view
import android.app.ListActivity
import android.content.Intent
import android.os.Bundle
import android.view.View
import dev.virtualplanet.rehabapp.R

import dev.virtualplanet.rehabapp.controller.Controller


class MainExerciciActivity : ListActivity() {

    private val controller = Controller
    private val muscleList = ArrayList<String>()
    //lateinit var l : ListView

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
        var adapt = Adaptador(this, list)
        listAdapter = adapt

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        this.loadList()
    }
}





