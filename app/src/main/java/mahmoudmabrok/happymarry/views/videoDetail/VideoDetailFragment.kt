package mahmoudmabrok.happymarry.views.videoDetail

import androidx.fragment.app.activityViewModels
import com.google.android.material.transition.MaterialFadeThrough
import com.google.android.material.transition.MaterialSharedAxis
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import kotlinx.android.synthetic.main.fragment_video_detail.*
import kotlinx.android.synthetic.main.rv_detail_video_item.view.*
import mahmoudmabrok.happymarry.R
import mahmoudmabrok.happymarry.base.BaseFragment
import mahmoudmabrok.happymarry.dataLayer.models.Video
import mahmoudmabrok.happymarry.util.Logger
import mahmoudmabrok.happymarry.util.getID
import mahmoudmabrok.happymarry.viewholders.VideoDetailVH2
import mahmoudmabrok.happymarry.viewmodels.VideoListViewModel
import me.ibrahimyilmaz.kiel.adapterOf


class VideoDetailFragment : BaseFragment(R.layout.fragment_video_detail) {

    private val model by activityViewModels<VideoListViewModel>()

    private val adapter = adapterOf<Video> {
        register(
            layoutResource = R.layout.rv_detail_video_item,
            viewHolder = ::VideoDetailVH2,
            onViewHolderCreated = { vh ->
                run {
                    viewLifecycleOwner.lifecycle.addObserver(vh.itemView.youtube_player_view)
                    vh.itemView.youtube_player_view.addYouTubePlayerListener(object :
                        AbstractYouTubePlayerListener() {
                        override fun onReady(youTubePlayer: YouTubePlayer) {
                            vh.player = youTubePlayer
                            val newUrl = vh.data?.url?.getID() ?: ""
                            vh.player?.cueVideo(newUrl, 0f)
                        }

                        override fun onError(
                            youTubePlayer: YouTubePlayer,
                            error: PlayerConstants.PlayerError
                        ) {
                            super.onError(youTubePlayer, error)
                            Logger.log("VideoDetailFragment onError: ${error.name}  $error")
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
        adapter.submitList(model.listItem?.items)
        enterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, true)
        exitTransition = MaterialFadeThrough()
    }
}