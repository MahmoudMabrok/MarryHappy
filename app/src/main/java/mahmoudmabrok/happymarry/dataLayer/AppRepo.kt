package mahmoudmabrok.happymarry.dataLayer

import io.reactivex.Single
import mahmoudmabrok.happymarry.dataLayer.models.NonVideosResponse
import mahmoudmabrok.happymarry.dataLayer.models.VideosResponse

class AppRepo : AppService {

    private val api by lazy { appService }

    override fun loadVideos(): Single<VideosResponse> {
        return api.loadVideos()
    }

    override fun loadNonVideos(): Single<NonVideosResponse> {
        return api.loadNonVideos()
    }

}