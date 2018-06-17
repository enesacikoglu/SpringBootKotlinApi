package com.cengenes.kotlin.api.service.imp

import com.cengenes.kotlin.api.entity.Hotel
import com.cengenes.kotlin.api.repository.HotelRepository
import com.cengenes.kotlin.api.service.HotelSaveService
import org.springframework.stereotype.Service

@Service
class HotelSaveServiceImp(var hotelRepository: HotelRepository) : HotelSaveService {

    override fun save(hotel: Hotel): Hotel {
        return hotelRepository.save(hotel)
    }
}