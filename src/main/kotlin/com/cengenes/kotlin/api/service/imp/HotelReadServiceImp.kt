package com.cengenes.kotlin.api.service.imp

import com.cengenes.kotlin.api.entity.Hotel
import com.cengenes.kotlin.api.service.HotelReadService
import org.springframework.stereotype.Service
import java.util.*

@Service
class HotelReadServiceImp : HotelReadService {

    override fun findAll(): MutableList<Hotel> {
        return  Collections.emptyList<Hotel>()
    }

    override fun findByName(): Hotel {
        return Hotel()
    }
}