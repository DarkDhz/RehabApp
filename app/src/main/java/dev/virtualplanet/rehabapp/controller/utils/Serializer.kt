package dev.virtualplanet.rehabapp.controller.utils

import android.util.Log

object Serializer {

    fun serializeList(item: List<Int>) : String {
        if (item.isEmpty()) {
            return "-1"
        }

        var serialized = ""

        for (it in item) {
            serialized += "$it;"
        }
        return serialized
    }


    fun deserializeList(item: String) : ArrayList<Int> {

        val toReturn = ArrayList<Int>()
        Log.i("myapp23", item)
        val list = item.split(";")
        for (it in list) {
            if (it != "" && it.toInt() >= 0 && it.toInt() < 11) {
                toReturn.add(it.toInt())
            }

        }
        return toReturn
    }
}