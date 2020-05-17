package dev.virtualplanet.rehabapp.view.adaptors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import dev.virtualplanet.rehabapp.R
import dev.virtualplanet.rehabapp.model.Exercice
import dev.virtualplanet.rehabapp.model.Medicine
import dev.virtualplanet.rehabapp.model.MedicineList
import dev.virtualplanet.rehabapp.view.MedicinasActivity

class MedicineShowAdaptor : BaseAdapter {

    private var activity : MedicinasActivity
    private var list : MedicineList

    constructor(actividad: MedicinasActivity, lista: MedicineList) : super() {
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
        return vi
    }



    override fun getItem(pos: Int): Medicine {
        return list.content[pos]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return list.content.size
    }

}