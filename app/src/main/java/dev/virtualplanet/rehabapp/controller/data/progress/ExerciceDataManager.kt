package dev.virtualplanet.rehabapp.controller.data.progress

import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.google.firebase.firestore.DocumentSnapshot
import dev.virtualplanet.rehabapp.controller.Controller
import dev.virtualplanet.rehabapp.controller.utils.Calculator
import dev.virtualplanet.rehabapp.controller.utils.CraftData
import dev.virtualplanet.rehabapp.controller.utils.Serializer

class ExerciceDataManager : DataManager{

    private val table = Controller.progressExerciceTable

    override fun loadB(barData: ArrayList<BarEntry>, counter: ArrayList<Int>, it : DocumentSnapshot) {
        for (x in 1 until 32) {
            val actual : String? = it.get(CraftData.craftData(x)).toString()
            if (actual != null && actual != "" && actual != "null") {
                if (actual.toInt() > 0){
                    barData.add(BarEntry(x.toFloat(), actual.toFloat()))
                    counter.add(actual.toInt())
                }
            }
        }
    }

    override fun loadB(barData: ArrayList<BarEntry>, counter: ArrayList<Int>, it : DocumentSnapshot, month: Int, year: Int) {
        for (x in 1 until 32) {
            val actual : String? = it.get(CraftData.craftData(x, month, year)).toString()
            if (actual != null && actual != "" && actual != "null") {
                if (actual.toInt() > 0){
                    barData.add(BarEntry(x.toFloat(), actual.toFloat()))
                    counter.add(actual.toInt())
                }
            }
        }
    }

    override fun loadL(lineData: ArrayList<Entry>, counter: ArrayList<Int>, it : DocumentSnapshot) {
        for (x in 1 until 32) {
            val actual : String? = it.get(CraftData.craftData(x)).toString()
            if (actual != null && actual != "" && actual != "null") {
                if (actual.toInt() > 0){
                    lineData.add(Entry(x.toFloat(), actual.toFloat()))
                    counter.add(actual.toInt())
                }
            }
        }
    }

    override fun loadL(lineData: ArrayList<Entry>, counter: ArrayList<Int>, it : DocumentSnapshot, month: Int, year: Int) {
        for (x in 1 until 32) {
            val actual : String? = it.get(CraftData.craftData(x, month, year)).toString()
            if (actual != null && actual != "" && actual != "null") {
                if (actual.toInt() > 0){
                    lineData.add(Entry(x.toFloat(), actual.toFloat()))
                    counter.add(actual.toInt())
                }
            }
        }
    }

    override fun generateData(user: String) {
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

    override fun addData(user: String, num: Int) {
        if (user != "") {
            val date = CraftData.craftData()

            Controller.data.collection(table).document(user).get().addOnSuccessListener {
                val actual : String? = it.get(date).toString()

                if (actual == null || actual != "" || actual == "null") {
                    val act = actual!!.toInt()
                    Controller.data.collection(table).document(user).update(
                        mapOf(
                            date to (act + num)
                        )
                    )
                }
            }
        }
    }

}