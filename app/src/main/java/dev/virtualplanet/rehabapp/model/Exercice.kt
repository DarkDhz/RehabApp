package dev.virtualplanet.rehabapp.model

class Exercice{

    var name = String()
    var description = String()
    var numRep : Int
    var numSer : Int
    var breakTime : Int
    var path : String

    constructor(n: String, des: String, rep: Int, ser: Int, time: Int, path : String) {
        this.name = n
        this.description = des
        this.numRep = rep
        this.numSer = ser
        this.breakTime = time
        this.path = path
    }

    /*fun playExercice() {
        var uri : Uri = Uri.parse(path)
        videoView.setVideoURI(uri)
        videoView.setMediaController(mediaController)
        mediaController.setAnchorView(videoView)
        videoView.start()
    }*/

    fun isSimilar(ex : Exercice) : Boolean{
        return (ex.name == name)
    }

}