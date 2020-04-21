package dev.virtualplanet.rehabapp.view

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import dev.virtualplanet.rehabapp.R
import dev.virtualplanet.rehabapp.controller.Controller


class ProgressActivity : AppCompatActivity() {

    private val controller = Controller

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress)
        this.init()
    }

    fun returnToMain(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }



    private fun init() {
        val sel_spiner = findViewById<Spinner>(R.id.select_static_spinner)
        val values = ArrayList<String>()
        values.add("Numero de ejercicios")

        val sel_adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, values)
        sel_spiner.adapter = sel_adapter

        Controller.loadProgressData(this)


    }

}
