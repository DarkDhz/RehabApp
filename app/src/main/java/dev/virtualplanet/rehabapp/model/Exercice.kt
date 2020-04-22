package dev.virtualplanet.rehabapp.model

import java.io.File

class Exercice {
    var name = String()
    var description = String()
    var num_rep : Int
    var num_ser : Int
    var break_time : Int
    var video : File

    constructor(n: String, des: String, rep: Int, ser: Int, time: Int, url: String) {
        this.name = n
        this.description = des
        this.num_rep = rep
        this.num_ser = ser
        this.break_time = time
        this.video = File(url)
    }

    fun playExercice() {

    }

}