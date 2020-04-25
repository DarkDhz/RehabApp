package dev.virtualplanet.rehabapp.model

import android.text.style.LineHeightSpan

object User {

    var user : String = ""
    var password : String = ""
    var mail : String = ""

    var sex : Int = 0// 0 man, 1 Woman

    //HEALTH VARS
    private var age = 0
    //IN CM
    var height = 0
    //IN KG
    var weight = 0
    //WHEELCHAIR ?
    var wheel : Boolean = false

    //private var num = 0

    fun modifyPassword(newPass: String, oldPass: String ) : Boolean {
        if (oldPass != password) {
            return false
        } else {
            password = newPass
            return true
        }
    }


    fun setHeight(h: Int) : Boolean {
        if (h <= 0) {
            return false
        } else {
            height = h
            return true
        }
    }

    fun setWeight(w: Int) : Boolean {
        if (w <= 0) {
            return false
        } else {
            weight = w
            return true
        }
    }

    fun calculateIMC() : Int {
        //kg/m^2
        return (weight/(height*height))
    }

}