package com.cengenes.kotlin.api.service

import com.cengenes.kotlin.api.entity.Hotel
import com.cengenes.kotlin.api.model.request.CheckInRequest
import com.nhaarman.mockito_kotlin.argumentCaptor
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HotelServiceManagerTest {

    @InjectMocks
    private lateinit var hotelServiceManager: HotelServiceManager

    @Mock
    private lateinit var hotelReadService: HotelReadService

    @Mock
    private lateinit var hotelSaveService: HotelSaveService

    @Test
    fun it_should_checkIn_when_free_room_count_available() {
        //Given
        var checkInRequest = CheckInRequest("Hilton Hotel", 5000L)

        var hotel = Hotel("Hilton Hotel", 104L, 10000L)

        `when`(hotelReadService.findByName("Hilton Hotel")).thenReturn(hotel)

        //When
        val isCheckedIn = hotelServiceManager.checkIn(checkInRequest)

        //Then
        verify(hotelReadService).findByName("Hilton Hotel")
        argumentCaptor<Hotel>().apply {
            verify(hotelSaveService).save(capture())

            assertThat(allValues.size).isEqualTo(1)
            assertThat(firstValue.name).isEqualTo("Hilton Hotel")
            assertThat(firstValue.totalRoomCount).isEqualTo(10000L)
            assertThat(firstValue.freeRoomCount).isEqualTo(5000L)

        }
        assertThat(isCheckedIn).isTrue()
    }

    @Test
    fun it_should_not_checkIn_when_free_room_count_is_not_available() {
        //Given
        val checkInRequest = CheckInRequest("Hilton Hotel", 5000L)

        val hotel = Hotel("Hilton Hotel", 104L, 4999L)

        `when`(hotelReadService.findByName("Hilton Hotel")).thenReturn(hotel)

        //When
        val isCheckedIn = hotelServiceManager.checkIn(checkInRequest)

        //Then
        verify(hotelReadService).findByName("Hilton Hotel")
        verifyZeroInteractions(hotelSaveService)
        assertThat(isCheckedIn).isFalse()
    }

    @Test
    fun it_should_get_all_hotels() {
        //Given
        val california = Hotel("Hotel California", 99L, 250L)
        val istanbul = Hotel("Istanbul", 101L, 400L)
        val newyork = Hotel("Newyork", 102L, 600L)

        `when`(hotelReadService.findAll()).thenReturn(arrayListOf(california, istanbul, newyork))

        //When
        val hotels = hotelServiceManager.findAll()

        //Then
        verify(hotelReadService).findAll()

        assertThat(hotels).isNotNull
        assertThat(hotels).containsExactlyInAnyOrder(california, istanbul, newyork)
    }

    @Test
    fun it_should_get_hotel_by_name() {
        //Given
        val istanbul = Hotel("Istanbul", 101L, 400L)

        `when`(hotelReadService.findByName("Istanbul")).thenReturn(istanbul)

        //When
        val hotel = hotelServiceManager.findByName("Istanbul")

        //Then
        verify(hotelReadService).findByName("Istanbul")

        assertThat(hotel).isNotNull
        assertThat(hotel.name).isEqualTo("Istanbul")
        assertThat(hotel.classification).isEqualTo(101L)
        assertThat(hotel.totalRoomCount).isEqualTo(400L)
        assertThat(hotel.freeRoomCount).isEqualTo(400L)

    }
}