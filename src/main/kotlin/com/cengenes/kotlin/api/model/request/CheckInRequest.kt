package com.cengenes.kotlin.api.model.request

import java.io.Serializable

data class CheckInRequest(var hotelName: String, var numberOfGuest: Long) : Serializable {

    override fun toString(): String {
        return "CheckInRequest(hotelName='$hotelName', numberOfGuest=$numberOfGuest)"
    }
}