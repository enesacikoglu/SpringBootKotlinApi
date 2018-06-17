package com.cengenes.kotlin.api.model.request

import java.io.Serializable
import javax.validation.constraints.NotNull

data class CheckInRequest(@NotNull var hotelName: String, @NotNull var numberOfGuest: Long) : Serializable {

    override fun toString(): String {
        return "CheckInRequest(hotelName='$hotelName', numberOfGuest=$numberOfGuest)"
    }
}