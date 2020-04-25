package dev.virtualplanet.rehabapp.model

import android.media.MediaPlayer
import android.net.Uri
import android.view.View
import android.widget.MediaController
import android.widget.VideoView
import com.google.common.reflect.Reflection.getPackageName

class Exercice{


    var name = String()
    var description = String()
    var num_rep : Int
    var num_ser : Int
    var break_time : Int
    lateinit var path : String
    lateinit var videoView : VideoView
    lateinit var mediaController : MediaController

    constructor(n: String, des: String, rep: Int, ser: Int, time: Int, path : String, videoView : VideoView, mediaController : MediaController) {
        this.name = n
        this.description = des
        this.num_rep = rep
        this.num_ser = ser
        this.break_time = time
        this.path = path
        this.videoView = videoView
        this.mediaController = mediaController
    }

    fun playExercice() {
        var uri : Uri = Uri.parse(path)
        videoView.setVideoURI(uri)
        videoView.setMediaController(mediaController)
        mediaController.setAnchorView(videoView)
        videoView.start()
    }

}