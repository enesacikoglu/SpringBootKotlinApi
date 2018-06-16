package com.cengenes.kotlin.api.repository

import com.cengenes.kotlin.api.entity.Hotel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface HotelRepository : JpaRepository<Hotel, Long> {
    fun findByName(name: String): Optional<Hotel>
}