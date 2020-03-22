package dev.virtualplanet.rehabapp.view

import android.content.Intent
import android.graphics.Color
import android.graphics.DashPathEffect
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
        values.add("Number of Exercices")
        values.add("Example 2")
        values.add("Example 3")

        val sel_adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, values)
        sel_spiner.adapter = sel_adapter

        val progress_chart = findViewById<LineChart>(R.id.progress_content)

        progress_chart.isDragEnabled = true
        progress_chart.setScaleEnabled(true)

        val y_values = ArrayList<Entry>()
        y_values.add(Entry(0f, 6f))
        y_values.add(Entry(1f, 3f))
        y_values.add(Entry(2f, 7f))
        y_values.add(Entry(3f, 5f))
        y_values.add(Entry(4f, 5f))
        y_values.add(Entry(5f, 4f))
        y_values.add(Entry(6f, 5f))

        val set1 = LineDataSet(y_values, "Number of exercices")
        set1.lineWidth = 6f
        set1.circleRadius = 6f
        set1.valueTextSize = 15f
        set1.setCircleColor(Color.LTGRAY)
        set1.setDrawCircleHole(false)

        val dataSets = ArrayList<ILineDataSet>()
        dataSets.add(set1)

        val data = LineData(dataSets)

        progress_chart.data = data

        val desc = Description()
        desc.text = ""
        progress_chart.description = desc

        progress_chart.axisLeft.setDrawLabels(false)
        progress_chart.axisRight.setDrawLabels(false)
        progress_chart.xAxis.setDrawLabels(false)
        progress_chart.setTouchEnabled(false)
    }

}
