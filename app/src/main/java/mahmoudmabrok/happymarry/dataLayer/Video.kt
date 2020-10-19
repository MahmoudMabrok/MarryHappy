package mahmoudmabrok.happymarry.dataLayer


import com.google.gson.annotations.SerializedName

data class Video(
    @SerializedName("group")
    val group: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("url")
    val url: String? = null
)