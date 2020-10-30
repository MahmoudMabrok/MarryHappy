package mahmoudmabrok.happymarry.views.videoDetail

import androidx.fragment.app.activityViewModels
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import kotlinx.android.synthetic.main.fragment_video_detail.*
import kotlinx.android.synthetic.main.rv_detail_video_item.view.*
import mahmoudmabrok.happymarry.R
import mahmoudmabrok.happymarry.base.BaseFragment
import mahmoudmabrok.happymarry.dataLayer.models.Video
import mahmoudmabrok.happymarry.util.Logger
import mahmoudmabrok.happymarry.viewholders.VideoDetailVH2
import mahmoudmabrok.happymarry.viewmodels.VideoListViewmodel
import me.ibrahimyilmaz.kiel.adapterOf


class VideoDetailFragment : BaseFragment(R.layout.fragment_video_detail) {

    val model by activityViewModels<VideoListViewmodel>()

    val adapter = adapterOf<Video> {
        register(
            layoutResource = R.layout.rv_detail_video_item,
            viewHolder = ::VideoDetailVH2,
            onViewHolderCreated = { vh ->
                run {
                    viewLifecycleOwner.lifecycle.addObserver(vh.itemView.youtube_player_view)
                    vh.itemView.youtube_player_view.addYouTubePlayerListener(object :
                        AbstractYouTubePlayerListener() {
                        override fun onReady(initializedYouTubePlayer: YouTubePlayer) {
                            vh.player = initializedYouTubePlayer
                            val newUrl = vh.data?.url?.split("=")?.lastOrNull() ?: ""
                            vh.player?.cueVideo(newUrl, 0f)
                        }
                    })
                }
            },
            onBindViewHolder = { vh: VideoDetailVH2, pos: Int, p: Video ->
                vh.bind(p)
            }
        )
    }

    override fun initViews() {
        rvItems?.adapter = adapter

        android.os.Handler().postDelayed({
            Logger.log("VideoDetailFragment initViews: ")
            adapter.submitList(model.lsitintem?.items)
        }, 200)

    }


}