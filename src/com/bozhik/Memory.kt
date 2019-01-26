package com.bozhik

import com.bozhik.struct.Session
import javafx.scene.media.MediaPlayer

object Memory {
    var nameList = ArrayList<String>()
    var playList = ArrayList<MediaPlayer>()
    lateinit var session: Session;
    var next : String = ""
    val register = "http://localhost:14878/register"
    val contText = "You firstly need to start the server!"

}
