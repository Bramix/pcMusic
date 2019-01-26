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



class Main: Application() {
    override fun start(primaryStage: Stage?) {
        val frame = Frame(Start.path)
        frame.show()
    }
}
fun main(args: Array<String>) {

    launch(Main::class.java)
}