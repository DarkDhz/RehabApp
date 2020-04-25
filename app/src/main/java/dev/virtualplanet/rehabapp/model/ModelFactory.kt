package dev.virtualplanet.rehabapp.model

import android.media.MediaPlayer
import android.widget.MediaController
import android.widget.VideoView

object ModelFactory {

    fun makeExerice(n: String, des: String, rep: Int, ser: Int, time: Int, path : String, videoView : VideoView, mediaController : MediaController): Exercice {
        return Exercice(n, des, rep, ser, time, path, videoView, mediaController)
    }

    fun saveUser(user: String, pass: String, mail: String): User {
        User.user = user
        User.password = pass
        User.mail = mail
        return User
    }

    fun saveUser(pass: String, mail: String): User {
        User.password = pass
        User.mail = mail
        return User
    }

    fun saveUser(user: String, pass: String, mail: String, sex : Int, height: Int, weight : Int, wheel : Boolean) : User{
        User.user = user
        User.password = pass
        User.mail = mail
        User.sex = sex
        User.height = height
        User.weight = weight
        User.wheel = wheel
        return User
    }

    fun makeExerciceList() : ExerciceList {
        return ExerciceList()
    }

    fun makeExerciceList(list: List<Exercice>) : ExerciceList {
        return ExerciceList(list)
    }
}