package dev.virtualplanet.rehabapp.view

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import dev.virtualplanet.rehabapp.R
import dev.virtualplanet.rehabapp.controller.Controller
import kotlinx.android.synthetic.main.activity_select_muscle.*

class selMus2Activity : AppCompatActivity() {

    private val controller = Controller

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sel_mus2)
        //var prueba : TextView = findViewById(R.id.prueba)
        var tit : TextView = findViewById(R.id.titulo)

        var displayMetrics : DisplayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        //this.prueba.text = "x: " + displayMetrics.widthPixels + " y: " + displayMetrics.heightPixels
        var imagen : ImageView = findViewById(R.id.cuerpo)
        imagen.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
                var x : Float = p1!!.rawX
                var y : Float = p1.rawY
                var x1 : Float = x/displayMetrics.widthPixels
                var y1 : Float = y/displayMetrics.heightPixels

                when (p1.action) {
                    MotionEvent.ACTION_DOWN -> {
                        //prueba.text = "x: " + x1 + " y: " + y1
                        if (x1>0.587 && x1<0.708 && y1>0.26 && y1<0.335){
                            //prueba.text = "x: " + x1 + " y: " + y1
                            imagen.setImageResource(R.drawable.hombro_derecho)
                        }
                        else if (x1>0.287 && x1<0.416 && y1>0.26 && y1<0.335){
                            //prueba.text = "x: " + x1 + " y: " + y1
                            imagen.setImageResource(R.drawable.hombro_izq)
                        }
                        else if(x1>0.645 && x1<0.75 && y1>0.318 && y1<0.45){
                            //prueba.text = "x: " + x1 + " y: " + y1
                            imagen.setImageResource(R.drawable.codo_derecho)
                        }
                        else if(x1>0.25 && x1<0.334 && y1>0.318 && y1<0.45){
                            //prueba.text = "x: " + x1 + " y: " + y1
                            imagen.setImageResource(R.drawable.codo_izq)
                        }
                        else if(x1>0.69 && x1<0.787 && y1>0.5 && y1<0.6){
                            //prueba.text = "x: " + x1 + " y: " + y1
                            imagen.setImageResource(R.drawable.muneca_derecha)
                        }
                        else if(x1>0.2 && x1<0.3 && y1>0.5 && y1<0.6){
                            //prueba.text = "x: " + x1 + " y: " + y1
                            imagen.setImageResource(R.drawable.muneca_izq)
                        }
                        else if(x1>0.55 && x1<0.7 && y1>0.65 && y1<0.8){
                            //prueba.text = "x: " + x1 + " y: " + y1
                            imagen.setImageResource(R.drawable.rodilla_derecha)
                        }
                        else if(x1>0.375 && x1<0.5 && y1>0.65 && y1<0.8){
                            //prueba.text = "x: " + x1 + " y: " + y1
                            imagen.setImageResource(R.drawable.rodilla_izq)
                        }
                        else if(x1>0.54 && x1<0.7 && y1>0.84 && y1<1.0){
                            //prueba.text = "x: " + x1 + " y: " + y1
                            imagen.setImageResource(R.drawable.tobillo_derecho)
                        }
                        else if(x1>0.35 && x1<0.5 && y1>0.84 && y1<1.0){
                            //prueba.text = "x: " + x1 + " y: " + y1
                            imagen.setImageResource(R.drawable.tobillo_izq)
                        }
                        else imagen.setImageResource(R.drawable.original)
                    }
                    MotionEvent.ACTION_MOVE -> {
                        p0!!.performClick()
                        //prueba.text = "x: " + x1 + " y: " + y1

                        if (x1>0.587 && x1<0.708 && y1>0.26 && y1<0.335){
                            //prueba.text = "x: " + x1 + " y: " + y1
                            imagen.setImageResource(R.drawable.hombro_derecho)
                        }
                        else if (x1>0.287 && x1<0.416 && y1>0.26 && y1<0.335){
                            //prueba.text = "x: " + x1 + " y: " + y1
                            imagen.setImageResource(R.drawable.hombro_izq)
                        }
                        else if(x1>0.645 && x1<0.75 && y1>0.318 && y1<0.45){
                            //prueba.text = "x: " + x1 + " y: " + y1
                            imagen.setImageResource(R.drawable.codo_derecho)
                        }
                        else if(x1>0.25 && x1<0.334 && y1>0.318 && y1<0.45){
                            //prueba.text = "x: " + x1 + " y: " + y1
                            imagen.setImageResource(R.drawable.codo_izq)
                        }
                        else if(x1>0.69 && x1<0.787 && y1>0.5 && y1<0.6){
                            //prueba.text = "x: " + x1 + " y: " + y1
                            imagen.setImageResource(R.drawable.muneca_derecha)
                        }
                        else if(x1>0.2 && x1<0.3 && y1>0.5 && y1<0.6){
                            //prueba.text = "x: " + x1 + " y: " + y1
                            imagen.setImageResource(R.drawable.muneca_izq)
                        }
                        else if(x1>0.55 && x1<0.7 && y1>0.65 && y1<0.8){
                            //prueba.text = "x: " + x1 + " y: " + y1
                            imagen.setImageResource(R.drawable.rodilla_derecha)
                        }
                        else if(x1>0.375 && x1<0.5 && y1>0.65 && y1<0.8){
                            //prueba.text = "x: " + x1 + " y: " + y1
                            imagen.setImageResource(R.drawable.rodilla_izq)
                        }
                        else if(x1>0.54 && x1<0.7 && y1>0.84 && y1<1.0){
                            //prueba.text = "x: " + x1 + " y: " + y1
                            imagen.setImageResource(R.drawable.tobillo_derecho)
                        }
                        else if(x1>0.35 && x1<0.5 && y1>0.84 && y1<1.0){
                            //prueba.text = "x: " + x1 + " y: " + y1
                            imagen.setImageResource(R.drawable.tobillo_izq)
                        }
                        else imagen.setImageResource(R.drawable.original)
                    }
                    MotionEvent.ACTION_UP -> {
                        prueba.text = "x: " + x1 + " y: " + y1
                        var intent  = Intent(this@selMus2Activity, Hombro_izquierdo::class.java)
                    }
                }
                return true
            }

        }
        )
    }


    fun goBack(view: View) {
        val intent = Intent(this, SelectMuscleActivity::class.java)
        startActivity(intent)
    }
}
