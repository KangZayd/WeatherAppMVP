package test.gojek.gojektest.location

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import android.location.Address
import android.location.Geocoder
import android.location.Location
import com.google.android.gms.location.LocationServices
import test.gojek.gojektest.App
import test.gojek.gojektest.R
import test.gojek.gojektest.appContext
import test.gojek.gojektest.ui.base.Response
import test.gojek.gojektest.util.getString
import java.io.IOException
import java.util.*
import javax.inject.Inject

class FetchCurrentCity @Inject constructor() : LiveData<Response>() {

    lateinit var location: Location

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
        LocationServices.getFusedLocationProviderClient(appContext).lastLocation.addOnSuccessListener {
            it?.let {
                location = it
                fetchCityName()
            }
        }
    }

}