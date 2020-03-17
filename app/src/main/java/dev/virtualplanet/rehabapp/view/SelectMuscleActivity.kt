package dev.virtualplanet.rehabapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Display
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import dev.virtualplanet.rehabapp.R

class SelectMuscleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_muscle)
        var prueba : TextView = findViewById(R.id.prueba)
        var displayMetrics : DisplayMetrics = DisplayMetrics()
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics)
        prueba.setText("x: " + displayMetrics.widthPixels + " y: " + displayMetrics.heightPixels)
        var imagen : ImageView = findViewById(R.id.cuerpo)
        imagen.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
                var x : Float = p1!!.getRawX()
                var y : Float = p1!!.getRawY()
                var x1 : Float = x/displayMetrics.widthPixels
                when (p1?.action) {
                    MotionEvent.ACTION_DOWN -> {
                        prueba.setText("x: " + x + " y: " + y)
                        if (x1>0.55 && x1<0.65){
                            prueba.setText("Hombro izquierdo")
                            imagen.setImageResource(R.drawable.articulaciones_h_izq)
                        }
                    }
                    MotionEvent.ACTION_MOVE -> {
                        p0!!.performClick()
                        prueba.setText("x: " + x + " y: " + y)

                        if (x1>0.55 && x1<0.65){
                            prueba.setText("Hombro izquierdo")
                            imagen.setImageResource(R.drawable.articulaciones_h_izq)
                        }
                        else imagen.setImageResource(R.drawable.articulaciones)
                    }
                    MotionEvent.ACTION_UP -> {
                        prueba.setText("x: " + x + " y: " + y)
                        var intent : Intent = Intent(this@SelectMuscleActivity, Hombro_izquierdo::class.java)
                    }
                }
                return true
            }

        }
        )
        }
    }

