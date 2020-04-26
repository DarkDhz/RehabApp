package dev.virtualplanet.rehabapp.view

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import dev.virtualplanet.rehabapp.R
import dev.virtualplanet.rehabapp.controller.Controller
import dev.virtualplanet.rehabapp.model.Exercice
import dev.virtualplanet.rehabapp.model.ExerciceList

class ViewExercicesActivity : AppCompatActivity() {

    private var count = 0
    private lateinit var mediaController: MediaController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_exercices)
        this.init()
    }

    private fun init() {
        if (intent.extras == null) {
            return
        }
        val progressBar = findViewById<ProgressBar>(R.id.ce_progressBar)
        progressBar.progress = 0
        mediaController = MediaController(this)
        val intent = intent
        val muscle = intent.getStringExtra("exercice")


        if (muscle.contains("Hombro") ) {
            val exerciceList = Controller.getList("Hombro", packageName)
            play(exerciceList.playNextExercice()!!)
            count++
            findViewById<Button>(R.id.ce_fab).setOnClickListener {
                val next = exerciceList.playNextExercice()
                if (next != null) {
                    play(next)
                    progressBar.progress = (progressBar.progress + (100/(next.size-1))) % 120
                }
            }
            return
        }
        if (muscle.contains("Codo") || true) {
            val exerciceList = Controller.getList("Codo", packageName)
            play(exerciceList.playNextExercice()!!)
            count++
            findViewById<Button>(R.id.ce_fab).setOnClickListener {
                val next = exerciceList.playNextExercice()
                if (next != null) {
                    play(next)
                    progressBar.progress = (progressBar.progress + (100/(next.size-1))) % 120
                }
            }
            return
        }
    }

    private fun play(returnable : ExerciceList.Returnable) {
        val ex = returnable.exercice
        if (returnable.pos == returnable.size) {
            findViewById<Button>(R.id.ce_fab).isEnabled = false
        }
        count++
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
        resultVideo.setOnPreparedListener { mp ->
            mp.isLooping = true
        }
        resultVideo.start()
    }

    fun goBack(view: View) {
        if (count > 0) {
            val userPreferences = getSharedPreferences(Controller.sharedTable, Context.MODE_PRIVATE)
            val user = userPreferences.getString("email", "")
            Controller.addExerciceCount(user, count)
        }

        val intent = Intent(this, MainExerciciActivity::class.java)
        startActivity(intent)
    }

    override fun onBackPressed() {
        if (count > 0) {
            val userPreferences = getSharedPreferences(Controller.sharedTable, Context.MODE_PRIVATE)
            val user = userPreferences.getString("email", "")
            Controller.addExerciceCount(user, count)
        }
        val intent = Intent(this, MainExerciciActivity::class.java)
        startActivity(intent)
    }

    
}