package dev.virtualplanet.rehabapp.controller.shared

import android.content.Context
import android.content.SharedPreferences
import dev.virtualplanet.rehabapp.controller.Controller

object UserSharedManager {

    private const val sharedTable = "userInfo"

    fun getUser(context: Context) : String? {
        val userPreferences = context.getSharedPreferences(sharedTable, Context.MODE_PRIVATE)
        return userPreferences.getString("email", "")
    }

    fun clear(context: Context) {
        val userPreferences = context.getSharedPreferences(sharedTable, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = userPreferences.edit()

        editor.remove("email")
        editor.apply()
    }

    fun saveUser(context: Context, user: String) {
        val userPreferences = context.getSharedPreferences(sharedTable, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = userPreferences.edit()

        editor.putString("email", user)
        editor.apply()
    }
}