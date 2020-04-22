package dev.virtualplanet.rehabapp.view

import android.R.attr.data
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.view.get
import androidx.core.view.setPadding
import dev.virtualplanet.rehabapp.R
import kotlinx.android.synthetic.main.delete_muscle_alert.view.*


class Adaptador : BaseAdapter {
    lateinit var actividad : Activity
    var lista = ArrayList<String>()

    constructor(actividad: Activity, lista: ArrayList<String>) : super() {
        this.actividad = actividad
        this.lista = lista
    }

    override fun getView(pos: Int, view: View?, p2: ViewGroup?): View {
        var inflater : LayoutInflater  = actividad.layoutInflater
        var vi: View? = view
        if (vi == null)  {
            vi = inflater.inflate(R.layout.list_item, null)
        }
        vi!!.findViewById<TextView>(R.id.list_item_header).text = lista[pos]
        vi!!.findViewById<Button>(R.id.list_item_play).setOnClickListener {
            var i : Intent = Intent(vi!!.context, ViewExercicesActivity::class.java).putExtra("exercice", lista[pos])
            vi!!.context.startActivity(i)
        }
        vi!!.findViewById<Button>(R.id.list_item_delete).setOnClickListener {
            val builder = AlertDialog.Builder(vi!!.context, R.style.AlertDialog)
            builder.setView(inflater.inflate(R.layout.delete_muscle_alert, null))
            val dialog: AlertDialog = builder.create()
            var alertView = inflater.inflate(R.layout.delete_muscle_alert, null)
            alertView.findViewById<TextView>(R.id.delete_muscle_alert_header).text = "¿Seguro que quieres eliminar " + lista[pos] + "?"
            alertView.findViewById<Button>(R.id.delete_muscle_alert_cancel).setOnClickListener {
                dialog.cancel()
            }
            alertView.findViewById<Button>(R.id.delete_muscle_alert_confirm).setOnClickListener {
                dialog.cancel()
                //TODO REMOVE FROM SHARED AND UPDATE
            }

            dialog.setView(alertView)
            dialog.show()
        }
        return vi
    }



    override fun getItem(p0: Int): Any {
        return lista[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return lista.size
    }
}