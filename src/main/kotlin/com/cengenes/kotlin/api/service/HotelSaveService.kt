package com.cengenes.kotlin.api.service

import com.cengenes.kotlin.api.entity.Hotel

interface HotelSaveService {

    fun save(hotel: Hotel): Hotel
}