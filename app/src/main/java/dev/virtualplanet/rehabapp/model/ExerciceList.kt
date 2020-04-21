package dev.virtualplanet.rehabapp.model

class ExerciceList {

    var content : List<Exercice>

    constructor() {
        content = ArrayList<Exercice>()
        //NOTHING
    }

    constructor(content: List<Exercice>) {
        this.content = content
    }

    fun getExerciceByName(name: String) : Exercice? {
        for (exercice in content) {
            if (exercice.name == name) {
                return exercice
            }
        }
        return null
    }
}