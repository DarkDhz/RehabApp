package dev.virtualplanet.rehabapp.controller.data

import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import dev.virtualplanet.rehabapp.controller.Controller
import dev.virtualplanet.rehabapp.controller.data.progress.DataManager
import dev.virtualplanet.rehabapp.controller.data.progress.ExerciceDataManager
import dev.virtualplanet.rehabapp.controller.data.progress.MovilityDataManager
import dev.virtualplanet.rehabapp.controller.utils.ChartHelper
import dev.virtualplanet.rehabapp.view.BarProgressActivity
import dev.virtualplanet.rehabapp.view.LinearProgressActivity

object ProgressDataManager {


    private val managers : List<DataManager> = init()

    private fun init() : ArrayList<DataManager> {
        val toReturn = ArrayList<DataManager>()
        toReturn.add(ExerciceDataManager())
        toReturn.add(MovilityDataManager())
        return toReturn
    }

    fun load(context: LinearProgressActivity, user: String, table: String){
        Controller.data.collection(table).document(user).get().addOnSuccessListener {
            val yValues = ArrayList<Entry>()
            val list = ArrayList<Int>()
            for (manager in managers) {
                if (manager.isTable(table)) {
                    manager.loadL(yValues, list, it)
                    break
                }
            }
            ChartHelper.setExerciceLineChartData(context, yValues, list, table)
        }
    }

    fun load(context: LinearProgressActivity, month : Int, year: Int, user: String, table: String){
        val month2 = month+1
        Controller.data.collection(table).document(user).get().addOnSuccessListener {
            val yValues = ArrayList<Entry>()
            val list = ArrayList<Int>()
            for (manager in managers) {
                if (manager.isTable(table)) {
                    manager.loadL(yValues, list, it, month2, year)
                    break
                }
            }
            ChartHelper.setExerciceLineChartData(context, yValues, list, table)
        }
    }

    fun load(context: BarProgressActivity, user: String, table: String){
        Controller.data.collection(table).document(user).get().addOnSuccessListener {
            val yValues = ArrayList<BarEntry>()
            val list = ArrayList<Int>()
            for (manager in managers) {
                if (manager.isTable(table)) {
                    manager.loadB(yValues, list, it)
                    break
                }
            }
            ChartHelper.setExericeBarChartData(context, yValues, list, table)
        }
    }


    fun load(context: BarProgressActivity, month : Int, year: Int, user: String, table: String){
        val month2 = month+1
        Controller.data.collection(table).document(user).get().addOnSuccessListener {
            val yValues = ArrayList<BarEntry>()
            val list = ArrayList<Int>()
            for (manager in managers) {
                if (manager.isTable(table)) {
                    manager.loadB(yValues, list, it, month2, year)
                    break
                }
            }
            ChartHelper.setExericeBarChartData(context, yValues, list, table)
        }

    }

    fun generateData(user: String, table: String) {
        for (manager in managers) {
            if (manager.isTable(table)) {
                manager.generateData(user)
                break
            }
        }
    }

    fun add(user: String, num: Int, table: String) {
        for (manager in managers) {
            if (manager.isTable(table)) {
                manager.addData(user, num)
                break
            }
        }
    }






}