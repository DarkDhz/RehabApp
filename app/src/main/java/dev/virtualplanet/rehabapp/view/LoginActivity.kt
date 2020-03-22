package dev.virtualplanet.rehabapp.view

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import dev.virtualplanet.rehabapp.R
import dev.virtualplanet.rehabapp.controller.Controller

class LoginActivity : AppCompatActivity() {

    private val controller = Controller

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var olv : TextView = findViewById(R.id.login_forgot_pass)
        olv.setOnClickListener {
            val intent = Intent (this, ContrasenaOlvidada::class.java)
            startActivity(intent)
        }
    }

    fun goToRegisterActivity(view: View) {
        val intent = Intent (this, RegisterActivity::class.java)
        startActivity(intent)
    }

    override fun onBackPressed() {
        //BLOCKED FOR SECURITY
    }

    fun goToMainActivity(view: View) {
        val intent = Intent (this, MainActivity::class.java)
        startActivity(intent)
    }

    fun goToRecoverPass(view: View) {

    }


}
