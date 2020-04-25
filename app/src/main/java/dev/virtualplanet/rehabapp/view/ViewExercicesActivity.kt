package dev.virtualplanet.rehabapp.view

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import dev.virtualplanet.rehabapp.R
import dev.virtualplanet.rehabapp.model.Exercice
import dev.virtualplanet.rehabapp.model.ExerciceList

class ViewExercicesActivity : AppCompatActivity() {
    private lateinit var mediaController: MediaController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_exercices)
        val progressBar = findViewById<ProgressBar>(R.id.ce_progressBar)
        progressBar.progress = 0
        mediaController = MediaController(this)
        var intent = intent
        var musculo = intent.getStringExtra("exercice")

        val resultTitle = findViewById<TextView>(R.id.ce_title_txt)
        val resultVideo = findViewById<VideoView>(R.id.ce_videoView)
        val next = findViewById<Button>(R.id.ce_fab)
        if (musculo.equals("Hombro derecho")) {
            resultTitle.text = "Hombro Derecho"
            var exercicis : MutableList<Exercice> = ArrayList()
            var exercice = Exercice("a", "a", 0, 0, 0, "android.resource://"+ packageName + "/" + R.raw.stair, resultVideo, mediaController)
            var exercice2 = Exercice("a", "a", 0, 0, 0, "android.resource://"+ packageName + "/" + R.raw.a1, resultVideo, mediaController)
            exercicis.add(exercice)
            exercicis.add(exercice2)
            var lista : ExerciceList = ExerciceList(exercicis)
            lista.playNetxtExercici()
            next.setOnClickListener {
                lista.playNetxtExercici()
                progressBar.progress = (progressBar.progress + 20) % 120
            }
        }
    }

    fun goBack(view: View) {
        val intent = Intent(this, MainExerciciActivity::class.java)
        startActivity(intent)
    }

    
}