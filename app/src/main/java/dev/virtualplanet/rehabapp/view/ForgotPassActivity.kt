package dev.virtualplanet.rehabapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import dev.virtualplanet.rehabapp.R

class ForgotPassActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_pass)
    }

    fun goBack(view: View) {
        val intent = Intent (this, LoginActivity::class.java)
        startActivity(intent)
    }

    fun sendMail(view: View) {
        val intent = Intent (this, LoginActivity::class.java)
        startActivity(intent)
    }
}
