package dev.virtualplanet.rehabapp.view

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.setPadding
import dev.virtualplanet.rehabapp.R
import java.security.AccessController.getContext

class Adaptador : BaseAdapter {
    lateinit var actividad : Activity
    var lista = ArrayList<String>()

    constructor(actividad: Activity, lista: ArrayList<String>) : super() {
        this.actividad = actividad
        this.lista = lista
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var inflater : LayoutInflater  = actividad.layoutInflater
        var vista : View = inflater.inflate(android.R.layout.activity_list_item, null, true)
        var texto : TextView = vista.findViewById(android.R.id.text1)
        var icono : ImageView = vista.findViewById(android.R.id.icon)
        texto.setTextColor(Color.WHITE)
        texto.setText(lista[p0])
        
        vista.setPadding(15)
        texto.setTextSize(25F)
        icono.setImageResource(android.R.drawable.star_off)
        return vista
    }

    override fun getItem(p0: Int): Any {
        return 0
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return lista.size
    }
}