package mahmoudmabrok.happymarry.dataLayer

import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val retrofit: Retrofit by lazy {
    Retrofit.Builder()
        .baseUrl("https://marriyapp.firebaseio.com/")
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
}

val appService: AppService by lazy {
    retrofit.create(AppService::class.java)
}
