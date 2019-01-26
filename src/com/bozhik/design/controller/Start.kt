package com.bozhik.design.controller

import com.bozhik.Memory

import com.bozhik.struct.Session
import com.bozhik.struct.Song
import com.google.gson.Gson
import javafx.collections.FXCollections
import javafx.concurrent.Task
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.*
import javafx.scene.image.ImageView
import javafx.scene.media.Media
import javafx.scene.media.MediaPlayer
import javafx.scene.media.MediaView
import javafx.stage.DirectoryChooser
import java.net.URL
import khttp.post
import java.io.File
import java.net.URISyntaxException
import java.util.*
import kotlin.collections.ArrayList

class Start: Initializable {

    @FXML
    private lateinit var  listViewTrack: ListView<String>
    @FXML
    private lateinit var progressIndicator: ProgressIndicator
    @FXML
    private lateinit  var label : Label
    @FXML
    private lateinit var playButton: ImageView
    @FXML
    private lateinit var stopButton : ImageView
    @FXML
    private lateinit var session : TextField


    companion object {
        var listOfTrack = FXCollections.observableArrayList<String>()!!
        var mediaView : MediaView? = null
    }

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        listViewTrack!!.items = listOfTrack
        try {
            val r = post(Memory.register)
            Memory.session = Gson().fromJson(r.text, Session::class.java)
            session.text += " " + Memory.session.url
            label.text = Memory.next
        }catch (e:Exception){
            val alert = Alert(Alert.AlertType.ERROR)
            alert.headerText = null
            alert.contentText = Memory.contText
            alert.showAndWait()
            clickExit()
        }
    }
    @FXML
    private fun clickOpen() {
        var path:String = ""
        val directoryChooser = DirectoryChooser()
        val selectedFile =  directoryChooser.showDialog(null)
        val folder = File(selectedFile.absolutePath)
        Thread {
                try {

                for (file in folder.listFiles()) {
                    println (1)
                    println (folder.listFiles().size)
                    listOfTrack.add(file.name)
                    path = file.path.replace("\\", "/")
                    Memory.nameList.add(file.name)
                    post("http://localhost:14878//add?url=${Memory.session.url}&password=${Memory.session.password}&name=${file.name}")
                    Memory.playList.add(MediaPlayer(Media(File(path).toURI().toString())))
                }
                }catch (e: URISyntaxException){

                }

        }.start()

    }

    @FXML
    private fun play () {
        if (mediaView == null) {
            getMusic()
            mediaView = MediaView(Memory.playList.get(Memory.nameList.indexOf(Memory.next)))
            val progr = SoundPlayer()
            val thread = Thread(progr)
            label.text = Memory.next
            progressIndicator!!.progressProperty().bind(progr.valueProperty())
            thread.start()

        }
            mediaView!!.mediaPlayer.play()
            getEnd()
            playButton.isVisible = false
            stopButton.isVisible = true

        }
        private fun getEnd (){
            mediaView!!.mediaPlayer!!.setOnEndOfMedia {
                getMusic()
                mediaView!!.mediaPlayer = Memory.playList.get(Memory.nameList.indexOf(Memory.next))
                mediaView!!.mediaPlayer.seek(mediaView!!.mediaPlayer.startTime)
                mediaView!!.mediaPlayer.play()
                label.text = Memory.next
                getEnd()
            }
        }
        @FXML
        private fun stop (){
            mediaView!!.mediaPlayer.stop()
        }
        @FXML
        private fun pause(){
            mediaView!!.mediaPlayer.pause()
            playButton.isVisible = true
            stopButton.isVisible = false
        }


        class SoundPlayer : Task<Double>() {
            override fun call(): Double {
                while (1 < 2)
                    updateValue(1.0 * mediaView!!.mediaPlayer.getCurrentTime().toMillis() / mediaView!!.mediaPlayer.getTotalDuration().toMillis());

            return 0.0
        }
    }

    @FXML
    private fun removeTrack() {
        val indx = listViewTrack?.selectionModel?.selectedIndex
        Memory.playList.removeAt(indx!!)
        Memory.nameList.removeAt(indx!!)
        listOfTrack.removeAt(indx)
    }

    @FXML
    private fun clickExit() {
        System.exit(0)
    }
    private fun getMusic (){
            var song: Song
            var next = post("http://localhost:14878/next?url=${Memory.session.url}&password=${Memory.session.password}").jsonArray.get(0)
            song = Gson().fromJson(next.toString(), Song::class.java)
            Memory.next = song.name
    }


}