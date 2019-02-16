package com.bozhik;

import com.bozhik.design.Path.Start
import com.bozhik.design.component.Frame
import com.bozhik.struct.Session
import javafx.application.Application
import javafx.application.Application.launch
import javafx.stage.Stage
import khttp.get
import khttp.post
import org.apache.logging.log4j.message.MapMessage.MapFormat.names
import com.google.gson.GsonBuilder
import com.google.gson.Gson
import javafx.scene.control.Alert
import javafx.scene.media.Media
import javafx.scene.media.MediaPlayer
import javafx.stage.DirectoryChooser
import java.io.File
import java.net.URISyntaxException


class Main: Application() {
    override fun start(primaryStage: Stage?) {
        val frame = Frame(Start.path)
        frame.show()
    }
}
fun main(args: Array<String>) {
    launch(Main::class.java)
}