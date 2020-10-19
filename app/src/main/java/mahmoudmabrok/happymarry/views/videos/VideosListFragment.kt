package mahmoudmabrok.happymarry.views.videos


import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.ListAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_movies_list.*
import mahmoudmabrok.happymarry.R
import mahmoudmabrok.happymarry.base.BaseFragment
import mahmoudmabrok.happymarry.dataLayer.AppRepo
import mahmoudmabrok.happymarry.dataLayer.models.Video
import mahmoudmabrok.happymarry.util.Logger
import mahmoudmabrok.happymarry.viewholders.VideoVH
import me.ibrahimyilmaz.kiel.adapterOf
import me.ibrahimyilmaz.kiel.core.RecyclerViewHolder


class VideosListFragment : BaseFragment(R.layout.fragment_movies_list) {

    private var recyclerViewAdapter: ListAdapter<Video, RecyclerViewHolder<Video>>? = null
    val bag = CompositeDisposable()
    val repo by lazy { AppRepo() }

    override fun initViews() {
        recyclerViewAdapter = adapterOf<Video> {
            register(
                layoutResource = R.layout.item_video,
                viewHolder = ::VideoVH,
                onViewHolderCreated = { vh ->
                    //you may handle your on click listener
                    vh.itemView.setOnClickListener {
                        Toast.makeText(requireContext(), "aa ${vh.data?.name}.", Toast.LENGTH_LONG)
                            .show()
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
        repo.loadVideos()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Logger.log("data ${it.videos?.size}")
                handleData(it.videos)
            }, {
                Logger.log("Error ${it.message}")
            })
            .also {
                bag.add(it)
            }
    }

    private fun handleData(videos: List<Video>?) {
        spVideos?.visibility = View.GONE
        videos?.let {
            recyclerViewAdapter?.submitList(videos)
        }
    }
}