package dev.virtualplanet.rehabapp.view

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
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
        /*val mail = this.username_login.text.toString()
        val pass = this.password_login.text.toString()

        if (mail != "") {
            data.collection("USERS").document(mail)
                .get().addOnSuccessListener {
                    val check_pass = it.get("password").toString()
                    if (check_pass == pass) {
                        val message = Toast.makeText(applicationContext, "Contraseña correcta", Toast.LENGTH_LONG)
                        message.show()
                        val intent = Intent (this, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        val message = Toast.makeText(applicationContext, "Contraseña incorrecta", Toast.LENGTH_LONG)
                        message.show()
                    }
                }.addOnFailureListener {
                    val message = Toast.makeText(applicationContext, "Contraseña o usuario incorrecto", Toast.LENGTH_LONG)
                    message.show()
                }

        } else {
            val message = Toast.makeText(applicationContext, "Alguno de los campos esta vacio", Toast.LENGTH_LONG)
            message.show()
        }*/
    }

    fun goToRecoverPass(view: View) {
        val intent = Intent (this, RecoverPassActivity::class.java)
        startActivity(intent)
    }


}
