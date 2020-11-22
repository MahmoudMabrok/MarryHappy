package edu.mahmoud_mabrouk.happy_marry.viewholders

import android.view.View
import edu.mahmoud_mabrouk.happy_marry.dataLayer.models.NonVideo
import kotlinx.android.synthetic.main.item_non_video.view.*
import me.ibrahimyilmaz.kiel.core.RecyclerViewHolder

class NonVideoVH(view: View) : RecyclerViewHolder<NonVideo>(view) {
    var data: NonVideo? = null

    fun bind(item: NonVideo) {
        data = item
        itemView.tvTitle?.text = item.name
    }
}