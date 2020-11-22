package edu.mahmoud_mabrouk.happy_marry.dataLayer.models


import com.google.gson.annotations.SerializedName


data class NonVideo(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("url")
    val url: String? = null
)