package dev.virtualplanet.rehabapp.controller.shared

import android.content.Context
import android.content.SharedPreferences

object MedicineSharedManager {
    private const val sharedTable = "selected"

    fun clear(context: Context) {
        val medicinePreferences = context.getSharedPreferences(sharedTable, Context.MODE_PRIVATE)
        val editor = medicinePreferences.edit()
        editor.clear()
    }
    fun contains(context: Context, name : String) : Boolean {
        val medicinePreferences = context.getSharedPreferences(sharedTable, Context.MODE_PRIVATE)
        if (medicinePreferences.contains(name)){
            return true
        }
        return false
    }

    fun addSavedMedicines(context: Context, name : String) : Boolean {
        val medicinePreferences = context.getSharedPreferences(sharedTable, Context.MODE_PRIVATE)
        val valid = medicinePreferences.getString(name, "null")
        if (valid == "null") {
            val editor: SharedPreferences.Editor = medicinePreferences.edit()
            editor.putString(name, "valid")
            editor.commit()
            editor.apply()
            return true
        } else {
            return false
        }

    }

    fun addSavedDay(context: Context, day : String, key : String) : Boolean {
        val medicinePreferences = context.getSharedPreferences(sharedTable, Context.MODE_PRIVATE)
        val valid = medicinePreferences.getString(key, "null")
        val editor: SharedPreferences.Editor = medicinePreferences.edit()
        if (valid == "null") {
            editor.putString(key, day)
            editor.commit()
            editor.apply()
            return true
        } else {
            editor.putString(key, day)
            editor.commit()
            return true
        }

    }

    fun loadSavedDay(context: Context, key : String) : String {
        val medicinePreferences = context.getSharedPreferences(sharedTable, Context.MODE_PRIVATE)
        var value = medicinePreferences.getString(key, "")
        if (value != null) {
            return value
        }
        return ""
        //Toast.makeText(context,to Return.toString(), Toast.LENGTH_LONG).show()
    }

    fun addSavedMedicineInt(context: Context, i : Int, key : String) : Boolean {
        val medicinePreferences = context.getSharedPreferences(sharedTable, Context.MODE_PRIVATE)
        val valid = medicinePreferences.getInt(key, 0)
        val editor: SharedPreferences.Editor = medicinePreferences.edit()
        if (valid == 0) {
            editor.putInt(key, i)
            editor.commit()
            editor.apply()
            return true
        } else {
            editor.putInt(key, i)
            editor.commit()
            return true
        }

    }

    fun loadMedicineInt(context: Context, key : String) : Int {
        val medicinePreferences = context.getSharedPreferences(sharedTable, Context.MODE_PRIVATE)
        var value = medicinePreferences.getInt(key, 0)
        if (value != null) {
            return value
        }
        return 0
        //Toast.makeText(context, toReturn.toString(), Toast.LENGTH_LONG).show()
    }

    fun loadSavedMedicines(context: Context) : ArrayList<String> {
        val toReturn = ArrayList<String>()
        val medicinePreferences = context.getSharedPreferences(sharedTable, Context.MODE_PRIVATE)
        for ( value in medicinePreferences.all.keys) {
            if (value == "Analgesico" || value == "Relajante" || value == "Antiinflamatorio"|| value == "Afavorecidor de circulacion"|| value == "Antitrombotico") toReturn.add(value)
        }
        //Toast.makeText(context, toReturn.toString(), Toast.LENGTH_LONG).show()
        return toReturn
    }



    fun removeSavedMedicine(context: Context, name: String) : Boolean {
        val medicinePreferences = context.getSharedPreferences(sharedTable, Context.MODE_PRIVATE)
        val valid = medicinePreferences.getString(name, "null")
        if (valid != "null") {
            val editor: SharedPreferences.Editor = medicinePreferences.edit()
            editor.remove(name)
            editor.commit()
            editor.apply()
            return true
        } else {
            val editor: SharedPreferences.Editor = medicinePreferences.edit()
            editor.remove(name)
            editor.commit()
            return true
        }
    }
}