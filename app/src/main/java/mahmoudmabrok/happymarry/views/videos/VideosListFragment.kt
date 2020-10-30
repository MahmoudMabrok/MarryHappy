package mahmoudmabrok.happymarry.views.videos


import android.view.View
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_movies_list.*
import mahmoudmabrok.happymarry.R
import mahmoudmabrok.happymarry.base.BaseFragment
import mahmoudmabrok.happymarry.dataLayer.AppRepo
import mahmoudmabrok.happymarry.dataLayer.models.Video
import mahmoudmabrok.happymarry.dataLayer.models.VideoListItem
import mahmoudmabrok.happymarry.util.Logger
import mahmoudmabrok.happymarry.viewholders.VideoVH
import mahmoudmabrok.happymarry.views.videoDetail.VideoDetailFragment
import me.ibrahimyilmaz.kiel.adapterOf
import me.ibrahimyilmaz.kiel.core.RecyclerViewHolder


class VideosListFragment : BaseFragment(R.layout.fragment_movies_list) {

    private var recyclerViewAdapter: androidx.recyclerview.widget.ListAdapter<VideoListItem, RecyclerViewHolder<VideoListItem>>? =
        null
    private val repo by lazy { AppRepo() }
    val bag = CompositeDisposable()

    override fun initViews() {
        recyclerViewAdapter = adapterOf {
            register(
                layoutResource = R.layout.item_video,
                viewHolder = ::VideoVH,
                onViewHolderCreated = { vh ->
                    Logger.log("v1 ${vh.data}")
                    vh.itemView.setOnClickListener {
                        show(VideoDetailFragment())
                    }
                },
                onBindViewHolder = { vh, _, it ->
                    vh.bind(it)
                }
            )

        }

        rvItems?.adapter = recyclerViewAdapter
    }

    override fun loadData() {
        spVideos.visibility = View.VISIBLE
        repo.loadVideos()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Logger.log("data ${it.size}")
                handleData(it)
            }, {
                Logger.log("Error ${it.message}")
                spVideos.visibility = View.GONE
                Toast.makeText(requireContext(), "حدث خطأ", Toast.LENGTH_SHORT).show()
            })
            .also {
                bag.add(it)
            }
    }

    private fun handleData(videos: List<Video>?) {
        spVideos?.visibility = View.GONE
        videos?.let {
            recyclerViewAdapter?.submitList(mapMoves(videos))
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