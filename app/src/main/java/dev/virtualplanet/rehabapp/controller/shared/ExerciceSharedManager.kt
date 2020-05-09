package dev.virtualplanet.rehabapp.controller.shared

import android.content.Context
import android.content.SharedPreferences
import dev.virtualplanet.rehabapp.controller.Controller

object ExerciceSharedManager {

    private const val sharedTable = "selected"

    fun clear(context: Context) {
        val exercicePreferences = context.getSharedPreferences(sharedTable, Context.MODE_PRIVATE)
        val editor = exercicePreferences.edit()

        for (key in exercicePreferences.all.keys) {
            editor.remove(key)
        }

        editor.apply()
    }

    fun addSavedExercices(context: Context, name : String) : Boolean {
        val exercicePreferences = context.getSharedPreferences(sharedTable, Context.MODE_PRIVATE)
        val valid = exercicePreferences.getString(name, "null")
        if (valid == "null") {
            val editor: SharedPreferences.Editor = exercicePreferences.edit()
            editor.putString(name, "valid")
            editor.apply()
            return true
        } else {
            return false
        }

    }

    fun loadSavedExercices(context: Context) : ArrayList<String> {
        val toReturn = ArrayList<String>()
        val exercicePreferences = context.getSharedPreferences(sharedTable, Context.MODE_PRIVATE)
        for ( value in exercicePreferences.all.keys) {
            toReturn.add(value)
        }
        //Toast.makeText(context, toReturn.toString(), Toast.LENGTH_LONG).show()
        return toReturn
    }

    fun removeSavedExercice(context: Context, name: String) : Boolean {
        val exercicePreferences = context.getSharedPreferences(sharedTable, Context.MODE_PRIVATE)
        val valid = exercicePreferences.getString(name, "null")
        if (valid != "null") {
            val editor: SharedPreferences.Editor = exercicePreferences.edit()
            editor.remove(name)
            editor.apply()
            return true
        } else {
            return false
        }
    }
}