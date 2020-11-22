package mahmoudmabrok.happy_marry.views.non_video


import android.view.View
import edu.mahmoud_mabrouk.happy_marry.R
import edu.mahmoud_mabrouk.happy_marry.base.BaseFragment
import edu.mahmoud_mabrouk.happy_marry.dataLayer.AppRepo
import edu.mahmoud_mabrouk.happy_marry.dataLayer.models.NonVideo
import edu.mahmoud_mabrouk.happy_marry.dataLayer.models.NonVideosResponse
import edu.mahmoud_mabrouk.happy_marry.util.IntentHelper
import edu.mahmoud_mabrouk.happy_marry.util.Logger
import edu.mahmoud_mabrouk.happy_marry.viewholders.NonVideoVH
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_articles_list.*
import me.ibrahimyilmaz.kiel.adapterOf

class NonVideoListFragment : BaseFragment(R.layout.fragment_articles_list) {

    private val adapter = adapterOf<NonVideo> {
        register(
            layoutResource = R.layout.item_non_video,
            viewHolder = ::NonVideoVH,
            onViewHolderCreated = { vh ->
                vh.itemView.setOnClickListener {
                    // model.nonVideoItem = vh.data
                    IntentHelper.openUrl(requireContext(), vh.data?.url)
                }
            },
            onBindViewHolder = { vh: NonVideoVH, _: Int, p: NonVideo ->
                vh.bind(p)
            }
        )
    }

    private val repo by lazy { AppRepo() }

    override fun initViews() {
        rvItems?.adapter = adapter
        swipeLayout.setOnRefreshListener {
            if (swipeLayout.isRefreshing)
                loadData()
        }

    }

    override fun loadData() {
        super.loadData()
        spLoading.visibility = View.VISIBLE
        repo.loadNonVideos()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Logger.log("data ${it.size}")
                handleData(it)
                spLoading.visibility = View.GONE
                swipeLayout.isRefreshing = false
            }, {
                Logger.log("NOn Video Error ${it.message}")
                handleError(it.message)
            })
            .also {
                bag.add(it)
            }
    }

    override fun handleError(message: String?) {
        super.handleError(message)
        spLoading?.visibility = View.GONE
        swipeLayout.isRefreshing = false
    }

    private fun handleData(it: NonVideosResponse?) {
        it?.let {
            adapter.submitList(it)
            rvItems?.scheduleLayoutAnimation()
        }
    }
}