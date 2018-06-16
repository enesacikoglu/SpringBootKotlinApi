package com.cengenes.kotlin.api.service

import com.cengenes.kotlin.api.entity.Hotel
import com.cengenes.kotlin.api.model.request.CheckInRequest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HotelServiceManagerTest {

    @InjectMocks
    private lateinit var hotelServiceManager: HotelServiceManager

    @Mock
    private lateinit var hotelReadService: HotelReadService


    @Test
    fun it_should_checkin_when_free_room_count_available() {
        //Given
        val checkInRequest = CheckInRequest("Hilton Hotel", 5000L)

        val hotel = Hotel("Hilton Hotel", 104L, 10000L)

        `when`(hotelReadService.findByName("Hilton Hotel")).thenReturn(hotel)

        //When
        val isCheckedIn = hotelServiceManager.checkIn(checkInRequest)

        //Then
        verify(hotelReadService).findByName("Hilton Hotel")
        assertThat(isCheckedIn).isTrue()
    }

    @Test
    fun it_should_not_checkin_when_free_room_count_is_not_available() {
        //Given
        val checkInRequest = CheckInRequest("Hilton Hotel", 5000L)

        val hotel = Hotel("Hilton Hotel", 104L, 4999L)

        `when`(hotelReadService.findByName("Hilton Hotel")).thenReturn(hotel)

        //When
        val isCheckedIn = hotelServiceManager.checkIn(checkInRequest)

        //Then
        verify(hotelReadService).findByName("Hilton Hotel")
        assertThat(isCheckedIn).isFalse()
    }
}