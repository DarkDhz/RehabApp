package dev.virtualplanet.rehabapp.view

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import dev.virtualplanet.rehabapp.R
import dev.virtualplanet.rehabapp.controller.Controller
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
            val intent = Intent(this, MainExerciciActivity::class.java)
            startActivity(intent)
            return
        }

        findViewById<Button>(R.id.ce_done_bt).isVisible = false

        val progressBar = findViewById<ProgressBar>(R.id.ce_progressBar)
        progressBar.progress = 0

        mediaController = MediaController(this)

        val muscle = intent.getStringExtra("exercice")


        if (muscle.contains("Hombro") ) {
            val exerciceList = Controller.getList("Hombro", packageName)
            play(exerciceList.playNextExercice()!!)
            count++
            findViewById<Button>(R.id.ce_next).setOnClickListener {
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
            findViewById<Button>(R.id.ce_next).setOnClickListener {
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
            findViewById<Button>(R.id.ce_done_bt).isVisible = true
            findViewById<Button>(R.id.ce_next).isVisible = false
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
        save()
    }

    override fun onBackPressed() {
        save()
    }

    private fun save() {
        if (count > 0) {
            val userPreferences = getSharedPreferences(Controller.sharedTable, Context.MODE_PRIVATE)
            val user = userPreferences.getString("email", "")
            Controller.addExerciceCount(user, count)
        }
        val intent = Intent(this, MainExerciciActivity::class.java)
        startActivity(intent)
    }

    fun openFinish(view: View) {
        openFinishAlert()
    }

    private fun openFinishAlert() {
        val inflater : LayoutInflater = layoutInflater
        val builder = AlertDialog.Builder(this, R.style.AlertDialog)
        builder.setView(inflater.inflate(R.layout.save_exercice_alert, null))
        val dialog: AlertDialog = builder.create()
        val alertView = inflater.inflate(R.layout.save_exercice_alert, null)

        alertView.findViewById<SeekBar>(R.id.sea_seekBar)


        alertView.findViewById<Button>(R.id.sea_finish).setOnClickListener {
            dialog.cancel()
            save()
            val intent = Intent(this, MainExerciciActivity::class.java)
            startActivity(intent)
        }
        /*alertView.findViewById<TextView>(R.id.delete_muscle_alert_header).text = "¿Estás seguro que quieres cerrar sesión?"
        alertView.findViewById<Button>(R.id.delete_muscle_alert_cancel).setOnClickListener {
            dialog.cancel()
        }
        alertView.findViewById<Button>(R.id.delete_muscle_alert_confirm).setOnClickListener {
            dialog.cancel()
            Controller.logOut(this)
        }*/

        dialog.setView(alertView)
        dialog.show()
    }

    
}

