package com.cengenes.kotlin.api.controller

import com.cengenes.kotlin.api.entity.Hotel
import com.cengenes.kotlin.api.model.request.CheckInRequest
import com.cengenes.kotlin.api.service.HotelServiceManager
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/hotels")
class HotelController(var hotelServiceManager: HotelServiceManager) {

    @GetMapping
    fun getAllHotels(): MutableList<Hotel> = this.hotelServiceManager.findAll()

    @GetMapping("/{name}")
    fun findByHotelName(@PathVariable(value = "name") name: String): Hotel = this.hotelServiceManager.findByName(name)

    @PostMapping("checkIn")
    fun checkIn(@Valid @RequestBody checkInRequest: CheckInRequest): Boolean {
        return hotelServiceManager.checkIn(checkInRequest)
    }
}