package edu.mahmoud_mabrouk.happy_marry.viewholders

import android.view.View
import androidx.core.content.ContextCompat
import edu.mahmoud_mabrouk.happy_marry.R
import edu.mahmoud_mabrouk.happy_marry.dataLayer.models.Video
import edu.mahmoud_mabrouk.happy_marry.util.Logger
import kotlinx.android.synthetic.main.rv_detail_video_item.view.*
import me.ibrahimyilmaz.kiel.core.RecyclerViewHolder

class VideoDetailVH2(view: View) : RecyclerViewHolder<Video>(view) {
    var data: Video? = null

    fun bind(item: Video) {
        data = item
        itemView.tvTitle?.text = item.name

        val bgID = if (true == item.isSelected) R.color.bgDetail else R.color.white
        itemView.setBackgroundColor(ContextCompat.getColor(itemView.context, bgID))

        Logger.log("VideoDetailVH2 bind: $item")

    }
}