package dev.virtualplanet.rehabapp.controller.data

import android.graphics.Color
import android.widget.TextView
import androidx.core.view.isVisible
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import dev.virtualplanet.rehabapp.R
import dev.virtualplanet.rehabapp.controller.Controller
import dev.virtualplanet.rehabapp.controller.utils.Calculator
import dev.virtualplanet.rehabapp.view.BarProgressActivity
import dev.virtualplanet.rehabapp.view.LinearProgressActivity

object ProgressDataManager {

    fun loadExericeProgressData(context: LinearProgressActivity, user: String){
        Controller.data.collection(Controller.progressTable).document(user).get().addOnSuccessListener {
            val yValues = ArrayList<Entry>()
            val list = ArrayList<Int>()
            for (x in 1 until 32) {
                val actual : String? = it.get(Controller.craftData(x)).toString()
                if (actual != null && actual != "" && actual != "null") {
                    if (actual.toInt() > 0){
                        yValues.add(Entry(x.toFloat(), actual.toFloat()))
                        list.add(actual.toInt())
                    }
                }
            }
            setExerciceLineChartData(context, yValues, list)
        }
    }

    fun loadExericeProgressData(context: LinearProgressActivity, month : Int, year: Int, user: String){
        val month2 = month+1
        Controller.data.collection(Controller.progressTable).document(user).get().addOnSuccessListener {
            val yValues = ArrayList<Entry>()
            val list = ArrayList<Int>()
            for (x in 1 until 32) {
                val actual : String? = it.get(Controller.craftData(x, month2, year)).toString()
                if (actual != null && actual != "" && actual != "null") {
                    if (actual.toInt() > 0){
                        yValues.add(Entry(x.toFloat(), actual.toFloat()))
                        list.add(actual.toInt())
                    }
                }
            }
            setExerciceLineChartData(context, yValues, list)
        }
    }

    private fun setExerciceLineChartData(context: LinearProgressActivity, values: ArrayList<Entry>, list : ArrayList<Int>) {
        context.findViewById<TextView>(R.id.progress_media).text = ("Media: " + Calculator.calculateAverage(
            list
        ).toString())
        val chart = context.findViewById<LineChart>(R.id.progress_content)
        val set1 = LineDataSet(values, "Número de Ejercicios")
        set1.lineWidth = 6f
        set1.circleRadius = 6f
        set1.valueTextSize = 15f
        set1.setCircleColor(Color.LTGRAY)
        set1.setDrawCircleHole(false)
        val dataSets = ArrayList<ILineDataSet>()
        dataSets.add(set1)
        val dat = LineData(dataSets)
        chart.data = dat
        chart.isVisible = true
        chart.invalidate()

    }


    fun loadExericeProgressData(context: BarProgressActivity, user: String){
        Controller.data.collection(Controller.progressTable).document(user).get().addOnSuccessListener {
            val yValues = ArrayList<BarEntry>()
            val list = ArrayList<Int>()
            for (x in 1 until 32) {
                val actual : String? = it.get(Controller.craftData(x)).toString()
                if (actual != null && actual != "" && actual != "null") {
                    if (actual.toInt() > 0){
                        yValues.add(BarEntry(x.toFloat(), actual.toFloat()))
                        list.add(actual.toInt())
                    }
                }
            }
            setExericeBarChartData(context, yValues, list)
        }
    }

    fun loadExericeProgressData(context: BarProgressActivity, month : Int, year: Int, user: String){
        val month2 = month+1
        Controller.data.collection(Controller.progressTable).document(user).get().addOnSuccessListener {
            val yValues = ArrayList<BarEntry>()
            val list = ArrayList<Int>()
            for (x in 1 until 32) {
                val actual : String? = it.get(Controller.craftData(x, month2, year)).toString()
                if (actual != null && actual != "" && actual != "null") {
                    if (actual.toInt() > 0){
                        yValues.add(BarEntry(x.toFloat(), actual.toFloat()))
                        list.add(actual.toInt())
                    }
                }
            }
            setExericeBarChartData(context, yValues, list)

        }

    }

    private fun setExericeBarChartData(context: BarProgressActivity, values: ArrayList<BarEntry>, list : ArrayList<Int>) {
        context.findViewById<TextView>(R.id.bar_progress_media).text = ("Media: " + Calculator.calculateAverage(
            list
        ).toString())
        val chart = context.findViewById<BarChart>(R.id.bar_progress_content)
        val set1 = BarDataSet(values, "Número de ejercicios")
        set1.setDrawIcons(false)
        set1.valueTextSize = 15f
        set1.barBorderColor = Color.LTGRAY
        set1.barBorderWidth = 2f
        val dataSets: java.util.ArrayList<IBarDataSet> = java.util.ArrayList()
        dataSets.add(set1)
        val data = BarData(dataSets)
        data.barWidth = 1.5f
        chart.data = data
        chart.isVisible = true
        chart.invalidate()

    }


}