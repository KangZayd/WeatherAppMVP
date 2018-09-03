package test.gojek.gojektest.data

import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object NetworkHandler {

    val BASE_URL = "http://api.apixu.com/"
    val KEY = your api key
    val CITY = "mumbai"
    val DAYS = "5"

    lateinit private var retrofit: Retrofit

    fun init() {

        val okHttpClient = OkHttpClient().newBuilder().connectTimeout(60, TimeUnit.SECONDS).readTimeout(60, TimeUnit.SECONDS).build()

        retrofit = Retrofit.Builder()
                .baseUrl(NetworkHandler.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient).build()
    }


    fun getApiService(): WeatherApiService {
        return retrofit.create(WeatherApiService::class.java)
    }


}