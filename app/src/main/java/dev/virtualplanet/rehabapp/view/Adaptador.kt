package dev.virtualplanet.rehabapp.view

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import dev.virtualplanet.rehabapp.R
import dev.virtualplanet.rehabapp.controller.Controller


class Adaptador : BaseAdapter {
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
            vi = inflater.inflate(R.layout.list_item, null)
        }
        vi!!.findViewById<TextView>(R.id.list_item_header).text = list[pos]
        vi!!.findViewById<Button>(R.id.list_item_play).setOnClickListener {
            val i : Intent = Intent(vi!!.context, ViewExercicesActivity::class.java).putExtra("exercice", list[pos])
            vi!!.context.startActivity(i)
        }
        vi!!.findViewById<Button>(R.id.list_item_delete).setOnClickListener {
            val builder = AlertDialog.Builder(vi!!.context, R.style.AlertDialog)
            builder.setView(inflater.inflate(R.layout.delete_muscle_alert, null))
            val dialog: AlertDialog = builder.create()
            var alertView = inflater.inflate(R.layout.delete_muscle_alert, null)
            alertView.findViewById<TextView>(R.id.delete_muscle_alert_header).text = "¿Seguro que quieres eliminar " + list[pos] + "?"
            alertView.findViewById<Button>(R.id.delete_muscle_alert_cancel).setOnClickListener {
                dialog.cancel()
            }
            alertView.findViewById<Button>(R.id.delete_muscle_alert_confirm).setOnClickListener {
                dialog.cancel()
                Controller.removeSavedExercice(activity, list[pos])
                activity.loadList()

            }

            dialog.setView(alertView)
            dialog.show()
        }
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