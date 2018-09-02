package test.gojek.gojektest.data

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.jvm.java


object NetworkHandler {

    val BASE_URL = "http://api.apixu.com/v1/"

    lateinit private var retrofit : Retrofit

    fun init(){
       retrofit = Retrofit.Builder()
                .baseUrl(NetworkHandler.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(Gson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }


    fun getApiService(): WeatherApiService {
        return retrofit.create(WeatherApiService::class.java)
    }


}