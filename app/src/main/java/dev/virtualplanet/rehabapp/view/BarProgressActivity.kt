package dev.virtualplanet.rehabapp.view

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Description
import dev.virtualplanet.rehabapp.R
import dev.virtualplanet.rehabapp.controller.Controller
import java.util.*


class BarProgressActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bar_progress)
        this.init()
    }

    fun returnToMain(view: View) {
        val intent = Intent(this, SelectStaticActivity::class.java)
        startActivity(intent)
    }



    private fun init() {

        val chart = findViewById<BarChart>(R.id.bar_progress_content)
        chart.isVisible = false
        val desc = Description()
        chart.isEnabled = false
        chart.isDragEnabled = true
        desc.text = ""
        chart.axisLeft.setDrawLabels(false)
        chart.axisRight.setDrawLabels(true)
        chart.xAxis.setDrawLabels(true)
        chart.setTouchEnabled(false)
        chart.setScaleEnabled(false)
        chart.description = desc

        if (intent.extras != null) {
            when (intent.getStringExtra("type")) {
                "Exercice" -> {
                    findViewById<TextView>(R.id.bar_progress_header).text = "Resumen de ejercicios"
                    Controller.loadExericeProgressData(this)
                }
                "Movility" -> {
                    findViewById<TextView>(R.id.bar_progress_header).text = "Resumen de movilidad"
                    Controller.loadMovData(this)
                }
            }
        }
    }

    fun openMonthSelector(view: View) {
        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)
        val picker = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, day ->
            Controller.loadExericeProgressData(this, month, year)

        }, year, month, day)
        picker.datePicker.maxDate = Calendar.getInstance().timeInMillis
        picker.show()
    }
}
