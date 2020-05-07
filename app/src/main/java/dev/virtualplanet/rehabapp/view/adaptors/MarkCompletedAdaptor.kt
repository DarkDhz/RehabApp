package dev.virtualplanet.rehabapp.view.adaptors

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import dev.virtualplanet.rehabapp.R
import dev.virtualplanet.rehabapp.controller.Controller
import dev.virtualplanet.rehabapp.model.Exercice
import dev.virtualplanet.rehabapp.model.ExerciceList
import dev.virtualplanet.rehabapp.view.MainExerciciActivity
import dev.virtualplanet.rehabapp.view.ViewExercicesActivity

class MarkCompletedAdaptor : BaseAdapter {
    private var activity : ViewExercicesActivity
    private var list : ExerciceList
    private var marked = 0

    constructor(actividad: ViewExercicesActivity, lista: ExerciceList) : super() {
        this.activity = actividad
        this.list = lista
    }

    override fun getView(pos: Int, view: View?, p2: ViewGroup?): View {
        val inflater : LayoutInflater = activity.layoutInflater
        var vi: View? = view
        if (vi == null)  {
            vi = inflater.inflate(R.layout.mkcomp_item, null)
        }
        val markButton = vi!!.findViewById<CheckBox>(R.id.mkcomp_name)
        markButton.text = list.content[pos].name
        Log.i("myapp23", marked.toString())
        markButton.setOnClickListener {
            if(markButton.isChecked) {
                marked++
            } else {
                marked--
            }
        }
        return vi
    }



    override fun getItem(pos: Int): Exercice {
        return list.content[pos]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return list.content.size
    }

    fun getMarkedCount() : Int {
        return marked
    }

}