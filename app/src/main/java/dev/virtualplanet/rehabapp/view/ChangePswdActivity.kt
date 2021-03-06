package dev.virtualplanet.rehabapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import dev.virtualplanet.rehabapp.R
import dev.virtualplanet.rehabapp.controller.data.Callback
import dev.virtualplanet.rehabapp.controller.Controller

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

            val user = controller.getSharedUser(applicationContext)

            val old = findViewById<EditText>(R.id.pswd_old_value).text.toString()
            val new = findViewById<EditText>(R.id.pswd_new_value).text.toString()
            val repeat = findViewById<EditText>(R.id.pswd_repeat).text.toString()

            controller.changePass(user, old , new, repeat, object :
                Callback<String> {
                override fun onCallback(value: String) {
                    updatting = false
                    when (value) {
                        "success" -> {
                            Toast.makeText(view.context, "Contraseña actualizada", Toast.LENGTH_LONG).show()
                            val intent = Intent(view.context, SettingsActivity::class.java)
                            startActivity(intent)
                        }
                        else -> {
                            Toast.makeText(view.context, value, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            })
        } else {
            Toast.makeText(view.context, "La contraseña se esta actualizando, espera unos segundos para volver a cambiarla", Toast.LENGTH_LONG).show()
        }
    }

    fun goBack(view: View) {
        val intent = Intent(view.context, SettingsActivity::class.java)
        startActivity(intent)
    }

    override fun onBackPressed() {
        val intent = Intent(applicationContext, SettingsActivity::class.java)
        startActivity(intent)
    }
}
