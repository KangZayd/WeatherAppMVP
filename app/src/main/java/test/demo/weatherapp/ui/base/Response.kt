package test.demo.weatherapp.ui.base

import android.location.Location
import com.google.android.gms.location.LocationResult

sealed class Response {
    data class OnLoading(var showLoading : Boolean) : Response()
    data class SuccessResponse(var s : Any):Response()
    data class ErrorResponse(var s : String?) : Response()
    data class LocationResponse(var locationResult : Location) : Response()
}

sealed class LocationResponse{

    data class LocationSettings(var any : Any) : LocationResponse()
    data class CityName(var string : String) : LocationResponse()
    data class FetchLocation(var location : Location) : LocationResponse()
}
