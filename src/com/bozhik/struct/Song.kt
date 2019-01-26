package com.bozhik.struct


import com.google.gson.annotations.SerializedName


data class Song(
        @SerializedName("famous") val famous: Double,
        @SerializedName("isUse") val isUse: Boolean,
        @SerializedName("id") val id: Long,
        @SerializedName("name") val name: String)
