package com.cengenes.kotlin.api.entity

import javax.persistence.*

@Entity
@Table(name = "hotel")
@SequenceGenerator(name = "seq_hotel", sequenceName = "seq_hotel")
data class Hotel(
        @Column(name = "hotel_name", nullable = false)
        var name: String,
        @Column(name = "classification", nullable = false)
        var classification: Long,
        @Column(name = "total_room_count", nullable = false)
        var totalRoomCount: Long) {

    constructor() : this("", 0L, 0L)

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_hotel")
    var id: Long = 0

    @Column(name = "free_room_count", nullable = false)
    var freeRoomCount: Long = this.totalRoomCount
}