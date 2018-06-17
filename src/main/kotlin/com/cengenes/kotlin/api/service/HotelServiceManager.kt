package com.cengenes.kotlin.api.service

import com.cengenes.kotlin.api.entity.Hotel
import com.cengenes.kotlin.api.model.request.CheckInRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class HotelServiceManager {

    @Autowired
    private lateinit var hotelReadService: HotelReadService

    @Autowired
    private lateinit var hotelSaveService: HotelSaveService

    fun checkIn(checkInRequest: CheckInRequest): Boolean {
        val hotel = hotelReadService.findByName(checkInRequest.hotelName)
        return when {
            hotel.freeRoomCount >= checkInRequest.numberOfGuest -> {
                hotel.freeRoomCount -= checkInRequest.numberOfGuest
                hotelSaveService.save(hotel)
                true
            }
            else -> false
        }
    }

    fun findAll(): MutableList<Hotel> {
        return hotelReadService.findAll()
    }

    fun findByName(hotelName: String): Hotel {
        return hotelReadService.findByName(hotelName);
    }

}