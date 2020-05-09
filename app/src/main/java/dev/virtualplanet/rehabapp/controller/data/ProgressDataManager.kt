package dev.virtualplanet.rehabapp.controller.data

import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import dev.virtualplanet.rehabapp.controller.Controller
import dev.virtualplanet.rehabapp.controller.data.progress.DataManager
import dev.virtualplanet.rehabapp.controller.data.progress.ExerciceDataManager
import dev.virtualplanet.rehabapp.controller.data.progress.MovilityDataManager
import dev.virtualplanet.rehabapp.controller.utils.ChartHelper
import dev.virtualplanet.rehabapp.controller.utils.CraftData
import dev.virtualplanet.rehabapp.view.BarProgressActivity
import dev.virtualplanet.rehabapp.view.LinearProgressActivity

object ProgressDataManager {

    private val exManager : DataManager = ExerciceDataManager()
    private val movManager : DataManager = MovilityDataManager()

    fun load(context: LinearProgressActivity, user: String, table: String){
        Controller.data.collection(table).document(user).get().addOnSuccessListener {
            val yValues = ArrayList<Entry>()
            val list = ArrayList<Int>()
            when (table) {
                Controller.progressExerciceTable -> {
                    exManager.loadL(yValues, list, it)
                }
                Controller.progressMovilityTable -> {
                    movManager.loadL(yValues, list, it)
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
            when (table) {
                Controller.progressExerciceTable -> {
                    exManager.loadL(yValues, list, it, month2, year)
                }
                Controller.progressMovilityTable -> {
                    movManager.loadL(yValues, list, it, month2, year)
                }
            }
            ChartHelper.setExerciceLineChartData(context, yValues, list, table)
        }
    }

    fun load(context: BarProgressActivity, user: String, table: String){
        Controller.data.collection(table).document(user).get().addOnSuccessListener {
            val yValues = ArrayList<BarEntry>()
            val list = ArrayList<Int>()
            when (table) {
                Controller.progressExerciceTable -> {
                    exManager.loadB(yValues, list, it)
                }
                Controller.progressMovilityTable -> {
                    movManager.loadB(yValues, list, it)
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
            when (table) {
                Controller.progressExerciceTable -> {
                    exManager.loadB(yValues, list, it, month2, year)
                }
                Controller.progressMovilityTable -> {
                    movManager.loadB(yValues, list, it, month2, year)
                }
            }
            ChartHelper.setExericeBarChartData(context, yValues, list, table)
        }

    }

    fun generateData(user: String, table: String) {
        when (table) {
            Controller.progressMovilityTable -> {
                movManager.generateData(user)
            }
            Controller.progressExerciceTable -> {
                exManager.generateData(user)
            }
        }
    }

    fun add(user: String, num: Int, table: String) {
        when (table) {
            Controller.progressMovilityTable -> {
                movManager.addData(user, num)
            }
            Controller.progressExerciceTable -> {
                exManager.addData(user, num)
            }
        }
    }






}