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
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
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
        if (intent.extras != null) {
            val type = intent.getStringExtra("type")
            if (type.contains("Exercice")) {
                findViewById<TextView>(R.id.bar_progress_header).text = "Resumen de ejercicios"
            }
        }

        val chart = findViewById<BarChart>(R.id.bar_progress_content)
        chart.isVisible = false
        Controller.loadProgressData(this)



        //progressChart.isVisible = false
        //Controller.loadProgressData(this)


    }

    fun openMonthSelector(view: View) {
        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)
        val picker = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, day ->
            Controller.loadProgressData(this, month, year)

        }, year, month, day)
        picker.datePicker.maxDate = Calendar.getInstance().timeInMillis
        picker.show()
    }
}