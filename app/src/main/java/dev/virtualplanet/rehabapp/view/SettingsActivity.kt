package dev.virtualplanet.rehabapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Switch
import dev.virtualplanet.rehabapp.R
import dev.virtualplanet.rehabapp.controller.Controller
import kotlinx.android.synthetic.main.activity_settings.view.*

class SettingsActivity : AppCompatActivity() {

    private val controller = Controller

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
    }

    fun returnToMain(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun modifyNotifications(view: View) {
        //TODO
        val bt = findViewById<Switch>(R.id.stt_notify_bt)
    }

    fun modifySound(view: View) {
        //TODO
        val bt = findViewById<Switch>(R.id.stt_sound_bt)

    }

    fun logOut(view: View) {
        //TODO Remove from internal files actual-USER
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    fun changePassword(view: View) {
        val intent = Intent(this, ChangePswdActivity::class.java)
        startActivity(intent)
    }

}
