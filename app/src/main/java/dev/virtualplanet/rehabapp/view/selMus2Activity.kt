package dev.virtualplanet.rehabapp.view

import android.annotation.SuppressLint
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

class selMus2Activity : AppCompatActivity() {

    private val controller = Controller

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sel_mus2)
        //var prueba : TextView = findViewById(R.id.prueba)
        var tit : TextView = findViewById(R.id.titulo)
        lateinit var tostada : Toast
        val builder = AlertDialog.Builder(this@selMus2Activity, R.style.AlertDialog)
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
                var i : Intent = Intent(this@selMus2Activity, ViewExercicesActivity::class.java)
                when (p1.action) {
                    MotionEvent.ACTION_DOWN -> {
                        //prueba.text = "x: " + x1 + " y: " + y1
                        if (x1>0.587 && x1<0.708 && y1>0.26 && y1<0.4){
                            //prueba.text = "x: " + x1 + " y: " + y1
                            imagen.setImageResource(R.drawable.hombro_derecho)
                            tostada = Toast.makeText(applicationContext, "Hombro Derecho", Toast.LENGTH_LONG)
                            tostada.show()
                        }
                        else if (x1>0.287 && x1<0.416 && y1>0.26 && y1<0.4){
                            //prueba.text = "x: " + x1 + " y: " + y1
                            imagen.setImageResource(R.drawable.hombro_izq)
                            tostada = Toast.makeText(applicationContext, "Hombro Izquierdo", Toast.LENGTH_LONG)
                            tostada.show()
                        }
                        else if(x1>0.645 && x1<0.75 && y1>0.45 && y1<0.5){
                            //prueba.text = "x: " + x1 + " y: " + y1
                            imagen.setImageResource(R.drawable.codo_derecho)
                            tostada = Toast.makeText(applicationContext, "Codo Derecho", Toast.LENGTH_LONG)
                            tostada.show()
                        }
                        else if(x1>0.25 && x1<0.334 && y1>0.45 && y1<0.5){
                            //prueba.text = "x: " + x1 + " y: " + y1
                            imagen.setImageResource(R.drawable.codo_izq)
                            tostada = Toast.makeText(applicationContext, "Codo Izquierdo", Toast.LENGTH_LONG)
                            tostada.show()
                        }
                        else if(x1>0.69 && x1<0.787 && y1>0.5 && y1<0.6){
                            //prueba.text = "x: " + x1 + " y: " + y1
                            imagen.setImageResource(R.drawable.muneca_derecha)
                            tostada = Toast.makeText(applicationContext, "Muñeca Derecha", Toast.LENGTH_LONG)
                            tostada.show()
                        }
                        else if(x1>0.2 && x1<0.3 && y1>0.5 && y1<0.6){
                            //prueba.text = "x: " + x1 + " y: " + y1
                            imagen.setImageResource(R.drawable.muneca_izq)
                            tostada = Toast.makeText(applicationContext, "Muñeca Izquierda", Toast.LENGTH_LONG)
                            tostada.show()
                        }
                        else if(x1>0.55 && x1<0.7 && y1>0.65 && y1<0.8){
                            //prueba.text = "x: " + x1 + " y: " + y1
                            imagen.setImageResource(R.drawable.rodilla_derecha)
                            tostada = Toast.makeText(applicationContext, "Rodilla Derecha", Toast.LENGTH_LONG)
                            tostada.show()
                        }
                        else if(x1>0.375 && x1<0.5 && y1>0.65 && y1<0.8){
                            //prueba.text = "x: " + x1 + " y: " + y1
                            imagen.setImageResource(R.drawable.rodilla_izq)
                            tostada = Toast.makeText(applicationContext, "Rodilla Izquierda", Toast.LENGTH_LONG)
                            tostada.show()
                        }
                        else if(x1>0.54 && x1<0.7 && y1>0.84 && y1<1.0){
                            //prueba.text = "x: " + x1 + " y: " + y1
                            imagen.setImageResource(R.drawable.tobillo_derecho)
                            tostada = Toast.makeText(applicationContext, "Tobillo Derecho", Toast.LENGTH_LONG)
                            tostada.show()
                        }
                        else if(x1>0.35 && x1<0.5 && y1>0.84 && y1<1.0){
                            //prueba.text = "x: " + x1 + " y: " + y1
                            imagen.setImageResource(R.drawable.tobillo_izq)
                            tostada = Toast.makeText(applicationContext, "Tobillo Izquierdo", Toast.LENGTH_LONG)
                            tostada.show()
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
                                i.putExtra("musculo", "hombro_derecho")
                                setResult(Activity.RESULT_OK, intent)
                                finish()
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
                            builder.setTitle("Confirmar").setMessage("Estas seguro de que deseas seleccionar el hombro derecho").setPositiveButton("SÍ"){
                                    dialog, which ->
                                i.putExtra("musculo", "hombro_izquierdo")
                                setResult(Activity.RESULT_OK, intent)
                                finish()
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
                            builder.setTitle("Confirmar").setMessage("Estas seguro de que deseas seleccionar el hombro derecho").setPositiveButton("SÍ"){
                                    dialog, which ->
                                i.putExtra("musculo", "codo_derecho")
                                setResult(Activity.RESULT_OK, intent)
                                finish()
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
                            builder.setTitle("Confirmar").setMessage("Estas seguro de que deseas seleccionar el hombro derecho").setPositiveButton("SÍ"){
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
                            builder.setTitle("Confirmar").setMessage("Estas seguro de que deseas seleccionar el hombro derecho").setPositiveButton("SÍ"){
                                    dialog, which ->
                                i.putExtra("musculo", "muneca_derecha")
                                setResult(Activity.RESULT_OK, intent)
                                finish()
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
                            builder.setTitle("Confirmar").setMessage("Estas seguro de que deseas seleccionar el hombro derecho").setPositiveButton("SÍ"){
                                    dialog, which ->
                                i.putExtra("musculo", "muneca_izq")
                                setResult(Activity.RESULT_OK, intent)
                                finish()
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
                            builder.setTitle("Confirmar").setMessage("Estas seguro de que deseas seleccionar el hombro derecho").setPositiveButton("SÍ"){
                                    dialog, which ->
                                i.putExtra("musculo", "rodilla_derecha")
                                setResult(Activity.RESULT_OK, intent)
                                finish()
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
                            builder.setTitle("Confirmar").setMessage("Estas seguro de que deseas seleccionar el hombro derecho").setPositiveButton("SÍ"){
                                    dialog, which ->
                                i.putExtra("musculo", "rodilla_izquierda")
                                setResult(Activity.RESULT_OK, intent)
                                finish()
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
                            builder.setTitle("Confirmar").setMessage("Estas seguro de que deseas seleccionar el hombro derecho").setPositiveButton("SÍ"){
                                    dialog, which ->
                                i.putExtra("musculo", "tobillo_derecho")
                                setResult(Activity.RESULT_OK, intent)
                                finish()
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
                            builder.setTitle("Confirmar").setMessage("Estas seguro de que deseas seleccionar el hombro derecho").setPositiveButton("SÍ"){
                                    dialog, which ->
                                i.putExtra("musculo", "tobillo_izquierdo")
                                setResult(Activity.RESULT_OK, intent)
                                finish()
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
                                i.putExtra("musculo", "hombro_derecho")
                                setResult(Activity.RESULT_OK, intent)
                                finish()
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
                            builder.setTitle("Confirmar").setMessage("Estas seguro de que deseas seleccionar el hombro derecho").setPositiveButton("SÍ"){
                                    dialog, which ->
                                i.putExtra("musculo", "hombro_izquierdo")
                                setResult(Activity.RESULT_OK, intent)
                                finish()
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
                            builder.setTitle("Confirmar").setMessage("Estas seguro de que deseas seleccionar el hombro derecho").setPositiveButton("SÍ"){
                                    dialog, which ->
                                i.putExtra("musculo", "codo_derecho")
                                setResult(Activity.RESULT_OK, intent)
                                finish()
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
                            builder.setTitle("Confirmar").setMessage("Estas seguro de que deseas seleccionar el hombro derecho").setPositiveButton("SÍ"){
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
                            builder.setTitle("Confirmar").setMessage("Estas seguro de que deseas seleccionar el hombro derecho").setPositiveButton("SÍ"){
                                    dialog, which ->
                                i.putExtra("musculo", "muneca_derecha")
                                setResult(Activity.RESULT_OK, intent)
                                finish()
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
                            builder.setTitle("Confirmar").setMessage("Estas seguro de que deseas seleccionar el hombro derecho").setPositiveButton("SÍ"){
                                    dialog, which ->
                                i.putExtra("muneca_izquierda", "muneca_izquierda")
                                startActivity(i)
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
                            builder.setTitle("Confirmar").setMessage("Estas seguro de que deseas seleccionar el hombro derecho").setPositiveButton("SÍ"){
                                    dialog, which ->
                                i.putExtra("musculo", "rodilla_derecha")
                                setResult(Activity.RESULT_OK, intent)
                                finish()
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
                            builder.setTitle("Confirmar").setMessage("Estas seguro de que deseas seleccionar el hombro derecho").setPositiveButton("SÍ"){
                                    dialog, which ->
                                i.putExtra("musculo", "rodilla_izquierda")
                                setResult(Activity.RESULT_OK, intent)
                                finish()
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
                            builder.setTitle("Confirmar").setMessage("Estas seguro de que deseas seleccionar el hombro derecho").setPositiveButton("SÍ"){
                                    dialog, which ->
                                i.putExtra("musculo", "tobillo_derecho")
                                setResult(Activity.RESULT_OK, intent)
                                finish()
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
                            builder.setTitle("Confirmar").setMessage("Estas seguro de que deseas seleccionar el hombro derecho").setPositiveButton("SÍ"){
                                    dialog, which ->
                                i.putExtra("musculo", "tobillo_izquierdo")
                                setResult(Activity.RESULT_OK, intent)
                                finish()
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


    fun goBack(view: View) {
        val intent = Intent(this, SelectMuscleActivity::class.java)
        startActivity(intent)
    }
}
