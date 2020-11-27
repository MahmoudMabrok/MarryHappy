package mahmoudmabrok.happymarry.views.videoDetail

import android.annotation.SuppressLint
import android.util.SparseArray
import androidx.core.util.forEach
import androidx.core.util.isNotEmpty
import androidx.fragment.app.activityViewModels
import at.huber.youtubeExtractor.VideoMeta
import at.huber.youtubeExtractor.YouTubeExtractor
import at.huber.youtubeExtractor.YtFile
import com.google.android.material.transition.MaterialFadeThrough
import com.google.android.material.transition.MaterialSharedAxis
import kotlinx.android.synthetic.main.fragment_video_detail.*
import mahmoudmabrok.happymarry.R
import mahmoudmabrok.happymarry.base.BaseFragment
import mahmoudmabrok.happymarry.dataLayer.models.Video
import mahmoudmabrok.happymarry.util.Logger
import mahmoudmabrok.happymarry.util.ValueHelpers
import mahmoudmabrok.happymarry.util.VideoController
import mahmoudmabrok.happymarry.viewholders.VideoDetailVH2
import mahmoudmabrok.happymarry.viewmodels.VideoListViewModel
import me.ibrahimyilmaz.kiel.adapterOf


class VideoDetailFragment : BaseFragment(R.layout.fragment_video_detail) {

    private val qualityItems: MutableList<VideoDetailFragment.Play> = mutableListOf()
    private val videoController by lazy { VideoController(pvVideoDetail) }

    private val model by activityViewModels<VideoListViewModel>()

    private var index = 0
    private var qualityIndex = 0

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

        // start play first item in list
        playItem(model.listItem?.items?.firstOrNull(), 0)


        quality?.setOnClickListener {
            // go to next qulaity
            qualityIndex += 1
            // modulo (round around values . i.e 0,1,2,0,1 etc)
            qualityIndex %= qualityItems.size
            quality.text = "${qualityItems[qualityIndex].title}"
            videoController.releasePlayer()
            videoController.initializePlayer(requireContext(), qualityItems[qualityIndex].url)

        }
    }

    @SuppressLint("StaticFieldLeak")
    private fun playItem(video: Video?, index: Int) {
        // title?.text = video?.name
        val items = model.listItem?.items ?: emptyList()
        this.index = index
        Logger.log("VideoDetailFragment playItem: index$index")


        adapter.notifyItemRangeChanged(0, items.size)

        object : YouTubeExtractor(requireContext()) {
            override fun onExtractionComplete(
                ytFiles: SparseArray<YtFile>,
                vMeta: VideoMeta
            ) {
                if (ytFiles.isNotEmpty()) {
                    videoController.releasePlayer()
                    val ytSelected = ytFiles[18] ?: ytFiles[133] ?: ytFiles.valueAt(0)
                    val downloadUrl = ytSelected?.url
                    downloadUrl?.let {
                        context?.let {
                            quality.text = "${ytSelected.format.height} ${ytSelected.format.ext}"
                            if (isResumed)
                                videoController.initializePlayer(it, downloadUrl)
                        }
                    }
                    // convert them[extracted data ] into models to used in UI as user change quality
                    handleYt(ytFiles)
                    // update video with video length and views count
                    updateCurrentItemWithInfo(vMeta.videoLength, vMeta.viewCount)
                }
            }
        }.extract(video?.url, false, true)
    }

    data class Play(val title: String, val url: String)

    private fun handleYt(ytFiles: SparseArray<YtFile>) {
        qualityItems.clear()
        var audiAdded = false
        ytFiles.forEach { key, value ->
            if (value.format.audioBitrate > 0) {
                if (value.format.height == -1) {
                    // accept only first only first audio only item
                    if (!audiAdded) {
                        qualityItems.add(
                            Play(
                                getString(R.string.audio_only),
                                value.url
                            )
                        )
                        audiAdded = true
                    }
                } else {
                    qualityItems.add(Play("${value.format.height} ${value.format.ext}", value.url))
                }
            }
        }
    }

    private fun updateCurrentItemWithInfo(videoLength: Long, viewCount: Long) {
        val length = ValueHelpers.formatTime(videoLength)
        (rvItems.findViewHolderForAdapterPosition(index) as? VideoDetailVH2)?.let {
            it.data?.lengthTime = length
            it.data?.views = "$viewCount"
            adapter.notifyItemChanged(index)
        }
    }

    override fun onDestroyView() {
        model.listItem = null
        videoController.releasePlayer()
        super.onDestroyView()
    }
}