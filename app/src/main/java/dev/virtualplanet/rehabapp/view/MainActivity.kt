package dev.virtualplanet.rehabapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.firestore.FirebaseFirestore
import dev.virtualplanet.rehabapp.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val data = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //CHECK IF USER IS LOGED IF NOT requiereLogin()

        this.init()
    }

    private fun init() {
        //DATABASE TESTING "IGNORE"

        val users = data.collection("USERS")

        var lista = ArrayList<String>()

        data.collection("USERS")
            .get().addOnSuccessListener {
                it.forEach {
                    lista.add(it.get("mail").toString())
                }
            }

        for (mail in lista) {
            if ( ! mail.equals("example@gmail.com") ) {
                users.document("example@gmail.com").set(mapOf(
                    "name" to "Martin Garrix",
                    "mail" to "example@gmail.com",
                    "password" to "1234abcd",
                    "sex" to "man",
                    "age" to 23,
                    "weight" to 80,
                    "height" to 180,
                    "wheel" to false
                ))
            }
        }

        // END OF DATABASE TESTING
    }

    fun goToProfileActivity(view: View) {
        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)

    }

    private fun requiereLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    fun goToExercicesActivity(view: View) {
        val intent = Intent(this, MainExerciciActivity::class.java)
        startActivity(intent)
    }

    fun goToProgressActivity(view: View) {
        val intent = Intent(this, ProgressActivity::class.java)
        startActivity(intent)
    }

    fun goToSettingsActivity(view: View) {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }

}
