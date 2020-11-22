package mahmoudmabrok.happy_marry.views.videoDetail

import android.annotation.SuppressLint
import android.util.SparseArray
import androidx.core.util.isNotEmpty
import androidx.fragment.app.activityViewModels
import at.huber.youtubeExtractor.VideoMeta
import at.huber.youtubeExtractor.YouTubeExtractor
import at.huber.youtubeExtractor.YtFile
import com.google.android.material.transition.MaterialFadeThrough
import com.google.android.material.transition.MaterialSharedAxis
import edu.mahmoud_mabrouk.happy_marry.R
import edu.mahmoud_mabrouk.happy_marry.base.BaseFragment
import edu.mahmoud_mabrouk.happy_marry.dataLayer.models.Video
import edu.mahmoud_mabrouk.happy_marry.util.Logger
import edu.mahmoud_mabrouk.happy_marry.util.VideoController
import edu.mahmoud_mabrouk.happy_marry.viewholders.VideoDetailVH2
import edu.mahmoud_mabrouk.happy_marry.viewmodels.VideoListViewModel
import kotlinx.android.synthetic.main.fragment_video_detail.*
import me.ibrahimyilmaz.kiel.adapterOf


class VideoDetailFragment : BaseFragment(R.layout.fragment_video_detail) {

    private val videoController by lazy { VideoController(pvVideoDetail) }

    private val model by activityViewModels<VideoListViewModel>()

    private var index = 0

    private val adapter = adapterOf<Video> {
        register(
            layoutResource = R.layout.rv_detail_video_item,
            viewHolder = ::VideoDetailVH2,
            onViewHolderCreated = { vh ->
                Logger.log("onViewHolderCreated : ")
                vh.itemView.setOnClickListener {
                    Logger.log("VideoDetailFragment : aa ${vh.absoluteAdapterPosition}")
                    playItem(vh.data, vh.absoluteAdapterPosition)
                }
            },
            onBindViewHolder = { vh: VideoDetailVH2, pos: Int, p: Video ->
                p.isSelected = pos == index

                vh.bind(p)

                Logger.log("VideoDetailFragment onBindViewHolder : pos $pos index $index item $p")
            }
        )
    }

    override fun initViews() {
        rvItems?.adapter = adapter
        adapter.submitList(model.listItem?.items)
        enterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, true)
        exitTransition = MaterialFadeThrough()

        playItem(model.listItem?.items?.firstOrNull(), 0)
    }

    @SuppressLint("StaticFieldLeak")
    private fun playItem(video: Video?, index: Int) {
        title?.text = video?.name
        val items = model.listItem?.items ?: emptyList()
        this.index = index
        Logger.log("VideoDetailFragment playItem: index$index")
        //     adapter.submitList(emptyList())

        adapter.notifyItemRangeChanged(0, items.size)

        object : YouTubeExtractor(requireContext()) {
            override fun onExtractionComplete(
                ytFiles: SparseArray<YtFile>,
                vMeta: VideoMeta
            ) {
                if (ytFiles.isNotEmpty()) {
                    videoController.releasePlayer()
                    val downloadUrl = ytFiles.valueAt(0).url
                    context?.let {
                        videoController.initializePlayer(requireContext(), downloadUrl)
                    }

                }
            }
        }.extract(video?.url, true, true)
    }

    override fun onDestroyView() {
        videoController.releasePlayer()
        super.onDestroyView()
    }
}