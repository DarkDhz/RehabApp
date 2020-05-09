package dev.virtualplanet.rehabapp.controller.data.progress

import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.google.firebase.firestore.DocumentSnapshot

interface DataManager {

    fun addData(user: String, num: Int)
    fun generateData(user: String)
    fun loadB(barData: ArrayList<BarEntry>, counter: ArrayList<Int>, it : DocumentSnapshot)
    fun loadB(barData: ArrayList<BarEntry>, counter: ArrayList<Int>, it : DocumentSnapshot, month: Int, year: Int)

    fun loadL(lineData: ArrayList<Entry>, counter: ArrayList<Int>, it : DocumentSnapshot)
    fun loadL(lineData: ArrayList<Entry>, counter: ArrayList<Int>, it : DocumentSnapshot, month: Int, year: Int)

}