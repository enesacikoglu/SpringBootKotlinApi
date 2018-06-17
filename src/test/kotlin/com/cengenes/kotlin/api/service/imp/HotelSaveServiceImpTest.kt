package com.cengenes.kotlin.api.service.imp

import com.cengenes.kotlin.api.entity.Hotel
import com.cengenes.kotlin.api.repository.HotelRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class HotelSaveServiceImpTest {

    @InjectMocks
    private lateinit var hotelSaveService: HotelSaveServiceImp

    @Mock
    private lateinit var hotelRepository: HotelRepository

    @Test
    fun it_should_update_hotel() {
        //Given
        val istanbulBeforeSave = Hotel("Istanbul", 101L, 400L)
        val guestCount=201L

        val istanbulAfterSave = Hotel("Istanbul", 101L, 400L)
        istanbulAfterSave.freeRoomCount=istanbulAfterSave.totalRoomCount-guestCount


        `when`(hotelRepository.save(istanbulBeforeSave)).thenReturn(istanbulAfterSave)

        //When
        val updatedHotel = hotelSaveService.save(istanbulBeforeSave)

        //Then
        verify(hotelRepository).save(istanbulBeforeSave)

        assertThat(updatedHotel).isNotNull
        assertThat(updatedHotel.id).isEqualTo(istanbulBeforeSave.id)
        assertThat(updatedHotel.name).isEqualTo("Istanbul")
        assertThat(updatedHotel.totalRoomCount).isEqualTo(400L)
        assertThat(updatedHotel.freeRoomCount).isEqualTo(199L)
    }
}