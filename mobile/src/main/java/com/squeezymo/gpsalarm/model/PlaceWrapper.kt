package com.squeezymo.gpsalarm.model

import com.google.android.gms.location.places.Place
import com.orm.SugarRecord
import java.io.Serializable

/**
 * Wrapper intended to persist `com.google.android.gms.location.places.Place` data with Sugar ORM
 */
class PlaceWrapper(): SugarRecord(), Serializable {

    var name: String? = null
    var address: String? = null
    var latitude = 0.0
    var longitude = 0.0

    constructor(place: Place): this() {
        name = place.name.toString()
        address = place.address.toString()
        latitude = place.latLng?.latitude ?: 0.0
        longitude = place.latLng?.longitude ?: 0.0
    }

}
