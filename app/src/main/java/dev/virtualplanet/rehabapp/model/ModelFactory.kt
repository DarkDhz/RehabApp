package dev.virtualplanet.rehabapp.model

import android.media.MediaPlayer
import android.widget.MediaController
import android.widget.VideoView

object ModelFactory {

    fun makeExerice(n: String, des: String, rep: Int, ser: Int, time: Int, path : String, videoView : VideoView, mediaController : MediaController): Exercice {
        return Exercice(n, des, rep, ser, time, path, videoView, mediaController)
    }

    fun makeUser(user: String, pass: String, mail: String): User {
        return User(user, pass, mail)
    }

    fun makeUser(user: String, pass: String, mail: String, sex : Int, height: Int, weight : Int, wheel : Boolean): User {
        return User(user, pass, mail, sex, height, weight, wheel)
    }

    fun makeExerciceList() : ExerciceList {
        return ExerciceList()
    }

    fun makeExerciceList(list: List<Exercice>) : ExerciceList {
        return ExerciceList(list)
    }
}