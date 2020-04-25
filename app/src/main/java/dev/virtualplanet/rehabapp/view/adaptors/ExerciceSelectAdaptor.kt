package dev.virtualplanet.rehabapp.view.adaptors

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import dev.virtualplanet.rehabapp.R
import dev.virtualplanet.rehabapp.controller.Controller
import dev.virtualplanet.rehabapp.view.MainExerciciActivity

class ExerciceSelectAdaptor : BaseAdapter {
    private var activity : MainExerciciActivity
    private var list = ArrayList<String>()

    constructor(actividad: MainExerciciActivity, lista: ArrayList<String>) : super() {
        this.activity = actividad
        this.list = lista
    }

    override fun getView(pos: Int, view: View?, p2: ViewGroup?): View {
        val inflater : LayoutInflater  = activity.layoutInflater
        var vi: View? = view
        if (vi == null)  {
            vi = inflater.inflate(R.layout.sexer_item, null)
        }
        vi!!.findViewById<TextView>(R.id.dpm).text = list[pos]

        return vi
    }



    override fun getItem(p0: Int): Any {
        return list[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return list.size
    }
}