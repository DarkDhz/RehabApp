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

class RegisterActivity : AppCompatActivity() {

    private val controller = Controller

    private var wait = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

    }

    fun goToLoginActivity(view: View) {
        val intent = Intent (view.context, LoginActivity::class.java)
        startActivity(intent)
    }

    fun register(view: View) {

        if (wait) {
            Toast.makeText(view.context, "Espera mientras se comprueban los datos", Toast.LENGTH_SHORT).show()
            return
        }

        val user = findViewById<EditText>(R.id.username_register).text.toString()
        val pass = findViewById<EditText>(R.id.pass_register).text.toString()
        val confirm = findViewById<EditText>(R.id.register_confirm_pass).text.toString()
        val mail = findViewById<EditText>(R.id.email_register).text.toString()

        wait = true
        controller.validateRegister(user, pass, confirm, mail, object :
            Callback<String> {
            override fun onCallback(value: String) {
                when (value) {
                    "success" -> {
                        controller.saveSharedUser(view.context, user)

                        val intent = Intent (view.context, MainActivity::class.java)
                        startActivity(intent)

                    }
                    else -> {
                        wait = false
                        Toast.makeText(view.context, value, Toast.LENGTH_LONG).show()
                    }
                }
            }

        })

    }
}
