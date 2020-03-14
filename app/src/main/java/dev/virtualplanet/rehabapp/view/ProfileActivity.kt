package dev.virtualplanet.rehabapp.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import dev.virtualplanet.rehabapp.R
import kotlinx.android.synthetic.main.activity_edit_profile.*

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        this.initUser()
    }

    fun editProfile(view: View) {
        val intent = Intent(this, EditProfileActivity::class.java)
        startActivity(intent)
    }

    private fun initUser() {
        val pref = applicationContext.getSharedPreferences("USER", Context.MODE_PRIVATE)
        val name = pref.getString("NAME", "")
        this.e_profile_content.text = "$name"

    }

    fun returnToMain(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
