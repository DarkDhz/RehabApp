package dev.virtualplanet.rehabapp.model

object ModelFactory {

    fun makeExerice(n: String, des: String, rep: Int, ser: Int, time: Int, url: String): Exercice {
        return Exercice(n, des, rep, ser, time, url)
    }

    fun makeUser(user: String, pass: String, mail: String): User {
        return User(user, pass, mail)
    }

    fun makeUser(user: String, pass: String, mail: String, sex : Int, height: Int, weight : Int, wheel : Boolean): User {
        return User(user, pass, mail, sex, height, weight, wheel)
    }
}