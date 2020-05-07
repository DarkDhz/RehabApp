package dev.virtualplanet.rehabapp.controller.utils

import java.text.SimpleDateFormat
import java.util.*

object CraftData {

    fun craftData() : String {
        return SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().time)

    }

    fun craftData(day: Int) : String {
        val month : String = SimpleDateFormat("MM-yyyy").format(Calendar.getInstance().time)
        when (day > 9) {
            true -> return ("$day-$month")
            false -> return ("0$day-$month")
        }
    }

    fun craftData(day: Int, month: Int) : String {
        val year : String = SimpleDateFormat("yyyy").format(Calendar.getInstance().time)
        when (month > 9) {
            true -> return ("$day-$month-$year")
            false -> return ("$day-0$month-$year")
        }

    }

    fun craftData(day: Int, month: Int, year: Int) : String {
        when (month > 9) {
            true -> {
                when (day > 9) {
                    true -> return ("$day-$month-$year")
                    false -> return ("0$day-$month-$year")
                }
            }
            false -> {
                when (day > 9) {
                    true -> return ("$day-0$month-$year")
                    false -> return ("0$day-0$month-$year")

                }

            }
        }

    }
}