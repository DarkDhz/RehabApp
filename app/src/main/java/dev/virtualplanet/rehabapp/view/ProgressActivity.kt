package dev.virtualplanet.rehabapp.view

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.github.mikephil.charting.charts.LineChart
import dev.virtualplanet.rehabapp.R
import dev.virtualplanet.rehabapp.controller.Controller
import java.util.*


class ProgressActivity : AppCompatActivity() {

    private val controller = Controller

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress)
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
                findViewById<TextView>(R.id.progress_header).text = "Resumen de ejercicios"
            }
        }
        /*val selSpiner = findViewById<Spinner>(R.id.select_static_spinner)
        val values = ArrayList<String>()
        values.add("Número de ejercicios")

        val selAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, values)
        selSpiner.adapter = selAdapter*/


        val progressChart = findViewById<LineChart>(R.id.progress_content)
        progressChart.isVisible = false
        Controller.loadProgressData(this)


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
