package dev.virtualplanet.rehabapp.view

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.firestore.FirebaseFirestore
import dev.virtualplanet.rehabapp.R
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.activity_edit_profile.view.*

class EditProfileActivity : AppCompatActivity() {

    private val data = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        //this.initUser()

    }

    fun saveProfile(view: View) {
        val new_age = this.e_textView_Age_Value.text
        val new_height = this.e_textView_Height_Value.text
        val new_weight = this.e_textView_Weight_Value.text
        //val new_wheel = this.e_textView_WheelChair_Value.get

        val intent = Intent(this, ProfileActivity::class.java).apply {
            action = Intent.ACTION_SEND
            putExtra("AGE", new_age)
            putExtra("HEIGHT", new_height)
            putExtra("WEIGHT", new_weight)
            //putExtra("WHEEL", new_wheel)
            type = "text/plain"
        }
        startActivity(intent)

    }

    fun returnToMain(view: View) {
        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)
    }

    private fun initUser() {

    }
}
