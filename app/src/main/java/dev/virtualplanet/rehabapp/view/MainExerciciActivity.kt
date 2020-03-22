package dev.virtualplanet.rehabapp.view
import android.app.ListActivity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import dev.virtualplanet.rehabapp.R
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner

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

    fun startExercices(view: View) {
        val intent = Intent(this, ViewExercicesActivity::class.java)
        /*val option = findViewById<Spinner>(R.id.spinner)
        if (option.selectedItemPosition > 1) {
            intent.putExtra("Full-Body", "fullbody")

        } else {
            intent.putExtra("Rodilla", "rodilla")

        }
        startActivity(intent)
        */

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





