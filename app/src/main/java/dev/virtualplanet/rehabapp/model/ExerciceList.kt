package dev.virtualplanet.rehabapp.model

import android.view.Display
import dev.virtualplanet.rehabapp.R
import dev.virtualplanet.rehabapp.controller.Controller

class ExerciceList {
    private var i : Int = 0
    var content : ArrayList<Exercice>

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

    class Returnable {
        var exercice : Exercice
        var pos : Int
        var size : Int

        constructor(ex: Exercice, p: Int, s: Int) {
            exercice = ex
            pos = p
            size = s
        }

    }

    fun playNextExercice() : Returnable? {
        if (i < content.size) {
            val toReturn = content[i]
            i++
            return Returnable(toReturn, i, getSize())

        }
        return null
    }

    fun loadList(exerciceList: ExerciceList, name: String, path: String) {
        when (name) {
            "Hombro" -> {

                exerciceList.add(
                    ModelFactory.makeExerice(
                        "Flexión del Hombro",
                        "Eleve su brazo hasta señalar el techo manteniendo su codo.",
                        15,
                        3,
                        30,
                        path + R.raw.hombro_flexion
                    )
                )

                exerciceList.add(
                    ModelFactory.makeExerice(
                        "Rotación del Hombro con apoyo",
                        "Mantenga el codo apoyado en un lugar (como muestra la figura) y las escápulas (paletas) hacia abajo y juntas. Gire el antebrazo hacia delante y hacia atrás.",
                        10,
                        1,
                        20,
                        path + R.raw.hombro_rot_in
                    )
                )

                exerciceList.add(
                    ModelFactory.makeExerice(
                        "Caminado por pared de dedos",
                        "Con el codo, utilice los dedos para caminar hacia arriba por la pared o en el marco de la puerta lo más alto posible.",
                        10,
                        3,
                        20,
                        path + R.raw.hombro_pared
                    )
                )

                exerciceList.add(
                    ModelFactory.makeExerice(
                        "Rotación Interna del Hombro",
                        "Lleve su mano detrás de la espalda y hacia el lado opuesto.",
                        10,
                        1,
                        20,
                        path + R.raw.hombro_rotacion
                    )
                )

                exerciceList.add(
                    ModelFactory.makeExerice(
                        "Abducción del Hombro",
                        "Levante los brazos lateralmente, con los codos y las palmas hacia abajo. No encoja los hombros, ni incline el tronco.",
                        10,
                        3,
                        20,
                        path + R.raw.hombro_abd
                    )
                )
                return
            }
            "Codo" -> {
                exerciceList.add(
                    ModelFactory.makeExerice(
                        "Flexo-Extensión del codo",
                        "Elevar lentamente la extremidad superior hasta que esté totalmente horizontal con el codo extendido.",
                        15,
                        3,
                        30,
                        path + R.raw.codo_fl_ex
                    )
                )

                exerciceList.add(
                    ModelFactory.makeExerice(
                        "Flexión de codo",
                        "Realizar rotación de la extremidad de forma que la mano (conel peso) mire hacia arriba.",
                        15,
                        3,
                        30,
                        path + R.raw.codo_fl
                    )
                )

                exerciceList.add(
                    ModelFactory.makeExerice(
                        "Flexo-Extensión del codo estirado",
                        "Estirado, los brazos extendidos lateralmente a la altura de los hombros,  flexión-extensión de los antebrazos sobre los brazos.",
                        15,
                        3,
                        30,
                        path + R.raw.codo_estirado
                    )
                )

                exerciceList.add(
                    ModelFactory.makeExerice(
                        "Flexo-Extensión del codo frontal",
                        "Sentado en una silla o de pie, las manos juntas sobre el pecho, extender los brazos hacia delante.",
                        15,
                        3,
                        30,
                        path + R.raw.codo_adelante
                    )
                )

                return
            }
        }
    }

}