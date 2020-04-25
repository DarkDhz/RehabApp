package dev.virtualplanet.rehabapp.model

class ExerciceList {
    private var i : Int = 0
    private var content : ArrayList<Exercice>

    constructor() {
        content = ArrayList<Exercice>()
        i = 0
        //NOTHING
    }

    constructor(content: ArrayList<Exercice>) {
        this.content = content
    }

    fun add(ex: Exercice) {
        if (validateExercice(ex)) {
            content.add(ex)
        }
    }

    private fun validateExercice(ex : Exercice) : Boolean {
        for (item in content) {
            if (item.isSimilar(ex)) {
                return false
            }
        }
        return true
    }

    fun getExerciceByName(name: String) : Exercice? {
        for (exercice in content) {
            if (exercice.name == name) {
                return exercice
            }
        }
        return null
    }

    fun getSize() : Int {
        return content.size
    }

    fun playNextExercice() : Exercice? {
        if (i < content.size) {
            val toReturn = content[i]
            i++
            return toReturn

        }
        return null
    }

}