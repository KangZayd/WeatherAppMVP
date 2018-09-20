package test.demo.weatherapp.location

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import android.location.Location
import com.google.android.gms.location.*
import test.demo.weatherapp.appContext
import test.demo.weatherapp.ui.base.LocationResponse
import test.demo.weatherapp.ui.base.Response
import javax.inject.Inject

/**
 *  Use fuse location provider to access the user location. If fuse provider returns null we request a location updates and inform the user for the location settings.
 */
class LocationListener @Inject constructor() : LiveData<LocationResponse>() {

    lateinit var location: Location
    var receivingLocationUpdates = false;
    var locationProvider: FusedLocationProviderClient
    var mLocationRequest = LocationRequest()

    init {
        locationProvider = LocationServices.getFusedLocationProviderClient(appContext)

        mLocationRequest.setInterval(1000)
        mLocationRequest.setFastestInterval(1000)
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
    }

    @SuppressLint("MissingPermission")
    override fun onActive() {
        super.onActive()
        locationProvider.lastLocation.addOnSuccessListener {
            if (it != null) {
                onFetchLocation(it)
            } else {
                /**
                 * check for setting as well
                 */
                value = LocationResponse.LocationSettings("")
                receiveLocationUpdate()
            }
        }
    }

    override fun onInactive() {
        super.onInactive()
        if (receivingLocationUpdates) {
            locationProvider.removeLocationUpdates(locationCallback)
            receivingLocationUpdates = false
        }
    }

    fun onFetchLocation(location: Location) {
        value = LocationResponse.FetchLocation(location)

    }

    @SuppressLint("MissingPermission")
    fun receiveLocationUpdate() {

        locationProvider.requestLocationUpdates(mLocationRequest, locationCallback, null)
        receivingLocationUpdates = true

    }

    var locationCallback = object : LocationCallback() {
        override fun onLocationResult(p0: LocationResult?) {
            super.onLocationResult(p0)
            p0?.let { onFetchLocation(p0.lastLocation) }
        }
    }
}