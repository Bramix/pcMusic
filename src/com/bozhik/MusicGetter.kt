package com.bozhik

import com.bozhik.struct.Song
import com.google.gson.Gson
import khttp.post
import org.json.JSONException

internal class MusicGetter() : Thread(){
    override fun run() {
        var max: Double
        var song: Song
        var next = post("http://localhost:14878/next?url=${Memory.session.url}&password=${Memory.session.password}").jsonArray.get(0)
        song = Gson().fromJson(next.toString(), Song::class.java)
        Memory.next = song.name
    }

}


