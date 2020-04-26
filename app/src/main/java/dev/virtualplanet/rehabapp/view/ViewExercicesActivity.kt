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


        if (muscle.equals("Hombro derecho") || muscle.equals("Hombro izquierdo") || true) {
            val exerciceList : ExerciceList = Controller.createExerciceList()
            val path = "android.resource://$packageName/"
            exerciceList.add(Controller.createExercice("Flexión del Hombro", "Eleve su brazo hasta señalar el techo manteniendo su codo.",
                15, 3, 30, path + R.raw.hombro_flexion))

            exerciceList.add(Controller.createExercice("Rotación del Hombro con apoyo", "Mantenga el codo apoyado en un lugar (como muestra la figura) y las escápulas (paletas) hacia abajo y juntas. Gire el antebrazo hacia delante y hacia atrás.",
                10, 1, 20, path + R.raw.hombro_rot_in))

            exerciceList.add(Controller.createExercice("Caminado por pared de dedos", "Con el codo, utilice los dedos para caminar hacia arriba por la pared o en el marco de la puerta lo más alto posible.",
                10, 3, 20, path + R.raw.hombro_pared))

            exerciceList.add(Controller.createExercice("Rotación Interna del Hombro", "Lleve su mano detrás de la espalda y hacia el lado opuesto.",
                10, 1, 20, path + R.raw.hombro_rotacion))

            exerciceList.add(Controller.createExercice("Abducción del Hombro", "Levante los brazos lateralmente, con los codos  y las palmas hacia abajo. No encoja los hombros, ni incline el tronco.",
                10, 3, 20, path + R.raw.hombro_abd))

            play(exerciceList.playNextExercice()!!)
            count++
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