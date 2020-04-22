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
        //l = findViewById(android.R.id.list)

    }

    fun goToMuscle(view: View) {
        val intent = Intent(this, SelectMuscleActivity::class.java)
        startActivityForResult(intent,1234)
    }


    fun ListarMusclus(){
        var adapt = Adaptador(this, muscleList)
        listAdapter = adapt
    }
    fun añadir(s : String){
        if (s != "null" && !muscleList.contains(s)) {
            muscleList.add(s)
            ListarMusclus()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var muscle : String? = "null"
        if (data != null) {
            muscle = data.getStringExtra("musculo")
            when (muscle) {
                "hombro_derecho" -> muscle = "Hombro derecho"
                "hombro_izquierdo" -> muscle = "Hombro izquierdo"
                "codo_derecho" -> muscle = "Codo derecho"
                "codo_izquierdo" -> muscle = "Codo izquierdo"
                "muneca_derecha" -> muscle = "Muñeca derecha"
                "muneca_izq" -> muscle = "Muñeca izquierda"
                "rodilla_derecha" -> muscle = "Rodilla derecha"
                "rodilla_izquierda" -> muscle = "Rodilla izquierda"
                "tobillo_derecho" -> muscle = "Tobillo derecho"
                "tobillo_izquierdo" -> muscle = "Tobillo izquierdo"
            }
            añadir(muscle!!)
        }

    }
}





