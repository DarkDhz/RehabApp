package dev.virtualplanet.rehabapp.model

class ExerciceList {
    private var i : Int = 0
    private val factory = ModelFactory
    var content : List<Exercice>

    constructor() {
        content = ArrayList<Exercice>()
        i = 0
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

    fun playNetxtExercici() {
        content[i].playExercice()
        i++
    }
}