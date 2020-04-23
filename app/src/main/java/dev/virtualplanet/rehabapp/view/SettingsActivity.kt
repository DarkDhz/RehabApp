package dev.virtualplanet.rehabapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import dev.virtualplanet.rehabapp.R
import dev.virtualplanet.rehabapp.controller.Controller

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
        var inflater : LayoutInflater = layoutInflater
        val builder = AlertDialog.Builder(this, R.style.AlertDialog)
        builder.setView(inflater.inflate(R.layout.delete_muscle_alert, null))
        val dialog: AlertDialog = builder.create()
        var alertView = inflater.inflate(R.layout.delete_muscle_alert, null)
        alertView.findViewById<TextView>(R.id.delete_muscle_alert_header).text = "¿Estás seguro que quieres cerrar sesión?"
        alertView.findViewById<Button>(R.id.delete_muscle_alert_cancel).setOnClickListener {
            dialog.cancel()
        }
        alertView.findViewById<Button>(R.id.delete_muscle_alert_confirm).setOnClickListener {
            dialog.cancel()
            Controller.logOut(this)
        }

        dialog.setView(alertView)
        dialog.show()


    }

    fun changePassword(view: View) {
        val intent = Intent(this, ChangePswdActivity::class.java)
        startActivity(intent)
    }


}
