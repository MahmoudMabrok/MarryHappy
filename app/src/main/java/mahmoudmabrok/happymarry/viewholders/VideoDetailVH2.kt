package mahmoudmabrok.happymarry.viewholders

import android.view.View
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.rv_detail_video_item.view.*
import mahmoudmabrok.happymarry.R
import mahmoudmabrok.happymarry.dataLayer.models.Video
import mahmoudmabrok.happymarry.util.Logger
import me.ibrahimyilmaz.kiel.core.RecyclerViewHolder

class VideoDetailVH2(view: View) : RecyclerViewHolder<Video>(view) {
    var data: Video? = null

    fun bind(item: Video) {
        data = item
        itemView.tvTitle?.text = item.name

        val bgID = if (true == item.isSelected) R.color.bgDetail else R.color.white
        itemView.setBackgroundColor(ContextCompat.getColor(itemView.context, bgID))

        Logger.log("VideoDetailVH2 bind: $item")

        // check to show/hide extra info fields
        val othersVisibility = if (item.lengthTime.isNullOrEmpty()) View.GONE else View.VISIBLE
        itemView.tvDuration.visibility = othersVisibility
        itemView.tvViews.visibility = othersVisibility
        updateExtra()

    }

    fun updateExtra() {
        // add data
        itemView.tvDuration.text = data?.lengthTime?.padStart(15, ' ')
        itemView.tvViews.text = data?.views
    }
}