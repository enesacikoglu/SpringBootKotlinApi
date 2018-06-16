package com.cengenes.kotlin.api.controller

import com.cengenes.kotlin.api.entity.Hotel
import com.cengenes.kotlin.api.model.request.CheckInRequest
import com.cengenes.kotlin.api.service.HotelServiceManager
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/hotels")
class HotelController(var hotelServiceManager: HotelServiceManager) {

    @GetMapping
    fun getAllHotels(): MutableIterable<Hotel> = this.hotelServiceManager.findAll()

    @GetMapping("/{name}")
    fun findByHotelName(@PathVariable(value = "name") name: String): Hotel = this.hotelServiceManager.findByName(name)

    @PostMapping("checkIn")
    fun checkIn(@RequestBody checkInRequest: CheckInRequest): Boolean {
        return hotelServiceManager.checkIn(checkInRequest)
    }
}