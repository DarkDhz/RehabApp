package dev.virtualplanet.rehabapp.view

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import dev.virtualplanet.rehabapp.R
import dev.virtualplanet.rehabapp.controller.Controller
import java.util.*


class LinearProgressActivity : AppCompatActivity() {

    private var type = 0
    private val cal = Calendar.getInstance()
    private var year = cal.get(Calendar.YEAR)
    private var month = cal.get(Calendar.MONTH)
    private var day = cal.get(Calendar.DAY_OF_MONTH)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_linear_progress)
        this.init()

    }

    fun returnToMain(view: View) {
        val intent = Intent(view.context, SelectStaticActivity::class.java)
        view.context.startActivity(intent)
    }



    private fun init() {


        val chart = findViewById<LineChart>(R.id.progress_content)
        chart.isVisible = false
        chart.isEnabled = false
        chart.isDragEnabled = true
        chart.setScaleEnabled(true)
        val desc = Description()
        desc.text = ""
        chart.description = desc

        chart.axisLeft.setDrawLabels(false)
        chart.axisRight.setDrawLabels(false)
        chart.xAxis.setDrawLabels(true)
        chart.setTouchEnabled(false)
        chart.setScaleEnabled(false)

        if (intent.extras != null) {
            when (intent.getStringExtra("type")) {
                "Exercice" -> {
                    findViewById<TextView>(R.id.progress_header).text = "Resumen de ejercicios"
                    Controller.loadExericeProgressData(this)
                }
                "Movility" -> {
                    type = 1
                    findViewById<TextView>(R.id.progress_header).text = "Resumen de movilidad"
                    Controller.loadMovData(this)
                }
            }
        }


    }

    fun openMonthSelector(view: View) {
        val picker = DatePickerDialog(view.context, DatePickerDialog.OnDateSetListener { _, year2, month2, day2 ->
            day = day2
            month = month2
            year = year2
            when (type) {
                0 -> {
                    Controller.loadExericeProgressData(this, month2, year2)
                }
                1 -> {
                    Controller.loadMovData(this, month2, year2)
                }
            }

        }, year, month, day)
        picker.datePicker.maxDate = Calendar.getInstance().timeInMillis
        picker.show()
    }


}
