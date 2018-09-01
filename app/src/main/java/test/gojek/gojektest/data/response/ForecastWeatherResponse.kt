package test.gojek.gojektest.data.response


data class ForecastWeatherResponse(
    val location: Location,
    val current: Current,
    val forecast: Forecast
)


data class Forecast(
    val forecastday: List<Forecastday>
)

data class Forecastday(
    val date: String,
    val date_epoch: Int,
    val day: Day,
    val astro: Astro
)

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
)

data class Astro(
    val sunrise: String,
    val sunset: String,
    val moonrise: String,
    val moonset: String
)