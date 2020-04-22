package dev.virtualplanet.rehabapp.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import dev.virtualplanet.rehabapp.R
import dev.virtualplanet.rehabapp.controller.Controller

class SelectMuscleActivity : AppCompatActivity() {

    private val controller = Controller

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_muscle)
        //var prueba : TextView = findViewById(R.id.prueba)
        var tit : TextView = findViewById(R.id.titulo)
        lateinit var tostada : Toast
        val builder = AlertDialog.Builder(this@SelectMuscleActivity, R.style.AlertDialog)
        var displayMetrics : DisplayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        var context = this
        //this.prueba.text = "x: " + displayMetrics.widthPixels + " y: " + displayMetrics.heightPixels
        var imagen : ImageView = findViewById(R.id.cuerpo)
        imagen.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
                var x : Float = p1!!.rawX
                var y : Float = p1.rawY
                var x1 : Float = x/displayMetrics.widthPixels
                var y1 : Float = y/displayMetrics.heightPixels
                var i : Intent = getIntent()
                when (p1.action) {
                    MotionEvent.ACTION_DOWN -> {
                        //prueba.text = "x: " + x1 + " y: " + y1
                        if (x1>0.587 && x1<0.708 && y1>0.26 && y1<0.4){
                            //prueba.text = "x: " + x1 + " y: " + y1
                            imagen.setImageResource(R.drawable.hombro_derecho)
                        }
                        else if (x1>0.287 && x1<0.416 && y1>0.26 && y1<0.4){
                            //prueba.text = "x: " + x1 + " y: " + y1
                            imagen.setImageResource(R.drawable.hombro_izq)
                        }
                        else if(x1>0.645 && x1<0.75 && y1>0.45 && y1<0.5){
                            //prueba.text = "x: " + x1 + " y: " + y1
                            imagen.setImageResource(R.drawable.codo_derecho)
                        }
                        else if(x1>0.25 && x1<0.334 && y1>0.45 && y1<0.5){
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

                        if (x1>0.587 && x1<0.708 && y1>0.26 && y1<0.4){
                            //prueba.text = "x: " + x1 + " y: " + y1
                            imagen.setImageResource(R.drawable.hombro_derecho)
                            builder.setTitle("Confirmar").setMessage("Estas seguro de que deseas seleccionar el hombro derecho").setPositiveButton("SÍ"){
                                    dialog, which ->
                                Controller.addSavedExercices(context, "Hombro derecho")
                                imagen.setImageResource(R.drawable.original)
                            }.setNegativeButton("NO"){dialog,which ->
                                Toast.makeText(applicationContext,"Selecciona otro",Toast.LENGTH_SHORT).show()
                                imagen.setImageResource(R.drawable.original)
                            }
                            val dialogo: AlertDialog = builder.create()
                            dialogo.show()
                        }
                        else if (x1>0.287 && x1<0.416 && y1>0.26 && y1<0.4){
                            //prueba.text = "x: " + x1 + " y: " + y1
                            imagen.setImageResource(R.drawable.hombro_izq)
                            builder.setTitle("Confirmar").setMessage("Estas seguro de que deseas seleccionar el hombro izquierdo").setPositiveButton("SÍ"){
                                    dialog, which ->
                                Controller.addSavedExercices(context, "Hombro izquierdo")
                                imagen.setImageResource(R.drawable.original)
                            }.setNegativeButton("NO"){dialog,which ->
                                Toast.makeText(applicationContext,"Selecciona otro",Toast.LENGTH_SHORT).show()
                                imagen.setImageResource(R.drawable.original)
                            }
                            val dialogo: AlertDialog = builder.create()
                            dialogo.show()
                        }
                        else if(x1>0.645 && x1<0.75 && y1>0.45 && y1<0.5){
                            //prueba.text = "x: " + x1 + " y: " + y1
                            imagen.setImageResource(R.drawable.codo_derecho)
                            builder.setTitle("Confirmar").setMessage("Estas seguro de que deseas seleccionar el codo derecho").setPositiveButton("SÍ"){
                                    dialog, which ->
                                Controller.addSavedExercices(context, "Codo derecho")
                                imagen.setImageResource(R.drawable.original)
                            }.setNegativeButton("NO"){dialog,which ->
                                Toast.makeText(applicationContext,"Selecciona otro",Toast.LENGTH_SHORT).show()
                                imagen.setImageResource(R.drawable.original)
                            }
                            val dialogo: AlertDialog = builder.create()
                            dialogo.show()
                        }
                        else if(x1>0.25 && x1<0.334 && y1>0.45 && y1<0.5){
                            //prueba.text = "x: " + x1 + " y: " + y1
                            imagen.setImageResource(R.drawable.codo_izq)
                            builder.setTitle("Confirmar").setMessage("Estas seguro de que deseas seleccionar el codo izquierdo").setPositiveButton("SÍ"){
                                    dialog, which ->
                                i.putExtra("musculo", "codo_izquierdo")
                                setResult(Activity.RESULT_OK, intent)
                                finish()
                            }.setNegativeButton("NO"){dialog,which ->
                                Toast.makeText(applicationContext,"Selecciona otro",Toast.LENGTH_SHORT).show()
                                imagen.setImageResource(R.drawable.original)
                            }
                            val dialogo: AlertDialog = builder.create()
                            dialogo.show()
                        }
                        else if(x1>0.69 && x1<0.787 && y1>0.5 && y1<0.6){
                            //prueba.text = "x: " + x1 + " y: " + y1
                            imagen.setImageResource(R.drawable.muneca_derecha)
                            builder.setTitle("Confirmar").setMessage("Estas seguro de que deseas seleccionar la muñeca derecha").setPositiveButton("SÍ"){
                                    dialog, which ->
                                Controller.addSavedExercices(context, "Muñeca derecha")
                                imagen.setImageResource(R.drawable.original)
                            }.setNegativeButton("NO"){dialog,which ->
                                Toast.makeText(applicationContext,"Selecciona otro",Toast.LENGTH_SHORT).show()
                                imagen.setImageResource(R.drawable.original)
                            }
                            val dialogo: AlertDialog = builder.create()
                            dialogo.show()
                        }
                        else if(x1>0.2 && x1<0.3 && y1>0.5 && y1<0.6){
                            //prueba.text = "x: " + x1 + " y: " + y1
                            imagen.setImageResource(R.drawable.muneca_izq)
                            builder.setTitle("Confirmar").setMessage("Estas seguro de que deseas seleccionar la muñeza izquierda").setPositiveButton("SÍ"){
                                    dialog, which ->
                                Controller.addSavedExercices(context, "Muñeca izquierda")
                                imagen.setImageResource(R.drawable.original)
                            }.setNegativeButton("NO"){dialog,which ->
                                Toast.makeText(applicationContext,"Selecciona otro",Toast.LENGTH_SHORT).show()
                                imagen.setImageResource(R.drawable.original)
                            }
                            val dialogo: AlertDialog = builder.create()
                            dialogo.show()
                        }
                        else if(x1>0.55 && x1<0.7 && y1>0.65 && y1<0.8){
                            //prueba.text = "x: " + x1 + " y: " + y1
                            imagen.setImageResource(R.drawable.rodilla_derecha)
                            builder.setTitle("Confirmar").setMessage("Estas seguro de que deseas seleccionar la rodilla derecha").setPositiveButton("SÍ"){
                                    dialog, which ->
                                Controller.addSavedExercices(context, "Rodilla derecha")
                                imagen.setImageResource(R.drawable.original)
                            }.setNegativeButton("NO"){dialog,which ->
                                Toast.makeText(applicationContext,"Selecciona otro",Toast.LENGTH_SHORT).show()
                                imagen.setImageResource(R.drawable.original)
                            }
                            val dialogo: AlertDialog = builder.create()
                            dialogo.show()
                        }
                        else if(x1>0.375 && x1<0.5 && y1>0.65 && y1<0.8){
                            //prueba.text = "x: " + x1 + " y: " + y1
                            imagen.setImageResource(R.drawable.rodilla_izq)
                            builder.setTitle("Confirmar").setMessage("Estas seguro de que deseas seleccionar la rodilla izquierda").setPositiveButton("SÍ"){
                                    dialog, which ->
                                Controller.addSavedExercices(context, "Rodilla izquierda")
                                imagen.setImageResource(R.drawable.original)
                            }.setNegativeButton("NO"){dialog,which ->
                                Toast.makeText(applicationContext,"Selecciona otro",Toast.LENGTH_SHORT).show()
                                imagen.setImageResource(R.drawable.original)
                            }
                            val dialogo: AlertDialog = builder.create()
                            dialogo.show()
                        }
                        else if(x1>0.54 && x1<0.7 && y1>0.84 && y1<1.0){
                            //prueba.text = "x: " + x1 + " y: " + y1
                            imagen.setImageResource(R.drawable.tobillo_derecho)
                            builder.setTitle("Confirmar").setMessage("Estas seguro de que deseas seleccionar el tobillo derecho").setPositiveButton("SÍ"){
                                    dialog, which ->
                                Controller.addSavedExercices(context, "Tobillo derecho")
                                imagen.setImageResource(R.drawable.original)
                            }.setNegativeButton("NO"){dialog,which ->
                                Toast.makeText(applicationContext,"Selecciona otro",Toast.LENGTH_SHORT).show()
                                imagen.setImageResource(R.drawable.original)
                            }
                            val dialogo: AlertDialog = builder.create()
                            dialogo.show()
                        }
                        else if(x1>0.35 && x1<0.5 && y1>0.84 && y1<1.0){
                            //prueba.text = "x: " + x1 + " y: " + y1
                            imagen.setImageResource(R.drawable.tobillo_izq)
                            builder.setTitle("Confirmar").setMessage("Estas seguro de que deseas seleccionar el tobillo izquierdo").setPositiveButton("SÍ"){
                                    dialog, which ->
                                Controller.addSavedExercices(context, "Tobillo izquierdo")
                                imagen.setImageResource(R.drawable.original)
                            }.setNegativeButton("NO"){dialog,which ->
                                Toast.makeText(applicationContext,"Selecciona otro",Toast.LENGTH_SHORT).show()
                                imagen.setImageResource(R.drawable.original)
                            }
                            val dialogo: AlertDialog = builder.create()
                            dialogo.show()
                        }
                        else imagen.setImageResource(R.drawable.original)
                    }
                    MotionEvent.ACTION_UP -> {
                        if (x1>0.587 && x1<0.708 && y1>0.26 && y1<0.4){
                            //prueba.text = "x: " + x1 + " y: " + y1
                            imagen.setImageResource(R.drawable.hombro_derecho)
                            builder.setTitle("Confirmar").setMessage("Estas seguro de que deseas seleccionar el hombro derecho").setPositiveButton("SÍ"){
                                    dialog, which ->
                                Controller.addSavedExercices(context, "Hombro derecho")
                                imagen.setImageResource(R.drawable.original)
                            }.setNegativeButton("NO"){dialog,which ->
                                Toast.makeText(applicationContext,"Selecciona otro",Toast.LENGTH_SHORT).show()
                                imagen.setImageResource(R.drawable.original)
                            }
                            val dialogo: AlertDialog = builder.create()
                            dialogo.show()
                        }
                        else if (x1>0.287 && x1<0.416 && y1>0.26 && y1<0.4){
                            //prueba.text = "x: " + x1 + " y: " + y1
                            imagen.setImageResource(R.drawable.hombro_izq)
                            builder.setTitle("Confirmar").setMessage("Estas seguro de que deseas seleccionar el hombro izquierdo").setPositiveButton("SÍ"){
                                    dialog, which ->
                                Controller.addSavedExercices(context, "Hombro izquierdo")
                                imagen.setImageResource(R.drawable.original)
                            }.setNegativeButton("NO"){dialog,which ->
                                Toast.makeText(applicationContext,"Selecciona otro",Toast.LENGTH_SHORT).show()
                                imagen.setImageResource(R.drawable.original)
                            }
                            val dialogo: AlertDialog = builder.create()
                            dialogo.show()
                        }
                        else if(x1>0.645 && x1<0.75 && y1>0.45 && y1<0.5){
                            //prueba.text = "x: " + x1 + " y: " + y1
                            imagen.setImageResource(R.drawable.codo_derecho)
                            builder.setTitle("Confirmar").setMessage("Estas seguro de que deseas seleccionar el codo derecho").setPositiveButton("SÍ"){
                                    dialog, which ->
                                Controller.addSavedExercices(context, "Codo derecho")
                                imagen.setImageResource(R.drawable.original)
                            }.setNegativeButton("NO"){dialog,which ->
                                Toast.makeText(applicationContext,"Selecciona otro",Toast.LENGTH_SHORT).show()
                                imagen.setImageResource(R.drawable.original)
                            }
                            val dialogo: AlertDialog = builder.create()
                            dialogo.show()
                        }
                        else if(x1>0.25 && x1<0.334 && y1>0.45 && y1<0.5){
                            //prueba.text = "x: " + x1 + " y: " + y1
                            imagen.setImageResource(R.drawable.codo_izq)
                            builder.setTitle("Confirmar").setMessage("Estas seguro de que deseas seleccionar el codo izquierdo").setPositiveButton("SÍ"){
                                    dialog, which ->
                                Controller.addSavedExercices(context, "Codo izquierdo")
                                imagen.setImageResource(R.drawable.original)
                            }.setNegativeButton("NO"){dialog,which ->
                                Toast.makeText(applicationContext,"Selecciona otro",Toast.LENGTH_SHORT).show()
                                imagen.setImageResource(R.drawable.original)
                            }
                            val dialogo: AlertDialog = builder.create()
                            dialogo.show()
                        }
                        else if(x1>0.69 && x1<0.787 && y1>0.5 && y1<0.6){
                            //prueba.text = "x: " + x1 + " y: " + y1
                            imagen.setImageResource(R.drawable.muneca_derecha)
                            builder.setTitle("Confirmar").setMessage("Estas seguro de que deseas seleccionar la muñeca derecha").setPositiveButton("SÍ"){
                                    dialog, which ->
                                Controller.addSavedExercices(context, "Muñeca derecha")
                                imagen.setImageResource(R.drawable.original)
                            }.setNegativeButton("NO"){dialog,which ->
                                Toast.makeText(applicationContext,"Selecciona otro",Toast.LENGTH_SHORT).show()
                                imagen.setImageResource(R.drawable.original)
                            }
                            val dialogo: AlertDialog = builder.create()
                            dialogo.show()
                        }
                        else if(x1>0.2 && x1<0.3 && y1>0.5 && y1<0.6){
                            //prueba.text = "x: " + x1 + " y: " + y1
                            imagen.setImageResource(R.drawable.muneca_izq)
                            builder.setTitle("Confirmar").setMessage("Estas seguro de que deseas seleccionar el muñeca izquierda").setPositiveButton("SÍ"){
                                    dialog, which ->
                                    Controller.addSavedExercices(context, "Muñeca izquierda")
                                    imagen.setImageResource(R.drawable.original)
                            }.setNegativeButton("NO"){dialog,which ->
                                Toast.makeText(applicationContext,"Selecciona otro",Toast.LENGTH_SHORT).show()
                                imagen.setImageResource(R.drawable.original)
                            }
                            val dialogo: AlertDialog = builder.create()
                            dialogo.show()
                        }
                        else if(x1>0.55 && x1<0.7 && y1>0.65 && y1<0.8){
                            //prueba.text = "x: " + x1 + " y: " + y1
                            imagen.setImageResource(R.drawable.rodilla_derecha)
                            builder.setTitle("Confirmar").setMessage("Estas seguro de que deseas seleccionar la rodilla derecha").setPositiveButton("SÍ"){
                                    dialog, which ->
                                Controller.addSavedExercices(context, "Rodilla derecha")
                                imagen.setImageResource(R.drawable.original)
                            }.setNegativeButton("NO"){dialog,which ->
                                Toast.makeText(applicationContext,"Selecciona otro",Toast.LENGTH_SHORT).show()
                                imagen.setImageResource(R.drawable.original)
                            }
                            val dialogo: AlertDialog = builder.create()
                            dialogo.show()
                        }
                        else if(x1>0.375 && x1<0.5 && y1>0.65 && y1<0.8){
                            //prueba.text = "x: " + x1 + " y: " + y1
                            imagen.setImageResource(R.drawable.rodilla_izq)
                            builder.setTitle("Confirmar").setMessage("Estas seguro de que deseas seleccionar la rodilla izquierda").setPositiveButton("SÍ"){
                                    dialog, which ->
                                Controller.addSavedExercices(context, "Rodilla izquierda")
                                imagen.setImageResource(R.drawable.original)
                            }.setNegativeButton("NO"){dialog,which ->
                                Toast.makeText(applicationContext,"Selecciona otro",Toast.LENGTH_SHORT).show()
                                imagen.setImageResource(R.drawable.original)
                            }
                            val dialogo: AlertDialog = builder.create()
                            dialogo.show()
                        }
                        else if(x1>0.54 && x1<0.7 && y1>0.84 && y1<1.0){
                            //prueba.text = "x: " + x1 + " y: " + y1
                            imagen.setImageResource(R.drawable.tobillo_derecho)
                            builder.setTitle("Confirmar").setMessage("Estas seguro de que deseas seleccionar el tobillo derecho").setPositiveButton("SÍ"){
                                    dialog, which ->
                                Controller.addSavedExercices(context, "Tobillo derecho")
                                imagen.setImageResource(R.drawable.original)
                            }.setNegativeButton("NO"){dialog,which ->
                                Toast.makeText(applicationContext,"Selecciona otro",Toast.LENGTH_SHORT).show()
                                imagen.setImageResource(R.drawable.original)
                            }
                            val dialogo: AlertDialog = builder.create()
                            dialogo.show()
                        }
                        else if(x1>0.35 && x1<0.5 && y1>0.84 && y1<1.0){
                            //prueba.text = "x: " + x1 + " y: " + y1
                            imagen.setImageResource(R.drawable.tobillo_izq)
                            builder.setTitle("Confirmar").setMessage("Estas seguro de que deseas seleccionar el tobillo izquierdo").setPositiveButton("SÍ"){
                                    dialog, which ->
                                Controller.addSavedExercices(context, "Tobillo izquierdo")
                                imagen.setImageResource(R.drawable.original)
                            }.setNegativeButton("NO"){dialog,which ->
                                Toast.makeText(applicationContext,"Selecciona otro",Toast.LENGTH_SHORT).show()
                                imagen.setImageResource(R.drawable.original)
                            }
                            val dialogo: AlertDialog = builder.create()
                            dialogo.show()
                        }
                    }
                }
                return true
            }

        }
        )
    }


}
