package dev.virtualplanet.rehabapp.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import dev.virtualplanet.rehabapp.R

class PlayExerciceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_exercice)
    }
    var hombro= intent.getStringExtra("hombro_derecho")
    var hombroi=intent.getStringExtra("hombro_izquiero")
    var codo= intent.getStringExtra("hombro_derecho")
    var codo1=intent.getStringExtra("hombro_izquiero")
    var muneca= intent.getStringExtra("hombro_derecho")
    var munecai=intent.getStringExtra("hombro_izquiero")
    var rodilla= intent.getStringExtra("hombro_derecho")
    var rodillai=intent.getStringExtra("hombro_izquiero")
    var tobillo= intent.getStringExtra("hombro_derecho")
    var tobilloi=intent.getStringExtra("hombro_izquiero")
    val resultTV=findViewById<TextView>(R.id.ce_title_txt)

    fun returnToMain(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
