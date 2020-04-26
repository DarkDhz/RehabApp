package dev.virtualplanet.rehabapp.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import dev.virtualplanet.rehabapp.R
import dev.virtualplanet.rehabapp.controller.Callback
import dev.virtualplanet.rehabapp.controller.Controller
import kotlinx.android.synthetic.main.activity_change_pswd.*

class ChangePswdActivity : AppCompatActivity() {

    private val controller = Controller
    private var updatting = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_pswd)
    }

    fun savePass(view: View) {
        //TODO
        if (!updatting) {
            updatting = true
            val userPreferences = view.context.getSharedPreferences(Controller.sharedTable, Context.MODE_PRIVATE)
            val user = userPreferences.getString("email", "")

            val old = findViewById<EditText>(R.id.pswd_old_value).text.toString()
            val new = findViewById<EditText>(R.id.pswd_new_value).text.toString()
            val repeat = findViewById<EditText>(R.id.pswd_repeat).text.toString()

            controller.changePass(user, old , new, repeat, object : Callback<String> {
                override fun onCallback(value: String) {
                    updatting = false
                    when (value) {
                        "success" -> {
                            Toast.makeText(applicationContext, "Contraseña actualizada", Toast.LENGTH_LONG).show()
                            val intent = Intent(applicationContext, SettingsActivity::class.java)
                            startActivity(intent)
                        }
                        else -> {
                            Toast.makeText(applicationContext, value, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            })
        } else {
            Toast.makeText(this, "La contraseña se esta actualizando, espera unos segundos para volver a cambiarla", Toast.LENGTH_LONG).show()
        }
    }

    fun goBack(view: View) {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }

    override fun onBackPressed() {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }
}
