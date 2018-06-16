package com.cengenes.kotlin.api.model.request

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class CheckInRequestTest {

    @Test
    fun it_should_convert_to_string_properly() {
        //Given
        val checkInRequest = CheckInRequest("Hilton Hotel", 5000L)

        //When
        val toString = checkInRequest.toString()

        //Then
        assertThat(toString).isEqualTo("CheckInRequest(hotelName='Hilton Hotel', numberOfGuest=5000)")
    }
}