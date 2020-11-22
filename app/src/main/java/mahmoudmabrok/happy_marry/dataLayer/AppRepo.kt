package edu.mahmoud_mabrouk.happy_marry.dataLayer

import edu.mahmoud_mabrouk.happy_marry.dataLayer.models.NonVideosResponse
import edu.mahmoud_mabrouk.happy_marry.dataLayer.models.VideosResponse
import io.reactivex.Single

class AppRepo : AppService {

    private val api by lazy { appService }

    override fun loadVideos(): Single<VideosResponse> {
        return api.loadVideos()
    }

    override fun loadNonVideos(): Single<NonVideosResponse> {
        return api.loadNonVideos()
    }

}