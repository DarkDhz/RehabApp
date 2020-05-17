package dev.virtualplanet.rehabapp.model

import android.media.MediaPlayer
import android.widget.MediaController
import android.widget.VideoView

object ModelFactory {

    fun makeExerice(n: String, des: String, rep: Int, ser: Int, time: Int, path : String): Exercice {
        return Exercice(n, des, rep, ser, time, path)
    }

    fun makeExerciceList() : ExerciceList {
        return ExerciceList()
    }

    fun makeExerciceList(list: ArrayList<Exercice>) : ExerciceList {
        return ExerciceList(list)
    }

    fun makeMedicine(name: String) : Medicine {
        return Medicine(name)
    }

    fun makeMedicineList() : MedicineList {
        return MedicineList()
    }
}