package dev.virtualplanet.rehabapp.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import dev.virtualplanet.rehabapp.R
import dev.virtualplanet.rehabapp.controller.Controller

class PlayExerciceActivity : AppCompatActivity() {

    private val controller = Controller

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_exercice)
        var intent = intent
        var hombro = intent.getStringExtra("hombro_derecho")
        var hombroi = intent.getStringExtra("hombro_izquiero")
        var codo = intent.getStringExtra("hombro_derecho")
        var codoi = intent.getStringExtra("hombro_izquiero")
        var muneca = intent.getStringExtra("hombro_derecho")
        var munecai = intent.getStringExtra("hombro_izquiero")
        var rodilla = intent.getStringExtra("hombro_derecho")
        var rodillai = intent.getStringExtra("hombro_izquiero")
        var tobillo = intent.getStringExtra("hombro_derecho")
        var tobilloi = intent.getStringExtra("hombro_izquiero")
        val resultTitle = findViewById<TextView>(R.id.ce_title_txt)
        val resultVideo =findViewById<VideoView>(R.id.videoView)

        if (!hombro.isNullOrEmpty()) {
            resultTitle.text = hombro

        } else if (!hombroi.isNullOrEmpty()) {
            resultTitle.text = hombroi
        } else if (!codo.isNullOrEmpty()) {
            resultTitle.text = codo
        } else if (!codoi.isNullOrEmpty()) {
            resultTitle.text = codoi
        } else if (!muneca.isNullOrEmpty()) {
            resultTitle.text = muneca
        } else if (!munecai.isNullOrEmpty()) {
            resultTitle.text = munecai
        } else if (!rodilla.isNullOrEmpty()) {
            resultTitle.text = rodilla
        } else if (!rodillai.isNullOrEmpty()) {
            resultTitle.text = rodillai
        } else if (!tobillo.isNullOrEmpty()) {
            resultTitle.text = tobillo
        } else if (!tobilloi.isNullOrEmpty()) {
            resultTitle.text = tobilloi
        } else {
            resultTitle.text = "No tenemos ejercicios"
        }


    }


    fun returnToMain(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
