package test.gojek.gojektest.data

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkHandler {

    val BASE_URL = "http://api.apixu.com/v1"

    private var retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    fun getApiService(): WeatherApiService {
        return retrofit.create(WeatherApiService::class.java)
    }


}