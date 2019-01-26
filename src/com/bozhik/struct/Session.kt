package com.bozhik.struct

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Session(
    @SerializedName("url") val url: String,
    @SerializedName("password") val password: String )
