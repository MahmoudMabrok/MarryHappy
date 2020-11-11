package mahmoudmabrok.happymarry.viewholders

import android.view.View
import kotlinx.android.synthetic.main.item_non_video.view.*
import mahmoudmabrok.happymarry.dataLayer.models.NonVideo
import me.ibrahimyilmaz.kiel.core.RecyclerViewHolder

class NonVideoVH(view: View) : RecyclerViewHolder<NonVideo>(view) {
    var data: NonVideo? = null

    fun bind(item: NonVideo) {
        data = item
        itemView.tvTitle?.text = item.name
    }
}