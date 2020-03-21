package dev.virtualplanet.rehabapp.view

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.view.View
import dev.virtualplanet.rehabapp.R

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
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
    }


}
