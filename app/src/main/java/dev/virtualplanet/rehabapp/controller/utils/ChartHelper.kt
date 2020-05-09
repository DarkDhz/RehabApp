package dev.virtualplanet.rehabapp.controller.utils

import android.graphics.Color
import android.widget.TextView
import androidx.core.view.isVisible
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import dev.virtualplanet.rehabapp.R
import dev.virtualplanet.rehabapp.view.BarProgressActivity
import dev.virtualplanet.rehabapp.view.LinearProgressActivity

object ChartHelper {

    fun setExericeBarChartData(context: BarProgressActivity, values: ArrayList<BarEntry>, list : ArrayList<Int>) {
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

    fun setExerciceLineChartData(context: LinearProgressActivity, values: ArrayList<Entry>, list : ArrayList<Int>) {
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
}