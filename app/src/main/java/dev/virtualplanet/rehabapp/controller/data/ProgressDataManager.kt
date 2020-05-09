package dev.virtualplanet.rehabapp.controller.data

import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import dev.virtualplanet.rehabapp.controller.Controller
import dev.virtualplanet.rehabapp.controller.utils.ChartHelper
import dev.virtualplanet.rehabapp.controller.utils.CraftData
import dev.virtualplanet.rehabapp.view.BarProgressActivity
import dev.virtualplanet.rehabapp.view.LinearProgressActivity

object ProgressDataManager {

    private val exManager : DataManager = ExerciceManager()
    private val movManager : DataManager = MovilityDataManager()

    fun load(context: LinearProgressActivity, user: String, table: String){
        Controller.data.collection(table).document(user).get().addOnSuccessListener {
            val yValues = ArrayList<Entry>()
            val list = ArrayList<Int>()
            for (x in 1 until 32) {
                val actual : String? = it.get(CraftData.craftData(x)).toString()
                if (actual != null && actual != "" && actual != "null") {
                    if (actual.toInt() > 0){
                        yValues.add(Entry(x.toFloat(), actual.toFloat()))
                        list.add(actual.toInt())
                    }
                }
            }
            ChartHelper.setExerciceLineChartData(context, yValues, list)
        }
    }

    fun load(context: LinearProgressActivity, month : Int, year: Int, user: String, table: String){
        val month2 = month+1
        Controller.data.collection(table).document(user).get().addOnSuccessListener {
            val yValues = ArrayList<Entry>()
            val list = ArrayList<Int>()
            for (x in 1 until 32) {
                val actual : String? = it.get(CraftData.craftData(x, month2, year)).toString()
                if (actual != null && actual != "" && actual != "null") {
                    if (actual.toInt() > 0){
                        yValues.add(Entry(x.toFloat(), actual.toFloat()))
                        list.add(actual.toInt())
                    }
                }
            }
            ChartHelper.setExerciceLineChartData(context, yValues, list)
        }
    }




    fun load(context: BarProgressActivity, user: String, table: String){
        Controller.data.collection(table).document(user).get().addOnSuccessListener {
            val yValues = ArrayList<BarEntry>()
            val list = ArrayList<Int>()
            for (x in 1 until 32) {
                val actual : String? = it.get(CraftData.craftData(x)).toString()
                if (actual != null && actual != "" && actual != "null") {
                    if (actual.toInt() > 0){
                        yValues.add(BarEntry(x.toFloat(), actual.toFloat()))
                        list.add(actual.toInt())
                    }
                }
            }
            ChartHelper.setExericeBarChartData(context, yValues, list)
        }
    }

    fun load(context: BarProgressActivity, month : Int, year: Int, user: String, table: String){
        val month2 = month+1
        Controller.data.collection(table).document(user).get().addOnSuccessListener {
            val yValues = ArrayList<BarEntry>()
            val list = ArrayList<Int>()
            for (x in 1 until 32) {
                val actual : String? = it.get(CraftData.craftData(x, month2, year)).toString()
                if (actual != null && actual != "" && actual != "null") {
                    if (actual.toInt() > 0){
                        yValues.add(BarEntry(x.toFloat(), actual.toFloat()))
                        list.add(actual.toInt())
                    }
                }
            }
            ChartHelper.setExericeBarChartData(context, yValues, list)

        }

    }

    fun generateData(user: String, table: String) {
        val date = CraftData.craftData()

        Controller.data.collection(table).document(user).get().addOnSuccessListener {
            val actual : String? = it.get(date).toString()
            if (actual == null || actual == "null") {
                Controller.data.collection(table).document(user).update(
                    mapOf(
                        date to 0
                    )
                )
            }
        }.addOnFailureListener {
            Controller.data.collection(table).document(user).update(
                mapOf(
                    date to 0
                )
            )
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


    fun generateTestData(mail: String) {
        Controller.data.collection(Controller.progressExerciceTable).document(mail).set(mapOf(
            CraftData.craftData() to 15,
            CraftData.craftData(23) to 0,
            CraftData.craftData(7) to 5,
            CraftData.craftData(18) to 9,
            CraftData.craftData(2) to 12
        ))
    }

    private interface DataManager{
        fun addData(user: String, num: Int)
    }



    private class ExerciceManager : DataManager{

        val table = Controller.progressExerciceTable

        override fun addData(user: String, num: Int) {
            if (user != "") {
                val date = CraftData.craftData()

                Controller.data.collection(Controller.progressExerciceTable).document(user.toString()).get().addOnSuccessListener {
                    val actual : String? = it.get(date).toString()

                    if (actual == null || actual != "" || actual == "null") {
                        val act = actual!!.toInt()
                        Controller.data.collection(Controller.progressExerciceTable).document(user.toString()).update(
                            mapOf(
                                date to (act + num)
                            )
                        )
                    }
                }
            }
        }

    }


    private class MovilityDataManager : DataManager {

        val table = Controller.progressMovilityTable

        override fun addData(user: String, num: Int) {

        }
    }
}