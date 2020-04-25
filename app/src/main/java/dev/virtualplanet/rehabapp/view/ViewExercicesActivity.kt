package dev.virtualplanet.rehabapp.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import dev.virtualplanet.rehabapp.R
import dev.virtualplanet.rehabapp.controller.Controller
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
        val intent = intent
        val muscle = intent.getStringExtra("exercice")



        if (muscle.equals("Hombro derecho") || muscle.equals("Hombro izquierdo")) {
            val exerciceList : ExerciceList = Controller.createExerciceList()
            exerciceList.add(Controller.createExercice("Flexión del Hombro", "TODO", 15, 3, 30, "android.resource://"+ packageName + "/" + R.raw.stair))
            exerciceList.add(Controller.createExercice("Rotación del Hombro con apoyo", "TODO", 10, 1, 20, "android.resource://"+ packageName + "/" + R.raw.a1))
            exerciceList.add(Controller.createExercice("Caminado por pared de dedos", "TODO", 10, 3, 20, "android.resource://"+ packageName + "/" + R.raw.a1))
            exerciceList.add(Controller.createExercice("Rotación Interna del Hombro", "TODO", 10, 1, 20, "android.resource://"+ packageName + "/" + R.raw.a1))
            exerciceList.add(Controller.createExercice("Abducción del Hombro", "TODO", 10, 3, 20, "android.resource://"+ packageName + "/" + R.raw.a1))
            play(exerciceList.playNextExercice()!!)

            findViewById<Button>(R.id.ce_fab).setOnClickListener {
                val next = exerciceList.playNextExercice()
                if (next != null) {
                    play(next)
                    progressBar.progress = (progressBar.progress + 20) % 120
                }
            }
        }
    }

    private fun play(ex : Exercice) {
        val resultTitle = findViewById<TextView>(R.id.ce_title_txt)
        val resultVideo = findViewById<VideoView>(R.id.ce_videoView)
        val des = findViewById<TextView>(R.id.ce_descripction_txt)
        val rep = findViewById<TextView>(R.id.ce_num_rep_value)
        val ser = findViewById<TextView>(R.id.ce_num_ser_value)
        val wait = findViewById<TextView>(R.id.ce_num_des_value)

        resultTitle.text = ex.name
        des.text = ex.description
        rep.text = ex.numRep.toString()
        ser.text = ex.numSer.toString()
        wait.text = ex.breakTime.toString()
        val uri : Uri = Uri.parse(ex.path)
        resultVideo.setVideoURI(uri)
        resultVideo.setMediaController(mediaController)
        mediaController.setAnchorView(resultVideo)
        resultVideo.start()
    }

    fun goBack(view: View) {
        val intent = Intent(this, MainExerciciActivity::class.java)
        startActivity(intent)
    }

    
}