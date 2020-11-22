package edu.mahmoud_mabrouk.happy_marry.dataLayer

import edu.mahmoud_mabrouk.happy_marry.dataLayer.models.NonVideosResponse
import edu.mahmoud_mabrouk.happy_marry.dataLayer.models.VideosResponse
import io.reactivex.Single
import retrofit2.http.GET

interface AppService {

    @GET("videos.json")
    fun loadVideos(): Single<VideosResponse>

    @GET("articles.json")
    fun loadNonVideos(): Single<NonVideosResponse>


}