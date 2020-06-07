package dev.virtualplanet.rehabapp.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dev.virtualplanet.rehabapp.R
import dev.virtualplanet.rehabapp.controller.data.Callback
import dev.virtualplanet.rehabapp.controller.Controller


class LoginActivity : AppCompatActivity() {

    private var wait = false

    private val controller = Controller

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        this.autoLogin()
    }

    fun goToRegisterActivity(view: View) {
        val intent = Intent (view.context, RegisterActivity::class.java)
        startActivity(intent)
    }

    override fun onBackPressed() {
        //BLOCKED FOR SECURITY
    }

    fun login(view: View) {
        if (wait) {
            Toast.makeText(view.context, "Espera mientras se cargan los datos", Toast.LENGTH_SHORT).show()
            return
        }
        val user = findViewById<EditText>(R.id.username_login).text.toString()
        val pass = findViewById<EditText>(R.id.password_login).text.toString()

        wait = true
        controller.validateLogin(user, pass, object :
            Callback<String> {
            override fun onCallback(value: String) {
                when (value) {
                    "success" -> {
                        controller.saveSharedUser(view.context, user)

                        val intent = Intent(view.context, MainActivity::class.java)
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

    fun goToRecoverPass(view: View) {
        val intent = Intent (view.context, RecoverPassActivity::class.java)
        startActivity(intent)
    }

    fun autoLogin() {
        Controller.autologin(this)
    }


}
