package dev.virtualplanet.rehabapp.view


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import dev.virtualplanet.rehabapp.R

class ViewExerciseAtivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.exercise_viewpager_layout)
        val fab = findViewById<Button>(R.id.fab)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar_horizontal)


        progressBar.progress = 0

        fab.setOnClickListener {
            progressBar.progress = (progressBar.progress + 20) % 100
        }
    }


}
