package com.cengenes.kotlin.api.service.imp

import com.cengenes.kotlin.api.entity.Hotel
import com.cengenes.kotlin.api.repository.HotelRepository
import com.cengenes.kotlin.api.service.HotelReadService
import org.springframework.stereotype.Service

@Service
class HotelReadServiceImp(var hotelRepository: HotelRepository) : HotelReadService {

    override fun findAll(): MutableList<Hotel> {
        return hotelRepository.findAll()
    }

    override fun findByName(hotelName: String): Hotel {
        return hotelRepository.findByName(hotelName).orElseThrow { throw Exception("Hotel Not Found!") }
    }
}