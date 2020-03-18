package dev.virtualplanet.rehabapp.view

import android.content.Intent
import android.graphics.ColorSpace
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.database.ValueEventListener
import dev.virtualplanet.rehabapp.R
import dev.virtualplanet.rehabapp.model.User

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //CHECK IF USER IS LOGED IF NOT requiereLogin()


        /*val database = FirebaseFirestore.getInstance()
        var exampleUser = User("Martin Garrix", "1234", "example@gmail.com")

        val example_user = hashMapOf(
            "name" to exampleUser.getUser(),
            "object" to exampleUser
        )

        database.collection("users").add(exampleUser)*/

    }



    fun goToProfileActivity(view: View) {
        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)

    }

    fun requiereLogin() {
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
