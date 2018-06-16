package com.cengenes.kotlin.api.service

import com.cengenes.kotlin.api.entity.Hotel

interface HotelReadService {

    fun findAll(): MutableList<Hotel>

    fun findByName(): Hotel

}