package mahmoudmabrok.happymarry.viewholders

import android.view.View
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import kotlinx.android.synthetic.main.rv_detail_video_item.view.*
import mahmoudmabrok.happymarry.dataLayer.models.Video
import mahmoudmabrok.happymarry.util.Logger
import mahmoudmabrok.happymarry.util.getID
import me.ibrahimyilmaz.kiel.core.RecyclerViewHolder

class VideoDetailVH2(view: View) : RecyclerViewHolder<Video>(view) {
    var data: Video? = null
    var player: YouTubePlayer? = null

    fun bind(item: Video) {
        data = item
        itemView.tvTitle?.text = item.name
        cueVideo()
    }

    fun cueVideo() {
        val newUrl = data?.url?.getID() ?: ""
        Logger.log("VideoDetailVH2 cueVideo: $newUrl")
        player?.cueVideo(newUrl, 0f)
    }
}