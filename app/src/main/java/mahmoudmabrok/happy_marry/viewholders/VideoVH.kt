package mahmoudmabrok.happy_marry.viewholders

import android.view.View
import com.bumptech.glide.Glide
import edu.mahmoud_mabrouk.happy_marry.R
import edu.mahmoud_mabrouk.happy_marry.dataLayer.models.VideoListItem
import edu.mahmoud_mabrouk.happy_marry.util.getID
import kotlinx.android.synthetic.main.item_video_new.view.*
import me.ibrahimyilmaz.kiel.core.RecyclerViewHolder

class VideoVH(view: View) : RecyclerViewHolder<VideoListItem>(view) {
    var data: VideoListItem? = null

    fun bind(item: VideoListItem) {
        data = item
        itemView.tvTitle?.text = item.title
        itemView.tvCount?.text = item.items?.size.toString()

        //  itemView.tvCount?.text = item.items?.size.toString()
        val id = item.items?.first()?.url?.getID() ?: ""


        val image = String.format("https://img.youtube.com/vi/%s/mqdefault.jpg", id)

        Glide.with(itemView.context)
            .load(image)
            .placeholder(R.color.bg)
            .into(itemView.im)

    }
}