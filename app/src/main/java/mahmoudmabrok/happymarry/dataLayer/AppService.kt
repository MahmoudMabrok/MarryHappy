package mahmoudmabrok.happymarry.dataLayer

import io.reactivex.Single
import mahmoudmabrok.happymarry.dataLayer.models.NonVideosResponse
import mahmoudmabrok.happymarry.dataLayer.models.VideosResponse
import retrofit2.http.GET

interface AppService {

    @GET("videos.json")
    fun loadVideos(): Single<VideosResponse>

    @GET("articles.json")
    fun loadNonVideos(): Single<NonVideosResponse>


}