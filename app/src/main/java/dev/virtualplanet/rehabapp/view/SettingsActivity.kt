package dev.virtualplanet.rehabapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import dev.virtualplanet.rehabapp.R
import kotlinx.android.synthetic.main.activity_settings.view.*

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)


    }

    fun returnToMain(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun modifyNotifications(view: View) {
        if (view.stt_notify_bt.text.equals("ON")) {
            view.stt_notify_bt.text = "OFF"
        } else {
            view.stt_notify_bt.text = "ON"
        }
    }

    fun modifySound(view: View) {
        if (view.stt_sound_bt.text.equals("ON")) {
            view.stt_sound_bt.text = "OFF"
        } else {
            view.stt_sound_bt.text = "ON"
        }
    }

}
