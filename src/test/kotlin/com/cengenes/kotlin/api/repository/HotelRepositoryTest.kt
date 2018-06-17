package com.cengenes.kotlin.api.repository

import com.cengenes.kotlin.api.entity.Hotel
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@DataJpaTest
class HotelRepositoryTest {

    @Autowired
    private lateinit var testEntityManager: TestEntityManager

    @Autowired
    private lateinit var hotelRepository: HotelRepository

    @Test
    fun it_should_find_hotel_by_name() {
        //Given
        val hotel = Hotel("Sultanahmet Inn Hotel",99L,250L)
        testEntityManager.persistAndFlush(hotel)

        //When
        val optionalHotel = hotelRepository.findByName("Sultanahmet Inn Hotel")

        //Then
        assertThat(optionalHotel).isPresent
        val expectedHotel = optionalHotel.get()
        assertThat(expectedHotel.id).isNotNull()
        assertThat(expectedHotel.name).isEqualTo("Sultanahmet Inn Hotel")
        assertThat(expectedHotel.classification).isEqualTo(99L)
        assertThat(expectedHotel.totalRoomCount).isEqualTo(250L)
        assertThat(expectedHotel.freeRoomCount).isEqualTo(250L)
    }
}