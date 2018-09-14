package test.demo.weatherapp.data.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CurrentWeatherResponse (
    var location: Location,
    var current: Current
): Parcelable

@Parcelize
data class Current(
    val last_updated_epoch: Int,
    val last_updated: String,
    var temp_c: Double,
    val temp_f: Double,
    val is_day: Int,
    val condition: Condition,
    val wind_mph: Double,
    val wind_kph: Double,
    val wind_degree: Int,
    val wind_dir: String,
    val pressure_mb: Double,
    val pressure_in: Double,
    val precip_mm: Double,
    val precip_in: Double,
    val humidity: Int,
    val cloud: Int,
    val feelslike_c: Double,
    val feelslike_f: Double,
    val vis_km: Double,
    val vis_miles: Double
): Parcelable

@Parcelize
data class Condition(
    val text: String,
    val icon: String,
    val code: Int
): Parcelable

@Parcelize
data class Location(
    var name: String,
    val region: String,
    val country: String,
    val lat: Double,
    val lon: Double,
    val tz_id: String,
    val localtime_epoch: Int,
    val localtime: String
): Parcelable