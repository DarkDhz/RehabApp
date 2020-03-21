package dev.virtualplanet.rehabapp.model

class ExerciceList {

    var content = ArrayList<Exercice>()

    constructor() {
        //NOTHING
    }

    constructor(content: ArrayList<Exercice>) {
        this.content = content
    }

    fun getExerciceByName(name: String) : Exercice? {
        return null
    }
}