package test.demo.weatherapp.location

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import android.content.IntentSender
import android.location.Address
import android.location.Geocoder
import android.location.Location
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.tasks.Task
import test.demo.weatherapp.App
import test.demo.weatherapp.R
import test.demo.weatherapp.appContext
import test.demo.weatherapp.ui.base.Response
import test.demo.weatherapp.util.getString
import java.io.IOException
import java.util.*
import javax.inject.Inject


/**
 *  Fetch the user city name using geocoder from the current location
 */
class FetchCurrentCity  @Inject constructor() : LiveData<Response>() {

    lateinit var location:Location

    fun fetchCityName() {
        val geocoder = Geocoder(appContext, Locale.getDefault())
        var addresses: List<Address> = emptyList()

        try {
            addresses = geocoder.getFromLocation(
                    location.latitude,
                    location.longitude,
                    1)
            if (addresses.size > 0) {
                value = Response.SuccessResponse(addresses.get(0).locality)
            } else {
                value = Response.ErrorResponse(getString(R.string.city_info_not_available))
            }
        } catch (ioException: IOException) {
            value = Response.ErrorResponse(getString(R.string.service_not_available))
        } catch (illegalArgumentException: IllegalArgumentException) {
            value = Response.ErrorResponse(getString(R.string.invalid_lat_long_used))
        }
    }

    @SuppressLint("MissingPermission")
    override fun onActive() {
        super.onActive()
        fetchCityName()
    }






}