package dev.virtualplanet.rehabapp.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import dev.virtualplanet.rehabapp.R
import dev.virtualplanet.rehabapp.controller.data.Callback
import dev.virtualplanet.rehabapp.controller.Controller


class LoginActivity : AppCompatActivity() {

    private val controller = Controller

    private val data = FirebaseFirestore.getInstance()

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

        val user = findViewById<EditText>(R.id.username_login).text.toString()
        val pass = findViewById<EditText>(R.id.password_login).text.toString()
        controller.validateLogin(user, pass, object :
            Callback<String> {
            override fun onCallback(value: String) {
                when (value) {
                    "success" -> {
                        val userPreferences = getSharedPreferences(controller.sharedTable, Context.MODE_PRIVATE)
                        val editor: SharedPreferences.Editor = userPreferences.edit()

                        editor.putString("email", user)
                        editor.apply()

                        val intent = Intent(view.context, MainActivity::class.java)
                        startActivity(intent)
                    }
                    else -> {
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
