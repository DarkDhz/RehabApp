package dev.virtualplanet.rehabapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import dev.virtualplanet.rehabapp.R
import dev.virtualplanet.rehabapp.controller.Controller

class SelectStaticActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_static)
    }

    fun returnToMain(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun goExercice(view: View) {
        openAlert("Exercice")
    }

    private fun openAlert(type: String) {
        var inflater : LayoutInflater = layoutInflater
        val builder = AlertDialog.Builder(this, R.style.AlertDialog)
        builder.setView(inflater.inflate(R.layout.select_representation_alert, null))
        val dialog: AlertDialog = builder.create()
        var alertView = inflater.inflate(R.layout.select_representation_alert, null)
        alertView.findViewById<ImageView>(R.id.representation_bar).setOnClickListener {
            val intent = Intent(this, BarProgressActivity::class.java).putExtra("type", "Exercice")
            startActivity(intent)
        }
        alertView.findViewById<ImageView>(R.id.representation_line).setOnClickListener {
            val intent = Intent(this, LinearProgressActivity::class.java).putExtra("type", "Exercice")
            startActivity(intent)
        }

        dialog.setView(alertView)
        dialog.show()
    }
}
