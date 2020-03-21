package dev.virtualplanet.rehabapp.model

import java.io.File

class Exercice {
    var name = String()
    var description = String()
    var num_rep = 0
    var num_ser = 0
    var break_time = 0
    var video = File("")

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