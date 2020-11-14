package mahmoudmabrok.happymarry.views.videos


import android.view.View
import androidx.fragment.app.activityViewModels
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_movies_list.*
import mahmoudmabrok.happymarry.R
import mahmoudmabrok.happymarry.base.BaseFragment
import mahmoudmabrok.happymarry.dataLayer.AppRepo
import mahmoudmabrok.happymarry.dataLayer.models.Video
import mahmoudmabrok.happymarry.dataLayer.models.VideoListItem
import mahmoudmabrok.happymarry.viewholders.VideoVH
import mahmoudmabrok.happymarry.viewmodels.VideoListViewModel
import mahmoudmabrok.happymarry.views.videoDetail.VideoDetailFragment
import me.ibrahimyilmaz.kiel.adapterOf


class VideosListFragment : BaseFragment(R.layout.fragment_movies_list) {

    private val recyclerViewAdapter by lazy {
        adapterOf<VideoListItem> {
            register(
                layoutResource = R.layout.item_video_new,
                viewHolder = ::VideoVH,
                onViewHolderCreated = { vh ->
                    vh.itemView.setOnClickListener {
                        model.listItem = vh.data
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

    private val model by activityViewModels<VideoListViewModel>()

    override fun initViews() {
        rvItems?.adapter = recyclerViewAdapter

        swipeLayout.setOnRefreshListener {
            if (swipeLayout.isRefreshing)
                loadData()
        }

    }

    override fun loadData() {
        recyclerViewAdapter.submitList(emptyList())
        spVideos.visibility = View.VISIBLE
        swipeLayout.isRefreshing = false
        repo.loadVideos()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                handleData(it)
            }, {
                handleError(it.message)
            })
            .also {
                bag.add(it)
            }
    }

    override fun handleError(message: String?) {
        super.handleError(message)
        spVideos.visibility = View.GONE
        swipeLayout.isRefreshing = false
    }


    private fun handleData(videos: List<Video>?) {
        spVideos?.visibility = View.GONE
        videos?.let {
            recyclerViewAdapter.submitList(mapMoves(videos))
            rvItems.scheduleLayoutAnimation()
        }

        rvItems.scheduleLayoutAnimation()
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