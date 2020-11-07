package mahmoudmabrok.happymarry.viewholders

import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_video_new.view.*
import mahmoudmabrok.happymarry.R
import mahmoudmabrok.happymarry.dataLayer.models.VideoListItem
import mahmoudmabrok.happymarry.util.getID
import me.ibrahimyilmaz.kiel.core.RecyclerViewHolder

class VideoVH(view: View) : RecyclerViewHolder<VideoListItem>(view) {
    var data: VideoListItem? = null

    fun bind(item: VideoListItem) {
        data = item
        itemView.tvTitle?.text = item.title
        itemView.tvCount.text = item.items?.size.toString()

        //  itemView.tvCount?.text = item.items?.size.toString()
        val id = item.items?.first()?.url?.getID() ?: ""
        val image = "https://img.youtube.com/vi/$id/mqdefault.jpg"

        Glide.with(itemView.context)
            .load(image)
            .placeholder(R.color.bg)
            .into(itemView.im)

    }
}