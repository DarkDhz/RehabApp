package dev.virtualplanet.rehabapp.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import dev.virtualplanet.rehabapp.R
import dev.virtualplanet.rehabapp.controller.data.Callback
import dev.virtualplanet.rehabapp.controller.Controller

class RegisterActivity : AppCompatActivity() {

    private val controller = Controller

    private val data = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

    }

    fun goToLoginActivity(view: View) {
        val intent = Intent (this, LoginActivity::class.java)
        startActivity(intent)
    }

    fun register(view: View) {
        val user = findViewById<EditText>(R.id.username_register).text.toString()
        val pass = findViewById<EditText>(R.id.pass_register).text.toString()
        val confirm = findViewById<EditText>(R.id.register_confirm_pass).text.toString()
        val mail = findViewById<EditText>(R.id.email_register).text.toString()


        controller.validateRegister(user, pass, confirm, mail, object :
            Callback<String> {
            override fun onCallback(value: String) {
                when (value) {
                    "success" -> {
                        val userPreferences = getSharedPreferences(controller.sharedTable, Context.MODE_PRIVATE)
                        val editor: SharedPreferences.Editor = userPreferences.edit()

                        editor.putString("email", mail)
                        editor.apply()

                        val intent = Intent (applicationContext, MainActivity::class.java)
                        startActivity(intent)

                    }
                    else -> {
                        Toast.makeText(applicationContext, value, Toast.LENGTH_LONG).show()
                    }
                }
            }

        })

    }
}
