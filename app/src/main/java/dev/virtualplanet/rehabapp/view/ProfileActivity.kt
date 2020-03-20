package dev.virtualplanet.rehabapp.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.SpannableStringBuilder
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import dev.virtualplanet.rehabapp.R
import dev.virtualplanet.rehabapp.model.User
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.activity_profile.*
import java.io.File


class ProfileActivity : AppCompatActivity() {

    val data = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        //this.initUser()

        var lista = ArrayList<String>()

        data.collection("USERS").document("example@gmail.com")
            .get().addOnSuccessListener {
                val name = it.get("name")
                this.profile_content.text = name.toString()
                this.textView_Age_Value.text = it.get("age").toString()
                this.textView_Height_Value.text = it.get("height").toString()
                this.textView_Weight_Value.text = it.get("weight").toString()
                if (it.get("wheel").toString().toBoolean()) {
                    this.textView_WheelChair_Value.text = "SI"
                } else {
                    this.textView_WheelChair_Value.text = "SI"
                }
            }

    }

    fun editProfile(view: View) {
        val intent = Intent(this, EditProfileActivity::class.java)
        startActivity(intent)
    }

    private fun initUser() {




    }

    fun returnToMain(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
