package dev.virtualplanet.rehabapp.controller

import dev.virtualplanet.rehabapp.model.Exercice
import dev.virtualplanet.rehabapp.model.ExerciceList

object Controller {

    val ex_list = ExerciceList()

    fun getExerciceByName(name: String) : Exercice? {
        return ex_list.getExerciceByName(name)
    }

    fun getExerciceByID(id: String) : Exercice? {
        return null
    }

    fun getUser() {

    }



}