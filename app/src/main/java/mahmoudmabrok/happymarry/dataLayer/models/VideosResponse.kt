package mahmoudmabrok.happymarry.dataLayer.models


import com.google.gson.annotations.SerializedName

data class VideosResponse(
    @SerializedName("videos")
    val videos: List<Video>? = null
)