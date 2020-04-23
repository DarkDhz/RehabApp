package dev.virtualplanet.rehabapp.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import dev.virtualplanet.rehabapp.R
import dev.virtualplanet.rehabapp.controller.Controller
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    private val controller = Controller

    private val data = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        this.autoLogin()
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

    fun autoLogin() {
        Controller.autologin(this)
    }


}
