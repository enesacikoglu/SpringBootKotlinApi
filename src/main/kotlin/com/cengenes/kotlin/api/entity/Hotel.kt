package com.cengenes.kotlin.api.entity

import javax.persistence.*

@Entity
@Table(name = "hotel")
data class Hotel(
        @Column(name = "hotel_name", nullable = false)
        var name: String,
        @Column(name = "classification", nullable = false)
        var classification: Long,
        @Column(name = "total_room_count", nullable = false)
        var totalRoomCount: Long) {

    constructor() : this("", 0L, 0L)

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0

    @Column(name = "free_room_numbers", nullable = false)
    var freeRoomCount: Long = this.totalRoomCount

    fun checkIn(guestCount: Int) {
        if (this.freeRoomCount >= guestCount) {
            this.freeRoomCount -= guestCount
        }
    }

    fun checkOut(guestCount: Int) {
        this.freeRoomCount += guestCount
    }
}