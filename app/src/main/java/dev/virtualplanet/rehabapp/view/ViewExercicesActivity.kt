package dev.virtualplanet.rehabapp.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import dev.virtualplanet.rehabapp.R

class ViewExercicesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_exercices)

        var intent = intent
        var hombro = intent.getStringExtra("hombro_derecho")
        var hombroi = intent.getStringExtra("hombro_izquierdo")
        var codo = intent.getStringExtra("codo_derecho")
        var codoi = intent.getStringExtra("codo_izquierdo")
        var muneca = intent.getStringExtra("muneca_derecha")
        var munecai = intent.getStringExtra("muneca_izquierda")
        var rodilla = intent.getStringExtra("rodilla_derecha")
        var rodillai = intent.getStringExtra("rodilla_izquierda")
        var tobillo = intent.getStringExtra("tobillo_derecho")
        var tobilloi = intent.getStringExtra("tobillo_izquierdo")
        val resultTitle = findViewById<TextView>(R.id.ce_title_txt)
        val resultVideo = findViewById<VideoView>(R.id.ce_videoView)

        if (!hombro.isNullOrEmpty()) {
            resultTitle.text = "Hombro Derecho"

        } else if (!hombroi.isNullOrEmpty()) {
            resultTitle.text = "Hombro Izquierdo"
        } else if (!codo.isNullOrEmpty()) {
            resultTitle.text = "Codo Derecho"
        } else if (!codoi.isNullOrEmpty()) {
            resultTitle.text = "Codo Izquierdo"
        } else if (!muneca.isNullOrEmpty()) {
            resultTitle.text = "Muñeca Derecha"
        } else if (!munecai.isNullOrEmpty()) {
            resultTitle.text = "Muñeca Izquierda"
        } else if (!rodilla.isNullOrEmpty()) {
            resultTitle.text = "Rodilla Derecha"
        } else if (!rodillai.isNullOrEmpty()) {
            resultTitle.text = "Rodilla Izquierda"
        } else if (!tobillo.isNullOrEmpty()) {
            resultTitle.text = "Tobillo Derecho"
        } else if (!tobilloi.isNullOrEmpty()) {
            resultTitle.text = "Tobillo Izquierdo"
        } else {
            resultTitle.text = "No tenemos ejercicios"
        }


        val fab = findViewById<Button>(R.id.ce_fab)
        val progressBar = findViewById<ProgressBar>(R.id.ce_progressBar)


        progressBar.progress = 0

        fab.setOnClickListener {
            progressBar.progress = (progressBar.progress + 20) % 120
        }
    }

    fun goBack(view: View) {
        val intent = Intent(this, MainExerciciActivity::class.java)
        startActivity(intent)
    }

    
}