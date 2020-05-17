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
import android.widget.SeekBar
import dev.virtualplanet.rehabapp.view.adaptors.MarkCompletedAdaptor

class ViewExercicesActivity : AppCompatActivity() {

    private lateinit var mediaController: MediaController
    private lateinit var exerciceList : ExerciceList

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
        initButtons()


    }

    private fun initButtons() {

        findViewById<Button>(R.id.ce_done_bt).isVisible = false

        val progressBar = findViewById<ProgressBar>(R.id.ce_progressBar)
        progressBar.progress = 0

        mediaController = MediaController(this)

        val muscle = intent.getStringExtra("exercice")
        if (muscle.contains("Hombro") ) {
            load("Hombro")
            return
        }
        if (muscle.contains("Codo")) {
            load("Codo")
            return
        }
        if (muscle.contains("Rodilla")) {
            load("Rodilla")
            return
        }
        if (muscle.contains("Muñeca")) {
            load("Muñeca")
            return
        }
        if (muscle.contains("Tobillo")) {
            load("Tobillo")
            return
        } else {
            load("Hombro")
        }

    }

    private fun load(exercice: String) {
        val progressBar = findViewById<ProgressBar>(R.id.ce_progressBar)

        exerciceList = Controller.getList(exercice, packageName)
        play(exerciceList.playNextExercice()!!)
        findViewById<Button>(R.id.ce_next).setOnClickListener {
            val next = exerciceList.playNextExercice()
            if (next != null) {
                play(next)
                progressBar.progress = (progressBar.progress + (100/(next.size-1))) % 120
            }
        }
        return

    }

    private fun play(returnable : ExerciceList.Returnable) {
        val ex = returnable.exercice
        if (returnable.pos == returnable.size) {
            findViewById<Button>(R.id.ce_done_bt).isVisible = true
            findViewById<Button>(R.id.ce_next).isVisible = false
        }
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
        val intent = Intent(view.context, MainExerciciActivity::class.java)
        startActivity(intent)
    }

    override fun onBackPressed() {
        val intent = Intent(applicationContext, MainExerciciActivity::class.java)
        startActivity(intent)
    }

    private fun save(count: Int, progress: Int) {
        val user = Controller.getSharedUser(applicationContext)
        if (user != "") {
            if (count > 0) {
                Controller.addExerciceCount(user.toString(), count)
            }
            Controller.addMov(user.toString(), progress)
        }


        val intent = Intent(applicationContext, MainExerciciActivity::class.java)
        startActivity(intent)
    }

    fun openFinish(view: View) {
        openFinishAlert(view.context)
    }

    private fun openFinishAlert(context: Context) {
        val inflater : LayoutInflater = layoutInflater
        val builder = AlertDialog.Builder(context, R.style.AlertDialog)
        builder.setView(inflater.inflate(R.layout.save_exercice_alert, null))
        val dialog: AlertDialog = builder.create()
        val alertView = inflater.inflate(R.layout.save_exercice_alert, null)

        val adapt = MarkCompletedAdaptor(this, exerciceList)
        alertView.findViewById<ListView>(R.id.sea_listView).adapter = adapt
        alertView.findViewById<SeekBar>(R.id.sea_seekBar).progress = 50

        alertView.findViewById<SeekBar>(R.id.sea_seekBar).setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                alertView.findViewById<TextView>(R.id.sea_seekValue).text = (progress/10).toString()
            }
           override fun onStopTrackingTouch(seekBar: SeekBar) {
               return
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                return
            }
        })

        alertView.findViewById<Button>(R.id.sea_finish).setOnClickListener {
            val adapt2 = alertView.findViewById<ListView>(R.id.sea_listView).adapter as MarkCompletedAdaptor
            val prg = (alertView.findViewById<SeekBar>(R.id.sea_seekBar).progress/10)
            save(adapt2.getMarkedCount(), prg)
            dialog.cancel()
        }

        alertView.findViewById<CheckBox>(R.id.sea_afirmative_box).setOnClickListener {
            alertView.findViewById<CheckBox>(R.id.sea_negative_box).isChecked = false
        }

        alertView.findViewById<CheckBox>(R.id.sea_negative_box).setOnClickListener {
            alertView.findViewById<CheckBox>(R.id.sea_afirmative_box).isChecked = false
        }


        dialog.setView(alertView)
        dialog.show()
    }


}

