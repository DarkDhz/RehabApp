package dev.virtualplanet.rehabapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.virtualplanet.rehabapp.R
import dev.virtualplanet.rehabapp.controller.Controller

class Hombro_izquierdo : AppCompatActivity() {

    private val controller = Controller

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hombro_izquierdo)
    }
}
