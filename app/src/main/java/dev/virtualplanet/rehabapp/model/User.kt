package dev.virtualplanet.rehabapp.model

class User {

    private var user = String()
    private var password = String()
    private var mail = String()

    private var sex = 0 // 0 man, 1 Woman

    //HEALTH VARS
    private var age = 0
    //IN CM
    private var height = 0
    //IN KG
    private var weight = 0
    //WHEELCHAIR ?
    private var wheel = false

    //private var num = 0

    constructor(us: String, pass: String, ma: String) {
        user = us
        password = pass
        mail = ma
    }




    fun getUser() : String {
        return user
    }

    fun getMail() : String {
        return mail
    }

    fun modifyPassword(newPass: String, oldPass: String ) : Boolean {
        if (oldPass != password) {
            return false
        } else {
            password = newPass
            return true
        }
    }

    fun getPassword() : String {
        return password
    }

    fun getAge() : Int {
        return age
    }

    fun getHeight() : Int {
        return height
    }

    fun setHeight(h: Int) : Boolean {
        if (h <= 0) {
            return false
        } else {
            height = h
            return true
        }
    }

    fun getWeight() : Int {
        return weight
    }

    fun setWeight(w: Int) : Boolean {
        if (w <= 0) {
            return false
        } else {
            weight = w
            return true
        }
    }

    fun getWheelChair() : Boolean {
        return wheel
    }

    fun setWheelChair(w: Boolean) {
        wheel = w
    }

    fun calculateIMC() : Int {
        //kg/m^2
        return (weight/(height*height))
    }

}