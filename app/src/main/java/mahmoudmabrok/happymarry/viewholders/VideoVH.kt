package mahmoudmabrok.happymarry.viewholders

import android.view.View
import kotlinx.android.synthetic.main.item_video.view.*
import mahmoudmabrok.happymarry.dataLayer.models.VideoListItem
import me.ibrahimyilmaz.kiel.core.RecyclerViewHolder

class VideoVH(view: View) : RecyclerViewHolder<VideoListItem>(view) {
    var data: VideoListItem? = null

    fun bind(item: VideoListItem) {
        data = item
        itemView.tvTitle?.text = item.title
        itemView.tvCount?.text = item.items?.size.toString()
    }
}