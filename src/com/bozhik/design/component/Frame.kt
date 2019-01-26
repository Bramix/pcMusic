package com.bozhik.design.component


import com.bozhik.Main
import javafx.fxml.*
import javafx.scene.*
import javafx.scene.image.Image
import javafx.stage.*

class Frame(path: String): Stage() {

    init {
        val root = FXMLLoader.load<Parent>(Main::class.java.getResource(path))
        scene = Scene(root)
        title = "PartyPlayer"
    }
}