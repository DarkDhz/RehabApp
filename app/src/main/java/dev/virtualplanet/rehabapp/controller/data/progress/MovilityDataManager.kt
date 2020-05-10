package dev.virtualplanet.rehabapp.controller.data.progress

import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.google.firebase.firestore.DocumentSnapshot
import dev.virtualplanet.rehabapp.controller.Controller
import dev.virtualplanet.rehabapp.controller.utils.Calculator
import dev.virtualplanet.rehabapp.controller.utils.CraftData
import dev.virtualplanet.rehabapp.controller.utils.Serializer

class MovilityDataManager : DataManager{

    private val table = Controller.progressMovilityTable

    override fun isTable(table: String) : Boolean {
        return (table.equals(this.table))
    }

    override fun loadB(barData: ArrayList<BarEntry>, counter: ArrayList<Int>, it : DocumentSnapshot) {
        for (x in 1 until 32) {
            val actual = it.get(CraftData.craftData(x))
            if (actual != null) {
                val list = Serializer.deserializeList(actual.toString())
                val average = Calculator.calculateAverage(list)
                barData.add(BarEntry(x.toFloat(), average.toFloat()))
                counter.add(average)
            }
        }
    }

    override fun loadB(barData: ArrayList<BarEntry>, counter: ArrayList<Int>, it : DocumentSnapshot, month: Int, year: Int) {
        for (x in 1 until 32) {
            val actual = it.get(CraftData.craftData(x, month, year))
            if (actual != null) {
                val list = Serializer.deserializeList(actual.toString())
                val average = Calculator.calculateAverage(list)
                barData.add(BarEntry(x.toFloat(), average.toFloat()))
                counter.add(average)
            }
        }
    }

    override fun loadL(lineData: ArrayList<Entry>, counter: ArrayList<Int>, it : DocumentSnapshot) {
        for (x in 1 until 32) {
            val actual = it.get(CraftData.craftData(x))
            if (actual != null) {
                val list = Serializer.deserializeList(actual.toString())
                val average = Calculator.calculateAverage(list)
                lineData.add(Entry(x.toFloat(), average.toFloat()))
                counter.add(average)
            }
        }
    }

    override fun loadL(lineData: ArrayList<Entry>, counter: ArrayList<Int>, it : DocumentSnapshot, month: Int, year: Int) {
        for (x in 1 until 32) {
            val actual = it.get(CraftData.craftData(x, month, year))
            if (actual != null) {
                val list = Serializer.deserializeList(actual.toString())
                val average = Calculator.calculateAverage(list)
                lineData.add(Entry(x.toFloat(), average.toFloat()))
                counter.add(average)
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
                        date to Serializer.serializeList(ArrayList())
                    )
                )
            }
        }.addOnFailureListener {
            Controller.data.collection(table).document(user).update(
                mapOf(
                    date to Serializer.serializeList(ArrayList())
                )
            )
        }
    }

    override fun addData(user: String, num: Int) {
        if (user != "") {
            val date = CraftData.craftData()
            Controller.data.collection(table).document(user).get().addOnSuccessListener {
                val actual  = it.getString(date)
                if (actual != null) {
                    val list = Serializer.deserializeList(actual.toString())
                    list.add(num)
                    Controller.data.collection(table).document(user).update(
                        mapOf(
                            date to Serializer.serializeList(list)
                        )
                    )

                }
            }.addOnFailureListener {
                Controller.data.collection(table).document(user).set(
                    mapOf(
                        date to Serializer.serializeList(ArrayList())
                    )
                )
            }
        }

    }
}