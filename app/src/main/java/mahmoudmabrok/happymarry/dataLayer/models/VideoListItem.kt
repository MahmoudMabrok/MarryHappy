package mahmoudmabrok.happymarry.dataLayer.models


data class VideoListItem(
    var isPlaylist: Boolean = false,
    val title: String? = null,
    val url: String? = null,
    val items: List<Video>? = null
)