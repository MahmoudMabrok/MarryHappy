package mahmoudmabrok.happymarry.views.videos


import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import mahmoudmabrok.happymarry.R
import mahmoudmabrok.happymarry.base.BaseFragment
import mahmoudmabrok.happymarry.dataLayer.AppRepo
import mahmoudmabrok.happymarry.util.Logger


class VideosListFragment : BaseFragment(R.layout.fragment_movies_list) {

    val bag = CompositeDisposable()
    val repo by lazy { AppRepo() }
    
    override fun initViews() {
        repo.loadVideos()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Logger.log("data ${it.videos?.size}")
            }, {
                Logger.log("Error ${it.message}")
            })
            .also {
                bag.add(it)
            }
    }
}