package mahmoudmabrok.happymarry.viewholders

import android.view.View
import kotlinx.android.synthetic.main.item_video.view.*
import mahmoudmabrok.happymarry.dataLayer.models.Video
import me.ibrahimyilmaz.kiel.core.RecyclerViewHolder

class VideoVH(view: View) : RecyclerViewHolder<Video>(view) {
    var data: Video? = null

    fun bind(item: Video) {
        data = item
        itemView.tvTitle?.text = item.name
    }
}