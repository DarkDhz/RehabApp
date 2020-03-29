package dev.virtualplanet.rehabapp.view
import android.app.ListActivity
import android.content.Intent
import android.os.Bundle
import android.view.View
import dev.virtualplanet.rehabapp.R
import android.widget.AdapterView
import android.widget.ListView

import dev.virtualplanet.rehabapp.controller.Controller


class MainExerciciActivity : ListActivity() {

    private val controller = Controller
    lateinit var musculo : String
    val musculos = ArrayList<String>()
    lateinit var l : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_exercici)
        l = findViewById(android.R.id.list)

    }

    fun goToMuscle(view: View) {
        val intent = Intent(this, selMus2Activity::class.java)
        startActivityForResult(intent,1234)
    }


    fun ListarMusclus(){
        var adapt = Adaptador(this, musculos)
        listAdapter = adapt
        l.setOnItemClickListener(AdapterView.OnItemClickListener(){ adapterView: AdapterView<*>, view1: View, i: Int, l: Long ->
            var i : Intent = Intent(this, ViewExercicesActivity::class.java)
            startActivity(i)
        })
    }
    fun añadir(s : String){
        musculos.add(s)
        ListarMusclus()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            musculo = data.getStringExtra("musculo")
        }
        añadir(musculo)
    }
}





