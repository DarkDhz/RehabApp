package dev.virtualplanet.rehabapp.view

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.google.firebase.firestore.FirebaseFirestore
import dev.virtualplanet.rehabapp.R
import dev.virtualplanet.rehabapp.controller.Controller
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_profile.*


class LoginActivity : AppCompatActivity() {

    private val controller = Controller

    private val data = FirebaseFirestore.getInstance()

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

    fun login(view: View) {
        controller.validateLogin(this.password_login.text.toString(), this.password_login.text.toString(), view)
    }

    fun goToRecoverPass(view: View) {
        val intent = Intent (this, RecoverPassActivity::class.java)
        startActivity(intent)
    }


}
