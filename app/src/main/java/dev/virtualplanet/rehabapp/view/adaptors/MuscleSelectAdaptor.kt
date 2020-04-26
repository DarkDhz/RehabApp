package dev.virtualplanet.rehabapp.view.adaptors

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import dev.virtualplanet.rehabapp.R
import dev.virtualplanet.rehabapp.controller.Controller
import dev.virtualplanet.rehabapp.view.MainExerciciActivity
import dev.virtualplanet.rehabapp.view.ViewExercicesActivity


class MuscleSelectAdaptor : BaseAdapter {
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
            //manageOpenAlert(vi, inflater, list[pos])
            val i : Intent = Intent(vi!!.context, ViewExercicesActivity::class.java).putExtra("exercice", list[pos])
            vi!!.context.startActivity(i)
        }
        vi!!.findViewById<Button>(R.id.list_item_delete).setOnClickListener {
            manageDelete(vi, inflater, list[pos])
        }
        return vi
    }

    private fun manageOpenAlert(vi : View?, inflater: LayoutInflater, pos : String) {
        val builder = AlertDialog.Builder(vi!!.context, R.style.AlertDialog)
        builder.setView(inflater.inflate(R.layout.select_exercice_alert, null))
        val dialog: AlertDialog = builder.create()
        val alertView = inflater.inflate(R.layout.select_exercice_alert, null)
        val list = alertView.findViewById<ListView>(R.id.alert_select_exercice_list)
        val arry = ArrayList<String>()
        arry.add("test")
        arry.add("test2")
        list.adapter = ExerciceSelectAdaptor(activity, arry)

        dialog.setView(alertView)
        dialog.show()
    }

    private fun manageDelete(vi : View?, inflater: LayoutInflater, pos : String) {
        val builder = AlertDialog.Builder(vi!!.context, R.style.AlertDialog)
        builder.setView(inflater.inflate(R.layout.delete_muscle_alert, null))
        val dialog: AlertDialog = builder.create()
        val alertView = inflater.inflate(R.layout.delete_muscle_alert, null)
        alertView.findViewById<TextView>(R.id.delete_muscle_alert_header).text = "¿Seguro que quieres eliminar $pos?"
        alertView.findViewById<Button>(R.id.delete_muscle_alert_cancel).setOnClickListener {
            dialog.cancel()
        }
        alertView.findViewById<Button>(R.id.delete_muscle_alert_confirm).setOnClickListener {
            dialog.cancel()
            Controller.removeSavedExercice(activity, pos)
            activity.loadList()

        }

        dialog.setView(alertView)
        dialog.show()
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