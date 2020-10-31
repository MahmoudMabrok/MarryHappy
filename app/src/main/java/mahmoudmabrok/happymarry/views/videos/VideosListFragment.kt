package mahmoudmabrok.happymarry.views.videos


import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_movies_list.*
import mahmoudmabrok.happymarry.R
import mahmoudmabrok.happymarry.base.BaseFragment
import mahmoudmabrok.happymarry.dataLayer.AppRepo
import mahmoudmabrok.happymarry.dataLayer.models.Video
import mahmoudmabrok.happymarry.dataLayer.models.VideoListItem
import mahmoudmabrok.happymarry.util.Logger
import mahmoudmabrok.happymarry.viewholders.VideoVH
import mahmoudmabrok.happymarry.viewmodels.VideoListViewmodel
import mahmoudmabrok.happymarry.views.videoDetail.VideoDetailFragment
import me.ibrahimyilmaz.kiel.adapterOf


class VideosListFragment : BaseFragment(R.layout.fragment_movies_list) {

    private val recyclerViewAdapter by lazy {
        adapterOf<VideoListItem> {
            register(
                layoutResource = R.layout.item_video,
                viewHolder = ::VideoVH,
                onViewHolderCreated = { vh ->
                    Logger.log("v1 ${vh.data}")
                    vh.itemView.setOnClickListener {
                        model.lsitintem = vh.data
                        show(VideoDetailFragment())
                    }
                },
                onBindViewHolder = { vh, _, it ->
                    vh.bind(it)
                }
            )

        }
    }
    private val repo by lazy { AppRepo() }

    val model by activityViewModels<VideoListViewmodel>()

    override fun initViews() {
        rvItems?.adapter = recyclerViewAdapter

        swip.setOnRefreshListener {
            if (swip.isRefreshing)
                loadData()
        }
    }

    override fun loadData() {
        spVideos.visibility = View.VISIBLE
        repo.loadVideos()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                swip.isRefreshing = false
                handleData(it)
            }, {
                handleError(it.message)
            })
            .also {
                bag.add(it)
            }
    }

    private fun handleError(message: String?) {
        Logger.log("Error $message")
        spVideos.visibility = View.GONE
        if (message?.contains("resolve") == true) {
            Toast.makeText(requireContext(), "تاكد من اتصالك بالانترنت", Toast.LENGTH_SHORT).show()
        } else
            Toast.makeText(requireContext(), "حدث خطأ", Toast.LENGTH_SHORT).show()
        swip.isRefreshing = false
    }

    private fun handleData(videos: List<Video>?) {
        spVideos?.visibility = View.GONE
        videos?.let {
            recyclerViewAdapter.submitList(mapMoves(videos))
        }
    }

    private fun mapMoves(videos: List<Video>): List<VideoListItem>? {
        val data = mutableListOf<VideoListItem>()
        videos.groupBy { it.group }.forEach { (groupName, list) ->
            if ("item" == groupName)
                data.addAll(list.map {
                    VideoListItem(
                        title = it.name,
                        url = it.url,
                        items = listOf(it)
                    )
                })
            else
                data.add(VideoListItem(isPlaylist = true, title = groupName, items = list))
        }

        return data
    }
}