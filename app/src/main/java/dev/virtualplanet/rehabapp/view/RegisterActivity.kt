package dev.virtualplanet.rehabapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import dev.virtualplanet.rehabapp.R
import dev.virtualplanet.rehabapp.controller.Controller
import kotlinx.android.synthetic.main.activity_register.*

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
        val user = this.username_register.text.toString()
        val pass = this.pass_register.text.toString()
        val confirm = this.register_confirm_pass.text.toString()
        val mail = this.email_register.text.toString()
        controller.validateRegister(user, pass, confirm, mail, view)

    }
}
