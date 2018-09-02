package test.gojek.gojektest.data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class ForecastWeatherResponse(
        val location: Location,
        val current: Current,
        val forecast: Forecast
) : Parcelable

@Parcelize
data class Forecast(
        @SerializedName("forecastday")
        val forecastday: List<Forecastday>
) : Parcelable

@Parcelize
data class Forecastday(
        val date: String,
        val date_epoch: Long,
        val day: Day,
        val astro: Astro
) : Parcelable

@Parcelize
data class Day(
        val maxtemp_c: Double,
        val maxtemp_f: Double,
        val mintemp_c: Double,
        val mintemp_f: Double,
        val avgtemp_c: Double,
        val avgtemp_f: Double,
        val maxwind_mph: Double,
        val maxwind_kph: Double,
        val totalprecip_mm: Double,
        val totalprecip_in: Double,
        val avgvis_km: Double,
        val avgvis_miles: Double,
        val avghumidity: Double,
        val condition: Condition,
        val uv: Double
) : Parcelable

@Parcelize
data class Astro(
        val sunrise: String,
        val sunset: String,
        val moonrise: String,
        val moonset: String
) : Parcelable