package com.cengenes.kotlin.api.service.imp

import com.cengenes.kotlin.api.entity.Hotel
import com.cengenes.kotlin.api.repository.HotelRepository
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.catchThrowable
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class HotelReadServiceImpTest {

    @InjectMocks
    private lateinit var hotelReadService: HotelReadServiceImp

    @Mock
    private lateinit var hotelRepository: HotelRepository

    @Test
    fun it_should_get_all_hotels() {
        //Given
        val california = Hotel("Hotel California", 99L, 250L)
        val istanbul = Hotel("Istanbul", 101L, 400L)
        val newyork = Hotel("Newyork", 102L, 600L)

        `when`(hotelRepository.findAll()).thenReturn(arrayListOf(california, istanbul, newyork))

        //When
        val hotels = hotelReadService.findAll()

        //Then
        verify(hotelRepository).findAll()

        assertThat(hotels).isNotNull
        assertThat(hotels).containsExactlyInAnyOrder(california, istanbul, newyork)
    }

    @Test
    fun it_should_get_hotel_by_name() {
        //Given
        val istanbul = Hotel("Istanbul", 101L, 400L)

        `when`(hotelRepository.findByName("Istanbul")).thenReturn(Optional.of(istanbul))

        //When
        val hotel = hotelReadService.findByName("Istanbul")

        //Then
        verify(hotelRepository).findByName("Istanbul")

        assertThat(hotel).isNotNull
        assertThat(hotel.name).isEqualTo("Istanbul")
        assertThat(hotel.classification).isEqualTo(101L)
        assertThat(hotel.totalRoomCount).isEqualTo(400L)
        assertThat(hotel.freeRoomCount).isEqualTo(400L)

    }

    @Test
    fun it_should_throw_hotel_not_found_exception_when_hotel_not_exist_by_name() {
        //Given
        `when`(hotelRepository.findByName("Istanbul")).thenReturn(Optional.empty())

        //When
        val throwable = catchThrowable { hotelReadService.findByName("Istanbul") }

        //Then
        verify(hotelRepository).findByName("Istanbul")

        assertThat(throwable).isNotNull()
        assertThat(throwable.message).isEqualTo("Hotel Not Found!")
    }
}