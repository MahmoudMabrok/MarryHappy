package mahmoudmabrok.happymarry.views.nonVideoDetails

import androidx.fragment.app.activityViewModels
import kotlinx.android.synthetic.main.fragment_non_video_detail.*
import mahmoudmabrok.happymarry.R
import mahmoudmabrok.happymarry.base.BaseFragment
import mahmoudmabrok.happymarry.viewmodels.VideoListViewmodel


class NonVideoDetailFragment : BaseFragment(R.layout.fragment_non_video_detail) {

    private val model by activityViewModels<VideoListViewmodel>()

    override fun initViews() {
        webView.loadUrl(model.nonVideoItem?.url ?: "www.google.com")
    }
}