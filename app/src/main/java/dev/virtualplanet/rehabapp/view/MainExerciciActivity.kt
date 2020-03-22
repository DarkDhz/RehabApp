package dev.virtualplanet.rehabapp.view

import android.app.ListActivity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import dev.virtualplanet.rehabapp.R
import dev.virtualplanet.rehabapp.controller.Controller
import java.lang.AssertionError
import java.lang.reflect.Array


class MainExerciciActivity : ListActivity() {

    private val controller = Controller
    lateinit var musculo : String
    val musculos = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_exercici)
    }
    fun startExercise(view: View) {
        val intent = Intent(this, ViewExerciseAtivity::class.java)
        startActivity(intent)
    }

    fun goToMuscle(view: View) {
        val intent = Intent(this, selMus2Activity::class.java)
        startActivityForResult(intent,1234)
    }

    fun ListarMusclus(){
        var adapt : Adaptador = Adaptador(this, musculos)
        listAdapter=adapt
    }
    fun añadir(s : String){
        musculos.add(s)
        Log.d("LOLLLLLLLLLLLL", musculos[0])
        ListarMusclus()
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            musculo = data.getStringExtra("musculo")
            Log.d("AQQQQUUUUUUUUÏIIIIIIII", musculo)
        }
        añadir(musculo)
    }
}
